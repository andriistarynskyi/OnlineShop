package service;

import entities.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public List<String> getItemsData() {
        List<String> itemsData = new ArrayList<>();
        String itemDataOne = "1;cauliflower;14;Smokey Bones;10.06.2017 22:50:41";
        String itemDataTwo = "2;apple pie spice;1;Scores;11.06.2017 18:09:44";
        String itemDataThree = "3;lettuce;11;Houlihan's;06.06.2017 12:54:27";
        String itemDataFour = "4;green beans;71;Sizzler;25.07.2017 21:28:44";

        itemsData.add(itemDataOne);
        itemsData.add(itemDataTwo);
        itemsData.add(itemDataThree);
        itemsData.add(itemDataFour);

        return itemsData;
    }

    public List<Item> parseItemsFromFile(List<String> itemsData) {
        List<Item> items = new ArrayList<>();
        for (String item : itemsData) {
            String[] tempArray = item.split(";");
            Item newItem = new Item();

            newItem.setId(Integer.parseInt(tempArray[0]));
            newItem.setTitle(tempArray[1]);
            newItem.setCode(Integer.parseInt(tempArray[2]));
            newItem.setProducer(tempArray[3]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");

            newItem.setDateOfLastUpdate(LocalDate.parse(tempArray[4], formatter));
            items.add(newItem);
        }
        return items;
    }
}