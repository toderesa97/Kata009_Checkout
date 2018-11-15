import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout {

    private SpecialPriceDeals specialPriceDeals;
    private Map<Item, Integer> scannedItems;
    private double due;

    public Checkout(SpecialPriceDeals specialPriceDeals) {
        this.specialPriceDeals = specialPriceDeals;
        scannedItems = new HashMap<>();
        due = 0.;
    }

    public Checkout() {
        scannedItems = new HashMap<>();
        due = 0.;
    }

    public void scan(List<Item> itemList) {
        if (specialPriceDeals == null) {
            itemList.forEach(this::standardScan);
        } else {
            itemList.forEach(this::advanceScan);
        }
    }

    private void advanceScan(Item item) {
        int currentItems = scannedItems.getOrDefault(item, 0) + 1;
        PriceDeal priceDeal = specialPriceDeals.getPriceDealFor(item);
        if (priceDeal.getAmount() == currentItems) {
            due = due - (currentItems-1)*item.getPrice() + priceDeal.getPrice();
            scannedItems.put(item, 0);
        } else {
            standardScan(item);
        }
    }

    private void standardScan(Item item) {
        due += item.getPrice();
        scannedItems.put(item, scannedItems.getOrDefault(item, 0) + 1);
    }

    public double getDue() {
        return due;
    }

}
