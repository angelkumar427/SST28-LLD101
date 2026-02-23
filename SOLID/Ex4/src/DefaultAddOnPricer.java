public class DefaultAddOnPricer implements AddOnPricer {
    @Override
    public Money calculatePrice(AddOn addOn) {
        double price;
        if (addOn == AddOn.MESS) price = 1000.0;
        else if (addOn == AddOn.LAUNDRY) price = 500.0;
        else if (addOn == AddOn.GYM) price = 300.0;
        else price = 0.0;
        return new Money(price);
    }
}
