package repository;

import DbUtils.DbConnection;
import entity.Customer;
import entity.Gender;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerRepository {
    public void save(Customer customer) {
        String sql = "INSERT INTO customers(customer_name, customer_address, customer_dob, " +
                "customer_gender, customer_phone_number) " +
                "values(?, ?, ?, ?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setDate(3, java.sql.Date.valueOf(customer.getDateOfBirth()));
            statement.setString(4, customer.getGender().toString());
            statement.setString(5, customer.getPhoneNumber());
            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Customer getByName(String name) {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE customer_name = ?";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private Gender getGender(String str) {
        Gender gender = null;
        String genderStr = str.toUpperCase();
        if (genderStr.equals("MALE")) {
            gender = Gender.MALE;
        } else if (genderStr.equals("FEMALE")) {
            gender = Gender.FEMALE;
        }
        return gender;
    }

    public Customer getById(int customerId) {
        Customer customer = null;
        String sql = "SELECT * FROM customers WHERE customer_id=" + customerId;

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer getCustomer(ResultSet rs) throws SQLException {
        int id = rs.getInt("customer_id");
        String fullName = rs.getString("customer_name");
        LocalDate dob = rs.getDate("customer_dob").toLocalDate();
        String address = rs.getString("customer_address");
        Gender gender = getGender(rs.getString("customer_gender"));
        String phoneNumber = rs.getString("customer_phone_number");

        return new Customer(id, fullName, dob, address, gender, phoneNumber);
    }
}