import service.ItemService;

public class Main {
    public static void main(String[] args) {
        ItemService itemService = new ItemService();
        System.out.println(itemService.addItemsToDb());
    }
}