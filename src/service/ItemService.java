package service;

import entities.Item;
import utils.ReadDataFromFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public List<Item> parseItemsFromFile() {
        String itemsFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\items.dat";
        List<Item> itemsList = new ArrayList<>();
        List<String> itemsDataList = ReadDataFromFile.readDataFromFile(itemsFilePath);

        for (String str : itemsDataList) {
            String[] tempArray = str.split(";");
            String title = tempArray[1];
            int code = Integer.parseInt(tempArray[2]);
            String producer = tempArray[3];
            LocalDate dateOfLastUpdate = getDateOfLastUpdate(tempArray[4]);

            itemsList.add(new Item(title, code, producer, dateOfLastUpdate));
        }
        return itemsList;
    }

    public LocalDate getDateOfLastUpdate(String str) {
        LocalDate dateOfLastUpdate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
        dateOfLastUpdate = LocalDate.parse(str, formatter);
        return dateOfLastUpdate;
    }
}