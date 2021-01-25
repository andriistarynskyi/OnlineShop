import entities.Customer;
import entities.Item;
import entities.Order;
import service.CustomerService;
import service.ItemService;
import service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ItemService itemService = new ItemService();
        List<Item> itemsList = itemService.getItemsFromFile();
//        itemService.addItemsToDb(itemsList);

        CustomerService customerService = new CustomerService();
        List<Customer> customersList = customerService.getCustomersFromFile();
//        customerService.addCustomersToDb(customersList);

        OrderService orderService = new OrderService();
        List<Order> ordersList = orderService.getOrdersFromFile(customersList, itemsList);
        for (Order order : ordersList) {
            System.out.println(order);
        }
    }
}