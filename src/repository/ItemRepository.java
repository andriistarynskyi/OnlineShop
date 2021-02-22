package repository;

import DbUtils.DbConnection;
import entity.Item;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ItemRepository {
    public void save(Item item) {
        String sql = "INSERT INTO items(item_title, item_code, item_producer, item_date_of_last_update) values(?, ?, ?, ?)";
        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, item.getTitle());
            statement.setInt(2, item.getCode());
            statement.setString(3, item.getProducer());
            statement.setDate(4, java.sql.Date.valueOf(item.getDateOfLastUpdate()));
            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public Item getById(int itemId) {
        Item item = null;
        String sql = "SELECT * FROM items WHERE item_id=" + itemId;

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("item_id");
                String name = rs.getString("item_title");
                int code = rs.getInt("item_code");
                String producer = rs.getString("item_producer");
                LocalDate lastUpdate = rs.getDate("item_date_of_last_update").toLocalDate();
                item = new Item(id, name, code, producer, lastUpdate);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}