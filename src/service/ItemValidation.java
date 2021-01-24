package service;

import entities.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ItemValidation {
    public List<Item> validateItems(List<String> itemsDataList) {
        List<Item> itemsList = new ArrayList<>();

        for (String str : itemsDataList) {
            Item newItem = new Item();
            String[] tempArray = str.split(";");
            newItem.setId(Integer.parseInt(tempArray[0]));
            newItem.setTitle(tempArray[1]);
            newItem.setCode(Integer.parseInt(tempArray[2]));
            newItem.setProducer(tempArray[3]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
            newItem.setDateOfLastUpdate(LocalDate.parse(tempArray[4], formatter));
            itemsList.add(newItem);
        }
        return itemsList;
    }
}