import entity.Customer;
import entity.Item;
import entity.Order;
import service.CustomerService;
import service.DbService;
import service.ItemService;
import service.OrderService;

import java.util.List;

public class ExecuteMethod {
    public boolean createReport() {
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
        return true;
    }
}
