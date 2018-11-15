import java.util.Map;

public class SpecialPriceDeals {

    private Map<Item, PriceDeal> itemPriceDealMap;

    public SpecialPriceDeals(Map<Item, PriceDeal> itemPriceDealMap) {
        this.itemPriceDealMap = itemPriceDealMap;
    }

    public PriceDeal getPriceDealFor(Item item) {
        return itemPriceDealMap.getOrDefault(item, new PriceDeal(1, item.getPrice()));
    }
}
