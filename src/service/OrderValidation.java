package service;

import entities.Customer;
import entities.Item;
import entities.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class OrderValidation {

    public List<Order> validateOrder(List<String> ordersDataFromFile) throws DateTimeParseException {
        List<Order> ordersList = new ArrayList<>();
        CustomerService customerService = new CustomerService();
        ItemService itemService = new ItemService();

        List<Customer> customersList = customerService.getCustomersFromFile();
        List<Item> itemsList = itemService.getItemsFromFile();

        for (String str : ordersDataFromFile) {
            Order newOrder = new Order();
            String[] tempArray = str.split(";");
            for (Customer customer : customersList) {
                if (customer.getName().equals(tempArray[0])) {
                    newOrder.setCustomer(customer);
                }
            }
            String orderData = tempArray[5];
            String orderedItemCodes = orderData.replaceAll("[^a-zA-Z0-9_-]", "");
            String[] arrayIds = orderedItemCodes.split(" ");
            List<Item> tempItemsList = new ArrayList<>();

            for (int i = 0; i < arrayIds.length; i++) {
                Integer.parseInt(arrayIds[i]);
                for (Item item : itemsList) {
                    if (item.getCode() == i) {
                        tempItemsList.add(item);
                    }
                }
            }
            newOrder.setOrderedItems(tempItemsList);

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("D/MM/yyyy");
//            newOrder.setOrderPlacementDate(LocalDate.parse(tempArray[6]), formatter);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("m/dd/yyyy");
            newOrder.setOrderPlacementDate(LocalDateTime.parse(tempArray[6]), formatter);
        }
        return ordersList;
    }
}