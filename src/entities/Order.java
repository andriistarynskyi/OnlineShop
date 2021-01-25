package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    Customer customer;
    List<Item> orderedItems = new ArrayList<>();
    LocalDateTime orderPlacementDate;

    public Order() {
    }

    public Order(Customer customer, List<Item> orderedItems, LocalDateTime orderPlacementDate) {
        this.customer = customer;
        this.orderedItems = orderedItems;
        this.orderPlacementDate = orderPlacementDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public LocalDateTime getOrderPlacementDate() {
        return orderPlacementDate;
    }

    public void setOrderPlacementDate(LocalDateTime orderPlacementDate, DateTimeFormatter formatter) {
        this.orderPlacementDate = orderPlacementDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", orderedItems=" + orderedItems +
                ", orderPlacementDate=" + orderPlacementDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getCustomer().equals(order.getCustomer()) && getOrderedItems().equals(order.getOrderedItems()) && getOrderPlacementDate().equals(order.getOrderPlacementDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getOrderedItems(), getOrderPlacementDate());
    }
}