package repository;

import DbUtils.DbConnection;
import entity.Item;
import entity.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
