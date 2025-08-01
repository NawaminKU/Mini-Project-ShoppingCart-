package lib.Discount;

import lib.*;

public class DefaultPricingStrategy implements DiscountStrategy {

    @Override
    public double calculateDiscountedPrice(CartItem item) {
        return item.getProduct().getPrice() * item.getQuantity();
    }
} 