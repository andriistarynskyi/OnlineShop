import entities.Customer;
import entities.Item;
import entities.Order;
import service.CustomerService;
import service.DbService;
import service.ItemService;
import service.OrderService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CustomerService customerService = new CustomerService();
        List<Customer> customersList = customerService.parseCustomersFromFile();

        ItemService itemService = new ItemService();
        List<Item> itemsList = itemService.parseItemsFromFile();

        OrderService orderService = new OrderService();
        List<Order> ordersList = orderService.parseOrdersFromFile();

        DbService dbService = new DbService();
//        dbService.addCustomersToDb(customersList);
//        dbService.addItemsToDb(itemsList);
//        dbService.addOrdersToDb(ordersList);
//        dbService.addOrderedItemsToDb(ordersList);
        for(Order o : ordersList) {
            System.out.println(o);
        }
    }
}