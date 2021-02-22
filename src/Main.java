import entity.Item;
import entity.Order;
import service.OrderService;
import service.report.OrderReportService;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ExecuteMethod executeMethod = new ExecuteMethod();
        executeMethod.createReport();

        OrderService orderService = new OrderService();
        for (Order o : orderService.getAll()) {
            System.out.println(o);
        }
        OrderReportService orderReportService = new OrderReportService();
        List<Item> items = orderReportService.getItemsPurchasedByWomen(orderService.getAll());
        List<Item> sortedList = items.stream().sorted().collect(Collectors.toList());
        for (Item i : sortedList) {
            System.out.println(i);
        }
    }
}