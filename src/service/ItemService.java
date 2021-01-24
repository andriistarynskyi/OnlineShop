package service;

import entities.Item;
import utils.DbConnection;
import utils.ReadDataFromFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemService {

    public List<String> readItemsFromFile() {
        String itemsFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\items.dat";
        List<String> itemsDataList = ReadDataFromFile.readDataFromFile(itemsFilePath);
        return itemsDataList;
    }

    public List<Item> getItemsFromFile() {
        List<String> itemsDataList = readItemsFromFile();
        ItemValidation itemsValidation = new ItemValidation();
        List<Item> itemsList = itemsValidation.validateItems(itemsDataList);
        return itemsList;
    }

    public void addItemsToDb(List<Item> itemsList) throws SQLException {
        String cmdText = "INSERT INTO items(id, title, code, producer, dateOfLastUpdate) values(?, ?, ?, ?, ?)";
        try (
                Connection connection = DbConnection.connect();
                PreparedStatement statement = connection.prepareStatement(cmdText);
        ) {
            for (Item item : itemsList) {
                statement.setInt(1, item.getId());
                statement.setString(2, item.getTitle());
                statement.setInt(3, item.getCode());
                statement.setString(4, item.getProducer());
                statement.setDate(5, java.sql.Date.valueOf(item.getDateOfLastUpdate()));
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}