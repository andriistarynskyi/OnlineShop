package service.report;

import entity.Gender;
import entity.Item;
import entity.Order;
import service.OrderService;
import utils.ItemSorter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderReportService {
    OrderService orderService = new OrderService();

    public Item getMostPopularItemAmongWomen() {
        List<Item> items = getItemsPurchasedByWomen(orderService.getAll());
        if (!items.isEmpty()) {
            return items.get(0);
        } else {
            System.out.println("List of purchased by women items is empty.");
            return null;
        }
    }

    //    get top three best selling items
    public List<Item> getBestSellers() {
        List<Item> items = orderService.getAllOrderedItems();
        return ItemSorter.getItemsSortedByNumberOfSales(items).subList(0, 3);
    }

    //    get candidates to remove (items with no sales or with poor sales performance).
    public List<Item> getCandidatesToRemove() {
        List<Item> items = orderService.getAllOrderedItems();
        List<Item> sortedBySalesItems = ItemSorter.getItemsSortedByNumberOfSales(items);
        List<Item> itemsToRemove = sortedBySalesItems.subList(sortedBySalesItems.size() - 3, sortedBySalesItems.size());
        return itemsToRemove;
    }

    //get list of all purchased items by women sorted by number of sales
    public List<Item> getItemsPurchasedByWomen(List<Order> orders) {
        List<Item> items = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getGender().equals(Gender.FEMALE)) {
                items.addAll(o.getOrderedItems());
            }
        }
        return ItemSorter.getItemsSortedByNumberOfSales(items);
    }

    //get all purchased items within certain period of time sorted by number of sales
    public List<Item> getItemsPurchasedWithinTimeFrame(
            LocalDate startDate, LocalDate endDate) {
        List<Item> items = new ArrayList<>();
        for (Order o : orderService.getAll()) {
            if (o.getOrderPlacementDate().isAfter(startDate) &&
                    o.getOrderPlacementDate().isBefore(endDate)) {
                items.addAll(o.getOrderedItems());
            }
        }
        return ItemSorter.getItemsSortedByNumberOfSales(items).subList(0, 3);
    }
}