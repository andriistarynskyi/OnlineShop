package service;

import java.util.List;

public class OrderService {

    public List<String> readOrdersFromFile() {
        CustomerService customerService = new CustomerService();
        List<String> ordersDataList = customerService.readCustomersDataFromFile();
        return ordersDataList;
    }

    public void addOrderToDb() {

    }
}