package service;

import entities.Item;
import utils.DbConnection;
import utils.ReadDataFromFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemService {

    public static String itemsFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\items.dat";

    public List<String> readItemsDataFromFile() {
        List<String> itemsDataList = ReadDataFromFile.readDataFromFile(itemsFilePath);
        return itemsDataList;
    }

    public List<Item> getItemsFromFile() {
        List<String> itemsDataList = readItemsDataFromFile();
        ItemValidation itemsValidation = new ItemValidation();
        List<Item> itemsList = itemsValidation.validateItems(itemsDataList);
        return itemsList;
    }

    public void addItemsToDb(List<Item> itemsList) throws SQLException, IOException, ClassNotFoundException {
        String cmdText = "INSERT INTO item(title, code, producer, dateOfLastUpdate) values(?, ?, ?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(cmdText);
        ) {
            for (Item item : itemsList) {
                statement.setString(1, item.getTitle());
                statement.setInt(2, item.getCode());
                statement.setString(3, item.getProducer());
                statement.setDate(4, java.sql.Date.valueOf(item.getDateOfLastUpdate()));
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }
}