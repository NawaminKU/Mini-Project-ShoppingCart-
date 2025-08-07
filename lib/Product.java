package lib;

/**
 * ADT ที่ไม่เปลี่ยนรูป (Immutable) สำหรับเก็บข้อมูลสินค้า
 * คลาสที่เป็น final เพื่อป้องกันการสืบทอดและรับประกัน Immutability
 */

public final class Product {
    private final String productId;
    private final String productName;
    private final double price;

    // Rep Invariant (RI):
    // - productId and productName are not null or blank.
    // - price >= 0.
    // -----------------------------------------------------
    // =====================================================
    // Abstrction Function (AF):
    // - AF(productId, productName, price) = A product with the given ID, name and price

    /**
     * ตรวจสอบว่า Rap Invariant เป็นจริงหรือไม่
     */
    private void checkRep() {
        if (productId == null || productId.isBlank()) {
            throw new RuntimeException("RI violated: productId is null/blank");
        }
        if (productName == null || productName.isBlank()) {
            throw new RuntimeException("RI violated: producName is null/blank");
        }
        if (price <= 0) {
            throw new RuntimeException("RI violated: price is less then 0");
        }
    }

    /**
     * สร้าง constructor ของ Product 
     * @param productId รหัสสินค้า ห้ามเป็นค่าว่าง
     * @param productName ชื่อสินค้า ห้ามเป็นค่าว่าง
     * @param price ราคา ห้ามน้อยกว่าหรือเท่ากับ 0
     */
    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        checkRep();
    }

    public String getProductId() {return productId;}
    public String getProductName() {return productName;}
    public double getPrice() {return price;}

    /**
     * เปรียบเทียบ Product สองชั้นโดยใช้ productId
     * @param obj อ็อบเจกต์ที่ต้องการเปรียบเทียบ
     * @return true หาก productId เหมือน
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Product product = (Product) obj;
        return productId.equals(product.productId);
    }
}
