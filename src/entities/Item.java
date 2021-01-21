package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {
    private int id;
    private String title;
    private int code;
    private String producer;
    private LocalDate dateOfLastUpdate;
    private List<Customer> customersPurchasedItemList = new ArrayList<>();

    public Item() {
    }

    public Item(int id, String title, int code, String producer, LocalDate dateOfLastUpdate,
                List<Customer> customersPurchasedItemList) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.producer = producer;
        this.dateOfLastUpdate = dateOfLastUpdate;
        this.customersPurchasedItemList = customersPurchasedItemList;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public LocalDate getDateOfLastUpdate() {
        return dateOfLastUpdate;
    }

    public void setDateOfLastUpdate(LocalDate dateOfLastUpdate) {
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    public List<Customer> getCustomersPurchasedItemList() {
        return customersPurchasedItemList;
    }

    public void setCustomersPurchasedItemList(List<Customer> customersPurchasedItemList) {
        this.customersPurchasedItemList = customersPurchasedItemList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code=" + code +
                ", producer='" + producer + '\'' +
                ", dateOfLastUpdate=" + dateOfLastUpdate +
                ", customersPurchasedItemList=" + customersPurchasedItemList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getCode() == item.getCode() && getTitle().equals(item.getTitle()) && getProducer().equals(item.getProducer()) && getDateOfLastUpdate().equals(item.getDateOfLastUpdate()) && getCustomersPurchasedItemList().equals(item.getCustomersPurchasedItemList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCode(), getProducer(), getDateOfLastUpdate(), getCustomersPurchasedItemList());
    }
}
