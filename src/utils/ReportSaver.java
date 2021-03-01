package utils;

import entity.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportSaver {
    public static void save(List<Item> items, String fileName) {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fileWriter)
        ) {
            bw.append(Constant.ITEM_REPORT_HEADER);
            bw.append("\n");
            for (Item i : items) {
                String[] values = {
                        String.valueOf(i.getId()),
                        i.getTitle(),
                        String.valueOf(i.getCode()),
                        i.getProducer(),
                        String.valueOf(i.getDateOfLastUpdate())
                };
                String line = String.join(Constant.DELIMITER, values);
                bw.append(line);
                bw.append("\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}