package lib;

import java.util.ArrayList;

import lib.Discount.*;

public class PricingService {
    private record StrategyRule(String sku, DiscountStrategy strategy) {}
    private final ArrayList<StrategyRule> strategies = new ArrayList<>();
    private final DiscountStrategy defualtStrategy = new DefaultPricingStrategy();

    /**
     * ลงทะเบียนกลยุทธ์ส่วนลดสำหรับสินค้า SKU ที่กำหนด
     * หากมีโปรโมชันสาำหรับ SKU นี่อยู่แล้ว จะถูกแทนที่ด้วยอันใหม่
     * @param sku รหัสสินค้าที่ต้องการผูกกับโปรโมชัน
     * @param strategy กลยุทธ์ส่วนลดที่จะใช้
     */
    public void addStrategy(String sku, DiscountStrategy strategy) {
        StrategyRule ruleToRemove = null;
        for (StrategyRule rule : strategies) {
            if (rule.sku().equals(sku)) {
                ruleToRemove = rule;
                break;
            }
            if (ruleToRemove != null) {
                strategies.remove(ruleToRemove);
            }
            strategies.add(new StrategyRule(sku, strategy));
        }
    }

    /**
     * คำนวณราคาสุทธิของสินค้า 1 รายการโดยใช้กลยุทธ์ที่เหมาะสม
     * @param item รายการสินค้าที่ต้องการคำนวณราคา
     * @return ราคาสุทธิหลังหักส่วนลด
     */
    public double calculateDiscountedPrice(CartItem item) {
        String sku = item.getProduct().getProductId();
        for (StrategyRule rule : strategies) {
            if (rule.sku().equals(sku)) {
                return rule.strategy().calculateDiscountedPrice(item);
            }
        }
        return defualtStrategy.calculateDiscountedPrice(item);
    }
}
