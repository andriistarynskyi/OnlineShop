package service;

public class CustomerService {

    public static String customersFilePath = "C:\\Users\\astar\\IdeaProjects\\OnlineShop\\src\\customers.dat";

    ReadDataFromFileService readDataFromFileService = new ReadDataFromFileService();

    public void addCustomersToDb() {
        readDataFromFileService.readDataFromFile(customersFilePath);
    }

//
//    public List<Customer> parseCustomersFromFile(List<String> customerDataList) {
//        List<Customer> customersList = new ArrayList<>();
//        for (String customerData : customerDataList) {
//            String[] tempArray = customerData.split(";");
//            Customer newCustomer = new Customer();
//
//            newCustomer.setName(tempArray[0]);
//
//            DateTimeFormatter dobFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
//            newCustomer.setDateOfBirth(LocalDate.parse(tempArray[1], dobFormatter));
//
//            newCustomer.setAddress(tempArray[2]);
//
//            if (tempArray[3].equals("female")) {
//                newCustomer.setGender(Gender.FEMALE);
//            } else if (tempArray[3].equals("male")) {
//                newCustomer.setGender((Gender.MALE));
//            }
//
////            check if phone number exist
//            if (!tempArray[4].equals("")) {
//                newCustomer.setPhoneNumber(tempArray[4]);
//            } else {
//                newCustomer.setPhoneNumber(null);
//            }
//
//            List<Item> lastOrderProductsList = new ArrayList<>();
//            ItemService itemService = new ItemService();
//            List<Item> itemsList = itemService.parseItemsFromFile(itemService.getItemsData());
//
//
//            String lastPurchaseProductsIds = tempArray[5];
//            String[] productsCodes = lastPurchaseProductsIds.split(" ");
//
//            for (Item item : itemsList) {
//                for (int i = 0; i < productsCodes.length; i++) {
//                    if (item.getCode() == Integer.parseInt(productsCodes[i])) {
//                        lastOrderProductsList.add(item);
//                    }
//                }
//            }
//
//            newCustomer.setLastPurchases(lastOrderProductsList);
//
//            DateTimeFormatter orderDateFormatter = DateTimeFormatter.ofPattern("d/MM/yyy");
//            newCustomer.setDateOfLastPurchase(LocalDate.parse(tempArray[6], orderDateFormatter));
//
//            customersList.add(newCustomer);
//        }
//        return customersList;
//    }
}