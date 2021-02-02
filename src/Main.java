import entities.Customer;
import service.CustomerService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        ItemService itemService = new ItemService();
//        List<Item> itemsList = itemService.getItemsFromFile();
//        itemService.addItemsToDb(itemsList);

        CustomerService customerService = new CustomerService();
        List<Customer> customersList = customerService.getCustomersFromFile();
        customerService.addCustomersToDb(customersList);
    }
}