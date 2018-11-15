import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class CheckoutShould {

    private Item leek = new Item("leek", 0.6);
    private Item milk = new Item("milk", 1.25);

    @Test
    public void computeBasicCheckOuts() {
        Checkout checkout = new Checkout();
        checkout.scan(Arrays.asList(leek, milk));
        Assert.assertEquals(1.85, checkout.getDue(), 0.000001);
    }

    @Test
    public void computeCheckOutsWithSpecialOffers() {
        SpecialPriceDeals specialPriceDeals = new SpecialPriceDeals(
                new HashMap<>() {{
                    put(leek, new PriceDeal(3, 1.5));
                }}
        );
        Checkout checkout = new Checkout(specialPriceDeals);
        checkout.scan(Arrays.asList(leek, leek, leek));
        Assert.assertEquals(1.5, checkout.getDue(), 0.000001);
    }

    @Test
    public void computeCheckOutsWithSpecialOffers1() {
        SpecialPriceDeals specialPriceDeals = new SpecialPriceDeals(
                new HashMap<>() {{
                    put(leek, new PriceDeal(3, 1.5));
                    put(milk, new PriceDeal(5, 2.5));
                }}
        );
        Checkout checkout = new Checkout(specialPriceDeals);
        checkout.scan(Arrays.asList(leek, leek, milk, milk, milk, milk, milk));
        Assert.assertEquals(3.7, checkout.getDue(), 0.000001);
    }

}
