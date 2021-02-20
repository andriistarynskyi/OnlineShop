package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static LocalDate parse(String dateStr, String pattern) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        date = LocalDate.parse(dateStr, formatter);
        return date;
    }
}