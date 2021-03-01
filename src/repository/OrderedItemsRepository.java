package repository;

import DbUtils.DbConnection;
import entity.Item;
import entity.Order;

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

    public void save(Order o) {
        String sql = "INSERT INTO ordered_items(order_id, item_id) VALUES(?, ?)";
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, o.getId());
            for (Item i : o.getOrderedItems()) {
                statement.setInt(2, i.getId());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getById(Order order) {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM ordered_items WHERE order_id=" + order.getId();
        try (
                Connection conn = DbConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item item = itemRepository.getById(rs.getInt("item_id"));
                items.add(item);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}