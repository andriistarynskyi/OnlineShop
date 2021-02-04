package service;

import entities.Customer;
import entities.Item;
import entities.Order;
import utils.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DbService {

    public void addCustomersToDb(List<Customer> customersList) throws SQLException, IOException {
        String cmdText = "INSERT INTO customers(customer_name, customer_dob, customer_gender, customer_phone_number) " +
                "values(?, ?, ?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(cmdText);
        ) {
            for (Customer customer : customersList) {
                statement.setString(1, customer.getName());
                statement.setDate(2, java.sql.Date.valueOf(customer.getDateOfBirth()));
                statement.setString(3, customer.getGender().toString());
                statement.setString(4, customer.getPhoneNumber());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public void addItemsToDb(List<Item> itemsList) throws SQLException, IOException, ClassNotFoundException {
        String cmdText = "INSERT INTO items(item_title, item_code, item_producer, item_date_of_last_update) values(?, ?, ?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(cmdText);
        ) {
            for (Item item : itemsList) {
                statement.setString(1, item.getTitle());
                statement.setInt(2, item.getCode());
                statement.setString(3, item.getProducer());
                statement.setDate(4, java.sql.Date.valueOf(item.getDateOfLastUpdate()));
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public void addOrdersToDb(List<Order> ordersList) throws IOException, SQLException {
        String sqlQuery = "INSERT INTO orders(customer_id, order_placement_date)" + "values(?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ) {
            for (Order o : ordersList) {
                statement.setInt(1, o.getCustomerId());
                statement.setDate(2, java.sql.Date.valueOf(o.getOrderPlacementDate()));
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public void addOrderedItemsToDb(List<Order> orderList) throws IOException, SQLException {
        String sqlQuery = "INSERT INTO ordered_items(order_id, item_code)" + "values(?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement((sqlQuery));
        ) {
            for (Order order : orderList) {
                int orderId = order.getId();
                statement.setInt(1, orderId);
                for (int i : order.getOrderedItemCodes()) {
                    statement.setInt(2, i);
                    statement.addBatch();
                }
            }
            statement.executeBatch();
        }
    }
}