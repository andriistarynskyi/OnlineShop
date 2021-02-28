package service;

public class FileReaderService {
    CustomerService customerService = new CustomerService();
    ItemService itemService = new ItemService();
    OrderService orderService = new OrderService();

    public boolean saveDataFromFiles() {
        saveCustomers();
        System.out.println("Customers were added to DB.");
        saveItems();
        System.out.println("Items were added to DB.");
        saveOrders();
        System.out.println("Orders were added to DB.");
        return true;
    }

    public boolean saveCustomers() {
        customerService.parseCustomersFromFile().forEach(c -> customerService.save(c));
        return true;
    }

    public boolean saveItems() {
        itemService.parseItemsFromFile().forEach(i -> itemService.save(i));
        return true;
    }

    public boolean saveOrders() {
        orderService.parse().forEach(o -> orderService.save(o));
        return true;
    }
}