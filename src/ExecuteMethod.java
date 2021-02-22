import entity.Item;
import entity.Order;
import service.FileReaderService;
import service.OrderService;
import service.report.OrderReportService;

import java.time.LocalDate;
import java.util.List;

public class ExecuteMethod {

    FileReaderService fileReader = new FileReaderService();
    OrderReportService orderReportService = new OrderReportService();
    OrderService orderService = new OrderService();

    public boolean createReport() {
        List<Order> orders = orderService.getAll();
//        Read data from files and persist all the data into 4 tables
//        (customers, items, orders, orderedItems?)

        fileReader.saveCustomers();
        fileReader.saveItems();
        fileReader.saveOrders();

//        what goods are the most popular among women
        for (Item i : orderReportService.getMostPopularItemsPurchasedByWomen(orders)) {
            System.out.println(i);
        }
        System.out.println("-----------------------");

//        the most popular goods during a particular weekend (passed in as a param)
        for (Item i : orderReportService.getPopularItemsDuringTimeFrame(orders,
                LocalDate.of(2017, 7, 3),
                LocalDate.of(2018, 6, 5))) {
            System.out.println(i);
        }
        System.out.println("-----------------------");

//        get 3 most popular items in the store
        for (Item i : orderReportService.getBestSellers(orders)) {
            System.out.println(i);
        }
        System.out.println("-----------------------");

//        get 3 least popular items in the store
        for (Item i : orderReportService.getItemsWithPoorSellingHistory(orders)) {
            System.out.println(i);
        }
        return true;
    }
}