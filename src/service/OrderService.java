package service;

import entities.Customer;
import entities.Item;
import entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<String> readOrdersFromFile() {
        CustomerService customerService = new CustomerService();
        List<String> ordersDataList = customerService.readCustomersDataFromFile();
        return ordersDataList;
    }

    public List<Order> getOrdersFromFile(List<Customer> customerList, List<Item> itemsList) {
        List<Order> ordersList = new ArrayList<>();
        List<String> ordersDataFile = readOrdersFromFile();
        OrderValidation orderValidation = new OrderValidation();
        ordersList = orderValidation.validateOrder(ordersDataFile, customerList, itemsList);
        return ordersList;
    }
}