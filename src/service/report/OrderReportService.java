package service.report;

import entity.Gender;
import entity.Item;
import entity.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderReportService {
    public List<Item> getMostPopularItemsPurchasedByWomen(List<Order> orders) {
        List<Item> items = getUniqueItemsSortedList(getItemsPurchasedByWomen(orders));
        return getMostPopularItems(items);
    }

    public List<Item> getPopularItemsDuringTimeFrame(List<Order> orders,
                                                     LocalDate startDate,
                                                     LocalDate endDate) {
        List<Item> items = getUniqueItemsSortedList(
                getItemsPurchasedWithinTimeFrame(startDate, endDate, orders));

        return getMostPopularItems(items);
    }

    public List<Item> getBestSellers(List<Order> orders) {
        return getMostPopularItems(getAllPurchasedItems(orders));
    }

    public List<Item> getItemsWithPoorSellingHistory(List<Order> orders) {
        return getLeastPopularItems(getAllPurchasedItems(orders));
    }


    //get list of all purchased items by women
    public List<Item> getItemsPurchasedByWomen(List<Order> orders) {
        List<Item> items = new ArrayList<>();
        for (Order o : orders) {
            if (o.getCustomer().getGender().equals(Gender.FEMALE)) {
                items.addAll(o.getOrderedItems());
            }
        }
        return items;
    }

    //get all purchased items
    public List<Item> getAllPurchasedItems(List<Order> orders) {
        List<Item> items = new ArrayList<>();
        for (Order o : orders) {
            items.addAll(o.getOrderedItems());
        }
        return items;
    }

    //get all purchased items within certain period of time
    public List<Item> getItemsPurchasedWithinTimeFrame(
            LocalDate startDate, LocalDate endDate, List<Order> orders) {
        List<Item> items = new ArrayList<>();
        for (Order o : orders) {
            if (o.getOrderPlacementDate().isAfter(startDate) &&
                    o.getOrderPlacementDate().isBefore(endDate)) {
                items.addAll(o.getOrderedItems());
            }
        }
        return items;
    }

    public List<Item> getUniqueItemsSortedList(List<Item> items) {
        List<Item> sortedList = items.stream().sorted().collect(Collectors.toList());
        Set<Item> uniqueItems = new HashSet<>(sortedList);
        List<Item> uniqueItemsList = new ArrayList<>(uniqueItems);
        return uniqueItemsList;
    }

    public List<Item> getMostPopularItems(List<Item> items) {
        if (items.size() > 3) {
            return items.subList(0, 3);
        } else {
            return items;
        }
    }

    public List<Item> getLeastPopularItems(List<Item> items) {
        if (items.size() > 3) {
            return items.subList(items.size() - 3, items.size());
        } else {
            return items;
        }
    }
}