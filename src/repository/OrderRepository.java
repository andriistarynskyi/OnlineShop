package repository;

import DbUtils.DbConnection;
import entity.Item;
import entity.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepository {

    public void save(Order order) {
        String sqlQuery = "INSERT INTO orders(customer_id, order_placement_date)" + "values(?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ) {
            statement.setInt(1, order.getCustomer().getId());
            statement.setDate(2, java.sql.Date.valueOf(order.getOrderPlacementDate()));
            if (!order.getOrderedItems().equals(null)) {
                saveOrderedItemCodes(order);
            }
            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveOrderedItemCodes(Order order) {
        String sqlQuery = "INSERT INTO ordered_items(order_id, item_code)" + "values(?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement((sqlQuery));
        ) {
            statement.setInt(1, order.getId());
            for (Item i : order.getOrderedItems()) {
                statement.setInt(2, i.getCode());
                statement.addBatch();

            }
            statement.executeBatch();
        } catch (
                SQLException throwable) {
            throwable.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}