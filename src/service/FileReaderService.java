package service;

import entity.Customer;
import entity.Item;
import entity.Order;

public class FileReaderService {
    CustomerService customerService = new CustomerService();
    ItemService itemService = new ItemService();
    OrderService orderService = new OrderService();

    public boolean readCustomers() {
        for (Customer c : customerService.parseCustomersFromFile()) {
            customerService.save(c);
        }
        System.out.println("Customers were updated");
        return true;
    }

    public boolean readItems() {
        for (Item i : itemService.parseItemsFromFile()) {
            itemService.save(i);
        }
        System.out.println("Items were updated");
        return true;
    }

    public boolean readOrders() {
        for (Order o : orderService.parse()) {
            orderService.save(o);
        }
        System.out.println("Orders were updated");
        return true;
    }
}
