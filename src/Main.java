import entity.Item;
import entity.Order;
import repository.OrderRepository;
import utils.ItemSorter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        OrderRepository orderRepository = new OrderRepository();
        ExecuteMethod executeMethod = new ExecuteMethod();
        executeMethod.createReport();

        List<Order> orders = orderRepository.getAll();
        List<Item> items = new ArrayList<>();

        orders.stream().forEach(x -> items.addAll(x.getOrderedItems()));

        List<Item> sortedItems = ItemSorter.getItemsSortedByNumberOfSales(items);

        sortedItems.forEach(x -> System.out.println(x));
    }
}