package service;

import utils.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportService {

//    public Integer getCustomerIdFromDb(String customerName) throws IOException, SQLException {
//        int customerId = 0;
//        String cmdText = "SELECT customer_id FROM customers WHERE customer_name = ?";
//        try (
//                Connection connection = DbConnection.getConnection();
//                PreparedStatement statement = connection.prepareStatement(cmdText)
//        ) {
//            statement.setString(1, customerName);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                customerId = resultSet.getInt("customer_id");
//            }
//        }
//        return customerId;
//    }

}