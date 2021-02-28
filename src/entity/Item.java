package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Item {
    private int id;
    private String title;
    private int code;
    private String producer;
    private LocalDate dateOfLastUpdate;
    private boolean primaryItem;
    private boolean candidateToRemove;

    public Item() {
    }

    public Item(String title, int code, String producer, LocalDate dateOfLastUpdate) {
        this.title = title;
        this.code = code;
        this.producer = producer;
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    public Item(int id, String title, int code, String producer, LocalDate dateOfLastUpdate) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.producer = producer;
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    public boolean isPrimaryItem() {
        return primaryItem;
    }

    public void setPrimaryItem(boolean primaryItem) {
        this.primaryItem = primaryItem;
    }

    public boolean isCandidateToRemove() {
        return candidateToRemove;
    }

    public void setCandidateToRemove(boolean candidateToRemove) {
        this.candidateToRemove = candidateToRemove;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code=" + code +
                ", producer='" + producer + '\'' +
                ", dateOfLastUpdate=" + dateOfLastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getCode() == item.getCode() && getTitle().equals(item.getTitle()) && getProducer().equals(item.getProducer()) && getDateOfLastUpdate().equals(item.getDateOfLastUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCode(), getProducer(), getDateOfLastUpdate());
    }
}