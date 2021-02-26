package repository;

import DbUtils.DbConnection;
import entity.Customer;
import entity.Item;
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
    ItemRepository itemRepository = new ItemRepository();

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
            saveOrderedItems(o);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveOrderedItems(Order o) {
        String sql = "INSERT INTO ordered_items(order_id, item_id) VALUES(?, ?)";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, o.getId());
            for (Item i : o.getOrderedItems()) {
                statement.setInt(2, i.getId());
                statement.addBatch();
            }
            statement.executeBatch();

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
                order.setOrderedItems(getOrderedItems(order));
                orders.add(order);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Item> getOrderedItems(Order order) {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM ordered_items WHERE order_id=" + order.getId();
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = itemRepository.getById(rs.getInt("item_id"));
                items.add(item);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}
