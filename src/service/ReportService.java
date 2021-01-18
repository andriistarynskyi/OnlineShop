package service;

import entities.Customer;
import entities.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReportService {

    public List<Item> sortItemsByPopularity(List<Customer> customersList) {

        List<Item> sortedItemsByPopularity = new ArrayList<>();

        for (Customer customer : customersList) {
            sortedItemsByPopularity.addAll(customer.getLastPurchases());
        }
        sortedItemsByPopularity.sort(Comparator.comparing(i -> Collections.frequency(sortedItemsByPopularity, i)).reversed());

        return sortedItemsByPopularity;
    }

    public List<Item> getSoldItemsByDate(List<Customer> customerList, LocalDate startDate, LocalDate endDate) {
        List<Item> itemsList = new ArrayList<>();
        for (Customer customer : customerList) {
            if (startDate.isBefore(customer.getDateOfLastPurchase()) && endDate.isAfter(customer.getDateOfLastPurchase())) {
                itemsList.addAll(customer.getLastPurchases());

            }
        }
        return itemsList;
    }
}