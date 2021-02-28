import service.FileReaderService;
import service.report.OrderReportService;

import java.time.LocalDate;

public class ExecuteMethod {

    FileReaderService fileReader = new FileReaderService();
    OrderReportService orderReportService = new OrderReportService();

    public boolean createReport() {
//        Read data from files and persist all the data into 4 tables
//        (customers, items, orders, orderedItems?)
        fileReader.saveDataFromFiles();

        System.out.println("-----------------------");

//        what product is the most popular among women
        System.out.println("The most popular item purchased by women is " +
                orderReportService.getMostPopularItemAmongWomen() + ".");

        System.out.println("-----------------------");

//        get 3 least popular items in the store
        System.out.println("Best sellers in the store are:");
        orderReportService.getBestSellers().forEach(i -> System.out.println(i));

        System.out.println("-----------------------");

        System.out.println("Candidates to remove from the store are:");
        orderReportService.getCandidatesToRemove().forEach(i -> System.out.println(i));

        System.out.println("-----------------------");

        System.out.println("Top three best selling items during certain period of time are:");
        orderReportService.getItemsPurchasedWithinTimeFrame(
                LocalDate.of(2016, 7, 10),
                LocalDate.of(2018, 8, 8))
                .forEach(i -> System.out.println(i));

        return true;
    }
}