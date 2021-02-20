import service.FileReaderService;

public class ExecuteMethod {

    FileReaderService fileReader = new FileReaderService();

    public boolean createReport() {

//        Read data from files and persist all the data into 4 tables
//        (customers, items, orders, orderedItems?)

        fileReader.readCustomers();
        fileReader.readItems();
        fileReader.readOrders();
        return true;
    }
}
