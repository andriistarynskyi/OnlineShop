import entities.Customer;
import entities.Item;
//import service.CustomerService;
import service.CustomerService;
import service.ItemService;
import service.OrderValidation;

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

        List<String> customersDataList = customerService.readCustomersDataFromFile();
        OrderValidation orderValidation = new OrderValidation();
        System.out.println(orderValidation.validateOrder(customersDataList));
    }
}