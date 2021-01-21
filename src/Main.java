import service.CustomerService;

public class Main {
    public static void main(String[] args) {
//        ItemService itemService = new ItemService();
//        System.out.println(itemService.addItemsToDb());

        CustomerService customerService = new CustomerService();
        System.out.println(customerService.addCustomersToDb());

    }
}