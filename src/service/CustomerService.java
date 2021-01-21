package service;

import entities.Customer;
import entities.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    ReadDataFromFileService readDataFromFileService = new ReadDataFromFileService();

    public List<Customer> addCustomersToDb() {
        String customersFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\customers.dat";
        List<Customer> customersList = new ArrayList<>();
        List<String> dataFromFileList = readDataFromFileService.readDataFromFile(customersFilePath);
        Customer newCustomer = new Customer();
        List<Integer> listOfItemsCodes = new ArrayList<>();

        for (String str : dataFromFileList) {
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
            String productCodes = tempArray[5];
            String lastOrderProductsIds = productCodes.replaceAll("[^a-zA-Z0-9]", "");
            String[] arrayIds = lastOrderProductsIds.split(" ");
            List<Integer> listOfCodes = new ArrayList<>();
            for (int i = 0; i < arrayIds.length; i++) {
                Integer.parseInt(arrayIds[i]);
                listOfCodes.add(i);
            }
            newCustomer.setProductsCodes(listOfCodes);

            DateTimeFormatter orderDateFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
            newCustomer.setDateOfLastPurchase(LocalDate.parse(tempArray[6], orderDateFormatter));

            customersList.add(newCustomer);

        }
        return customersList;
    }
}