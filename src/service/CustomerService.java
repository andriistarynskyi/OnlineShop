package service;

import entities.Customer;
import entities.Gender;
import entities.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<String> getCustomersData() {
        List<String> customerDataList = new ArrayList<>();
        String customerDataOne = "Nikki Sturges;11 August 1964;\"457 St Paul Street Utica NY 13501\";female;(284) 755-7619;1 14;6/10/2017";
        String customerDataTwo = "Shaunta Mcgeorge;10 September 1966;\"7710 Glenwood Lane Centreville VA 20120\";female;;14 71;6/10/2017";
        String customerDataThree = "Stephani Lunsford;10 March 1967;\"65 SE. Railroad St. Reading\tMA 01867\";female;(217) 247-4024;6 14;6/12/2017";
        customerDataList.add(customerDataOne);
        customerDataList.add(customerDataTwo);
        customerDataList.add(customerDataThree);

        return customerDataList;
    }

    public List<Customer> parseCustomersFromFile(List<String> customerDataList) {
        List<Customer> customersList = new ArrayList<>();
        for (String customerData : customerDataList) {
            String[] tempArray = customerData.split(";");
            Customer newCustomer = new Customer();

            newCustomer.setName(tempArray[0]);

            DateTimeFormatter dobFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
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

            List<Item> lastOrderProductsList = new ArrayList<>();
            ItemService itemService = new ItemService();
            List<Item> itemsList = itemService.parseItemsFromFile(itemService.getItemsData());


            String lastPurchaseProductsIds = tempArray[5];
            String[] productsCodes = lastPurchaseProductsIds.split(" ");

            for (Item item : itemsList) {
                for (int i = 0; i < productsCodes.length; i++) {
                    if (item.getCode() == Integer.parseInt(productsCodes[i])) {
                        lastOrderProductsList.add(item);
                    }
                }
            }

            newCustomer.setLastPurchases(lastOrderProductsList);

            DateTimeFormatter orderDateFormatter = DateTimeFormatter.ofPattern("d/MM/yyy");
            newCustomer.setDateOfLastPurchase(LocalDate.parse(tempArray[6], orderDateFormatter));

            customersList.add(newCustomer);
        }
        return customersList;
    }
}