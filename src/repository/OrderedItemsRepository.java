package repository;

import DbUtils.DbConnection;
import entity.Item;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderedItemsRepository {
    ItemRepository itemRepository = new ItemRepository();

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT item_id FROM ordered_items";

        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("item_id");
                items.add(itemRepository.getById(id));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}
