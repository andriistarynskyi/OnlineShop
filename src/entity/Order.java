package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private Customer customer;
    private LocalDate orderPlacementDate;
    private List<Item> orderedItems = new ArrayList<>();

    private boolean isFilled;

    public Order() {
    }

    public Order(Customer customer, LocalDate orderPlacementDate) {
        isFilled = false;
        id = count.incrementAndGet();
        this.customer = customer;
        this.orderPlacementDate = orderPlacementDate;
    }

    public Order(int id, Customer customer, LocalDate orderPlacementDate) {
        this.id = id;
        this.customer = customer;
        this.orderPlacementDate = orderPlacementDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderPlacementDate() {
        return orderPlacementDate;
    }

    public void setOrderPlacementDate(LocalDate orderPlacementDate) {
        this.orderPlacementDate = orderPlacementDate;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderPlacementDate=" + orderPlacementDate +
                ", orderedItems=" + orderedItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() && getCustomer().equals(order.getCustomer()) && getOrderPlacementDate().equals(order.getOrderPlacementDate()) && getOrderedItems().equals(order.getOrderedItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getOrderPlacementDate(), getOrderedItems());
    }
}