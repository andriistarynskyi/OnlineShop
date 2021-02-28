import service.FileReaderService;
import service.report.ItemReportService;

import java.time.LocalDate;

public class ExecuteMethod {

    FileReaderService fileReader = new FileReaderService();
    ItemReportService itemReportService = new ItemReportService();

    public boolean createReport() {
//        Read data from files and persist all the data into 4 tables
//        (customers, items, orders, orderedItems?)
        fileReader.saveDataFromFiles();

        System.out.println("-----------------------");

//        what product is the most popular among women
        System.out.println("The most popular item purchased by women is " +
                itemReportService.getMostPopularItemAmongWomen() + ".");

        System.out.println("-----------------------");

//        get 3 least popular items in the store
        System.out.println("Best sellers in the store are:");
        itemReportService.getBestSellers().forEach(i -> System.out.println(i));

        System.out.println("-----------------------");

        System.out.println("Candidates to remove from the store are:");
        itemReportService.getCandidatesToRemove().forEach(i -> System.out.println(i));

        System.out.println("-----------------------");

        LocalDate startDate = LocalDate.of(2016, 7, 10);
        LocalDate endDate = LocalDate.of(2018, 8, 8);

        System.out.println("Top three best selling items from " + startDate + " till " + endDate + ".");
        itemReportService.getItemsPurchasedWithinTimeFrame(startDate, endDate)
                .forEach(i -> System.out.println(i));

        System.out.println("-----------------------");
        System.out.println("Best selling items were saved to xls file =" +
                itemReportService.saveBestSellersToFile());

        System.out.println("-----------------------");
        System.out.println("Candidates to remove were saved to xls file=" +
                itemReportService.saveCandidatesToRemoveToFile());

        return true;
    }
}