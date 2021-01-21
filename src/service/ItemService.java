package service;

import entities.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    ReadDataFromFileService readDataFromFileService = new ReadDataFromFileService();

    public List<Item> addItemsToDb() {
        String itemsFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\items.dat";
        List<Item> itemsList = new ArrayList<>();
        List<String> dataFromFileList = readDataFromFileService.readDataFromFile(itemsFilePath);
        Item newItem = new Item();

        for (String str : dataFromFileList) {
            String[] tempArray = str.split(";");
            newItem.setId(Integer.parseInt(tempArray[0]));
            newItem.setTitle(tempArray[1]);
            newItem.setCode(Integer.parseInt(tempArray[2]));
            newItem.setProducer(tempArray[3]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");
            newItem.setDateOfLastUpdate(LocalDate.parse(tempArray[4], formatter));
        }
        itemsList.add(newItem);
        return itemsList;
    }
}