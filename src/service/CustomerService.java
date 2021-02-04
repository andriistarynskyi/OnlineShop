package service;

import entities.Customer;
import entities.Gender;
import utils.ReadDataFromFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<Customer> parseCustomersFromFile() {
        String customersFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\customers.dat";
        List<Customer> customersList = new ArrayList<>();
        List<String> customersDataList = ReadDataFromFile.readDataFromFile(customersFilePath);

        for (String str : customersDataList) {
            String[] tempArray = str.split(";");

            String customerName = tempArray[0];
            LocalDate dateOfBirth = getCustomerDoB(tempArray[1]);
            String address = tempArray[2];
            Gender gender = getCustomerGender(tempArray[3]);
            String phoneNumber = getCustomerPhoneNumber(tempArray[4]);

            customersList.add(new Customer(customerName, dateOfBirth, address, gender, phoneNumber));

        }
        return customersList;
    }

    public Gender getCustomerGender(String str) {
        Gender gender = null;
        if (str.equals("Male") || (str.equals("male"))) {
            gender = Gender.MALE;
        } else if (str.equals("Female") || (str.equals("female"))) {
            gender = Gender.FEMALE;
        }
        return gender;
    }

    public LocalDate getCustomerDoB(String str) {
        LocalDate dob = null;
        DateTimeFormatter dobFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        dob = LocalDate.parse(str, dobFormatter);
        return dob;
    }

    public String getCustomerPhoneNumber(String str) {
        String phoneNumber = "";
        if (!str.equals("")) {
            phoneNumber = str;
        } else {
            phoneNumber = "";
        }
        return phoneNumber;
    }
}