package utils;

public class Constant {

    //    constants related to report saving functionality
    public static final String ITEM_REPORT_HEADER = "id;title;code;producer;dateOfLastUpdate";
    public static final String DELIMITER = ";";
    public static final String BEST_SELLERS_REPORT = "primaryItems.csv";
    public static final String CANDIDATES_TO_REMOVE = "candidateToRemove.csv";

    //    paths to files
    public static final String CUSTOMERS_FILE_PATH = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\customers.dat";
    public static final String ITEMS_FILE_PATH = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\items.dat";

    //    patterns of dates used in the files
    public static final String DOB_PATTERN = "d MMMM yyyy";
    public static final String DATE_OF_LAST_UPDATE_PATTERN = "dd.MM.yyyy H:mm:ss";
    public static final String ORDER_PLACEMENT_DATE_PATTERN = "M/dd/yyyy";
}
