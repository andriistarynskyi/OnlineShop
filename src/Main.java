import entities.Item;
import service.ItemService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        ItemService itemService = new ItemService();
        List<Item> itemsList = itemService.getItemsFromFile();
        itemService.addItemsToDb(itemsList);
    }
}