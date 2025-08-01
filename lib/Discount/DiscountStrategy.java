package lib.Discount;

import lib.CartItem;

/**
 * Interface สำหรับกลยุทธ์การคำนวณส่วนลด
 */

public interface DiscountStrategy {
    /**
     * คำนวณราคาสุทธิหลังจากส่วนลด
     * @param item 
     * @return The price after applying the discount.
     */
    double calculateDiscountedPrice(CartItem item);
}