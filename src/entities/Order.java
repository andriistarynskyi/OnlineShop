package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private static int count = 1;
    private int id = 1;
    private int customerId;
    private LocalDate orderPlacementDate;
    private List<Integer> orderedItemCodes = new ArrayList();

    public Order() {

        id = count++;
    }

    public Order(int customerId, LocalDate orderPlacementDate, List<Integer> orderedItemCodes) {
        this.customerId = customerId;
        this.orderPlacementDate = orderPlacementDate;
        this.orderedItemCodes = orderedItemCodes;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderPlacementDate() {
        return orderPlacementDate;
    }

    public void setOrderPlacementDate(LocalDate orderPlacementDate) {
        this.orderPlacementDate = orderPlacementDate;
    }

    public List<Integer> getOrderedItemCodes() {
        return orderedItemCodes;
    }

    public void setOrderedItemCodes(List<Integer> orderedItemCodes) {
        this.orderedItemCodes = orderedItemCodes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderPlacementDate=" + orderPlacementDate +
                ", orderedItemCodes=" + orderedItemCodes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() && getCustomerId() == order.getCustomerId() && getOrderPlacementDate().equals(order.getOrderPlacementDate()) && getOrderedItemCodes().equals(order.getOrderedItemCodes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomerId(), getOrderPlacementDate(), getOrderedItemCodes());
    }
}