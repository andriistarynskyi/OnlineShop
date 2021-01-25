package service;

import entities.Customer;
import utils.DbConnection;
import utils.ReadDataFromFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    public List<String> readCustomersDataFromFile() {
        String customersFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\customers.dat";
        List<String> customersDataList = ReadDataFromFile.readDataFromFile(customersFilePath);
        return customersDataList;
    }

    public List<Customer> getCustomersFromFile() {
        List<String> customersDataFromFile = readCustomersDataFromFile();
        CustomerValidation customerValidation = new CustomerValidation();
        List<Customer> customersList = customerValidation.validateCustomer(customersDataFromFile);
        return customersList;
    }

    public void addCustomersToDb(List<Customer> customerList) throws SQLException {
        String cmdText = "INSERT INTO customer(name, dateOfBirth, gender, phoneNumber) " +
                "values(?, ?, ?, ?)";
        try (
                Connection connection = DbConnection.connect();
                PreparedStatement statement = connection.prepareStatement(cmdText);
        ) {
            for (Customer customer : customerList) {
                statement.setString(1, customer.getName());
                statement.setDate(2, java.sql.Date.valueOf(customer.getDateOfBirth()));
                statement.setString(3, customer.getGender().toString());
                statement.setString(4, customer.getPhoneNumber());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}