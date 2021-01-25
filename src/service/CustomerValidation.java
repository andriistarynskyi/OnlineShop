package service;

import entities.Customer;
import entities.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerValidation {
    public List<Customer> validateCustomer(List<String> customersDataList) {
        List<Customer> customersList = new ArrayList<>();

        for (String str : customersDataList) {
            Customer newCustomer = new Customer();
            String[] tempArray = str.split(";");
            newCustomer.setName(tempArray[0]);
            DateTimeFormatter dobFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            newCustomer.setDateOfBirth(LocalDate.parse(tempArray[1], dobFormatter));
            newCustomer.setAddress(tempArray[2]);
            if (tempArray[3].equals("female")) {
                newCustomer.setGender(Gender.FEMALE);
            } else if (tempArray[3].equals("male")) {
                newCustomer.setGender((Gender.MALE));
            }

//            check if phone number exist
            if (!tempArray[4].equals("")) {
                newCustomer.setPhoneNumber(tempArray[4]);
            } else {
                newCustomer.setPhoneNumber(null);
            }
            customersList.add(newCustomer);
        }
        return customersList;
    }
}