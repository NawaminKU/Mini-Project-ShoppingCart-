    
package lib;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private PricingService pricingService;
    private ProductCatalog productCatalog;
    private List<CartItem> items;

    //RI : ArrayList ภายในต้องไม่เป็น null และห้ามมี Product ที่ซ้ำกันใน CartItem ที่แตกต่างกัน

    /**
     * Constructor: ต้องรับ PricingService และ ProductCatalog
     */
    public ShoppingCart(PricingService pricingService, ProductCatalog productCatalog) {
        this.pricingService = pricingService;
        this.productCatalog =   productCatalog;
        this.items = new ArrayList<>();
        checkRep();
    }
    
    public void clearCart() {
        items.clear();
        checkRep();
    }

    public void addItem(String productId, int quantity) {
        Product product = productCatalog.findById(productId);
        if (product == null || quantity <= 0) return;
        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.increaseQuantity(quantity);
                checkRep();
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        checkRep();
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getProductId().equals(productId));
        checkRep();
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += pricingService.calculateDiscountedPrice(item);
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    private void checkRep() {
        if (items == null) throw new IllegalStateException("Cart items list is null");
        for (int i = 0; i < items.size(); i++) {
            Product p1 = items.get(i).getProduct();
            for (int j = i + 1; j < items.size(); j++) {
                Product p2 = items.get(j).getProduct();
                if (p1.getProductId().equals(p2.getProductId())) {
                    throw new IllegalStateException("Duplicate product in cart");
                }
            }
        }
    }
}
