package service.report;

import entity.Item;
import entity.Order;
import service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderReportService {
    OrderService orderService = new OrderService();

    //get list of all purchased items by women
    public List<Item> getItemsPurchasedByWomen(List<Order> orders) {
        List<Item> items = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getGender().equals("FEMALE")) {
                items.addAll(o.getOrderedItems());
            }
        }
        return items;
    }
}
