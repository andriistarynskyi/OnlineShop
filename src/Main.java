import entities.Customer;
import entities.Item;
import service.CustomerService;
import service.ReportService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        List<Customer> customersList = customerService.parseCustomersFromFile(customerService.getCustomersData());
        System.out.println(customersList);

        ReportService reportService = new ReportService();
        for (Item item : reportService.sortItemsByPopularity(customersList)) {
            System.out.println(item);
        }

        System.out.println(reportService.getSoldItemsByDate(customersList, LocalDate.of(2016, 5, 4),
                LocalDate.of(2018, 4, 3)));
    }
}
