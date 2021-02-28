package service.report;

import entity.Gender;
import entity.Item;
import entity.Order;
import service.ItemService;
import service.OrderService;
import utils.ItemSorter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemReportService {
    OrderService orderService = new OrderService();
    ItemService itemService = new ItemService();

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
        List<Item> bestSellingItems = ItemSorter.getItemsSortedByNumberOfSales(items).subList(0, 3);
        bestSellingItems.forEach(i -> itemService.updatePrimaryItem(i));
        return bestSellingItems;
    }

    //    get candidates to remove (items with no sales or with poor sales performance).
    public List<Item> getCandidatesToRemove() {
        List<Item> items = orderService.getAllOrderedItems();
        List<Item> sortedBySalesItems = ItemSorter.getItemsSortedByNumberOfSales(items);
        List<Item> itemsToRemove = sortedBySalesItems.subList(sortedBySalesItems.size() - 3, sortedBySalesItems.size());
        itemsToRemove.forEach(i -> itemService.updadeCandidateToRemove(i));
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

    public boolean saveBestSellersToFile() {
        List<Item> items = itemService.getBestSellers();
        return true;
    }

    public boolean saveCandidatesToRemoveToFile() {
        List<Item> items = itemService.getCandidatesToRemove();
        return true;
    }
}