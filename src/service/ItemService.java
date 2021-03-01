package service;

import entity.Item;
import repository.ItemRepository;
import utils.Constant;
import utils.DateParser;
import utils.FileReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    ItemRepository itemRepository = new ItemRepository();

    public List<Item> parseItemsFromFile() {
        String itemsFilePath = Constant.ITEMS_FILE_PATH;
        List<Item> itemsList = new ArrayList<>();
        List<String> itemsDataList = FileReader.read(itemsFilePath);

        for (String str : itemsDataList) {
            String[] tempArray = str.split(";");
            String title = tempArray[1];
            int code = Integer.parseInt(tempArray[2]);
            String producer = tempArray[3];
            LocalDate dateOfLastUpdate = DateParser.parse(tempArray[4], Constant.DATE_OF_LAST_UPDATE_PATTERN);

            itemsList.add(new Item(title, code, producer, dateOfLastUpdate));
        }
        return itemsList;
    }

    public boolean save(Item item) {
        itemRepository.save(item);
        return true;
    }

    public Item getById(int id) {
        return itemRepository.getById(id);
    }

    public boolean updatePrimaryItem(Item item) {
        itemRepository.updatePrimaryItem(item);
        return true;
    }

    public boolean updadeCandidateToRemove(Item item) {
        itemRepository.updateCandidateToRemove(item);
        return true;
    }

    public List<Item> getBestSellers() {
        return itemRepository.getBestSellers();
    }

    public List<Item> getCandidatesToRemove() {
        return itemRepository.getCandidatesToRemove();
    }
}