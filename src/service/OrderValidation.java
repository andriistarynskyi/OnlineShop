package service;

import entities.Customer;
import entities.Item;
import entities.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderValidation {

    public List<Order> validateOrder(List<String> ordersDataFromFile,
                                     List<Customer> customersList,
                                     List<Item> itemsList) {

        List<Order> ordersList = new ArrayList<>();


//        assign order to customer
        for (String str : ordersDataFromFile) {
            Order newOrder = new Order();
            String[] tempArray = str.split(";");
            if (tempArray[5] != null) {
                for (Customer customer : customersList) {
                    if (customer.getName().equals(tempArray[0])) {
                        newOrder.setCustomer(customer);
                    }
                }
            }

//        create list of items which belongs to the order
            String productCodes = tempArray[5];
            String productCodesWithoutSymbols = productCodes.substring(1, productCodes.length() - 1);
//            check if order was placed
            if (!productCodesWithoutSymbols.equals("")) {
                String[] codeArr = productCodesWithoutSymbols.split(" ");
                for (int i = 0; i < codeArr.length; i++) {
                    List<Item> purchasedItemsList = new ArrayList<>();
                    for (Item item : itemsList) {
                        if (item.getCode() == Integer.parseInt(codeArr[i])) {
                            purchasedItemsList.add(item);
                        }
                        newOrder.setOrderedItems(purchasedItemsList);
                    }
                }
            } else {
                newOrder.setOrderedItems(null);

//        set date of order placement
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
                newOrder.setOrderPlacementDate(LocalDate.parse(tempArray[6], formatter));

                ordersList.add(newOrder);
            }
        }
        return ordersList;
    }
}