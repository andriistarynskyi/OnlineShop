package service;

import entity.Order;
import DbUtils.DbConnection;
import utils.ReadDataFromFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public List<Order> parseOrdersFromFile() throws IOException, SQLException {
        String customersFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\customers.dat";
        List<String> orderDataList = ReadDataFromFile.readDataFromFile(customersFilePath);
        List<Order> ordersList = new ArrayList<>();
        for (String str : orderDataList) {
            String[] tempArray = str.split(";");
            int customerId = getCustomerIdFromDb(tempArray[0]);
            LocalDate orderPlacementDate = getOrderPlacementDate(tempArray[6]);
            List<Integer> orderedProductCodes = getOrderedProductCodes(tempArray[5]);
            ordersList.add(new Order(customerId, orderPlacementDate, orderedProductCodes));
        }
        return ordersList;
    }

    public LocalDate getOrderPlacementDate(String str) {
        LocalDate orderPlacementDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        orderPlacementDate = LocalDate.parse(str, formatter);
        return orderPlacementDate;
    }

    public List<Integer> getOrderedProductCodes(String str) {
        List<Integer> listOfProductCodes = new ArrayList<>();
        String strWithoutQuotes = str.substring(1, str.length() - 1);
        if (strWithoutQuotes.length() > 2) {
            String[] tempArray = strWithoutQuotes.split(" ");
            for (String s : tempArray) {
                int code = 0;
                code = Integer.valueOf(s);
                listOfProductCodes.add(code);
            }
        } else {
            listOfProductCodes.add(Integer.valueOf(strWithoutQuotes));
        }
        return listOfProductCodes;
    }

    public Integer getCustomerIdFromDb(String customerName) throws IOException, SQLException {
        int customerId = 0;
        String cmdText = "SELECT customer_id FROM customers WHERE customer_name = ?";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(cmdText)
        ) {
            statement.setString(1, customerName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customerId = resultSet.getInt("customer_id");
            }
        }
        return customerId;
    }
}