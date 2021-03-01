package repository;

import DbUtils.DbConnection;
import entity.Customer;
import entity.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    CustomerRepository customerRepository = new CustomerRepository();
    OrderedItemsRepository orderedItemsRepository = new OrderedItemsRepository();

    public void save(Order o) {
        String sql = "INSERT INTO orders(order_id, customer_id, order_placement_date) values(?, ?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, o.getId());
            statement.setInt(2, o.getCustomer().getId());
            statement.setDate(3, java.sql.Date.valueOf(o.getOrderPlacementDate()));
            statement.executeUpdate();
            orderedItemsRepository.save(o);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("order_id");
                Customer customer = customerRepository.getById(rs.getInt("customer_id"));
                LocalDate orderPlacementDate = rs.getDate("order_placement_date").toLocalDate();
                Order order = new Order(id, customer, orderPlacementDate);
                order.setOrderedItems(orderedItemsRepository.getById(order));
                orders.add(order);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return orders;
    }
}