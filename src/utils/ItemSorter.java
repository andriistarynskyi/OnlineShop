package utils;

import entity.Item;

import java.util.*;

public class ItemSorter {

    //    sort items by number of sales
    public static List<Item> getItemsSortedByNumberOfSales(List<Item> items) {
        Map<Integer, Item> itemsSalesCount = getCountOfOccurrences(items);
        Map<Integer, Item> sortedMapByKey = getSortedMap(itemsSalesCount);
        List<Item> sortedItems = getSortedItemsList(sortedMapByKey);

        return sortedItems;
    }

    //    count occurrences of items
    public static Map<Integer, Item> getCountOfOccurrences(List<Item> items) {
        Map<Integer, Item> itemCountMap = new LinkedHashMap<>();
        for (Item i : items) {
            itemCountMap.put(Collections.frequency(items, i), i);
        }
        return itemCountMap;
    }

    //    sort map by keys (number of sales)
    public static Map<Integer, Item> getSortedMap(Map<Integer, Item> unsortedMap) {
        Map<Integer, Item> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }

    //    create list of items out of the sorted map
    public static List<Item> getSortedItemsList(Map<Integer, Item> sortedMap) {
        List<Item> sortedItemsList = new ArrayList<>(sortedMap.values());
        return sortedItemsList;
    }
}