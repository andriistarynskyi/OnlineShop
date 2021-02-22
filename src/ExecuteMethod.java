import service.FileReaderService;

public class ExecuteMethod {

    FileReaderService fileReader = new FileReaderService();

    public boolean createReport() {

//        Read data from files and persist all the data into 4 tables
//        (customers, items, orders, orderedItems?)

        fileReader.saveCustomers();
        fileReader.saveItems();
        fileReader.saveOrders();

//        Create reports from Java:
//        what goods are the most popular among women



//        the most popular goods during a particular weekend (passed in as a param)
//        Alter the DB using plain SQL:
//        create new columns: PrimaryItem, CandidateToRemove
//        fetch three the most and least popular goods and mark them correspondingly in the table
//        Write all the marked goods (see the bullet point above) in two different files:
//        primaryItems.csv with the most popular goods;
//        candidateToRemove.csv - the least popular good.
//                All the data should be in csv format with ; used as a delimeter

        return true;
    }
}
