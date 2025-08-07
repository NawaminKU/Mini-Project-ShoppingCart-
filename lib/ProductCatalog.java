package lib;

import java.util.ArrayList;

/**
 * คลาสทำหน้าที่เป็นแคตล็อคสินค้า (Respository)
 */

public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();

    // RI: product list is not null, contains no null elements, and no duplicate products.
    // AF: AF(products) = A catalog of all avilable products.

    private void checkRep() {
    if (products == null) {
        throw new RuntimeException("RI violated: products is null.");
    }
    for (int i = 0; i < products.size(); i++) {
        for (int j = i + 1; j < products.size(); j++) {
            if (products.get(i).equals(products.get(j))) {
                throw new RuntimeException("RI violated: duplicated.");
            }
        }
    }
}

    public ProductCatalog() {
        checkRep();
    }

    /**
     * เพิ่มสินค้าใหม่เข้าสู่แคดตาล็อค
     * @param product สินค้าที่ต้องการเพิ่ม
     */
    public void addProduct(Product product) {
        if (product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
    }

    /**
     * ค้นหาสินค้าจากรหัสสินค้า
     * @param productId รหัสสินค้าที่ต้องการค้นหา
     * @return อ็อบเจกต์ Product หากพบ, หรือ null หากไม่เข้าเงื่อนไขจะ return p;
     */
    public Product findById(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }
}
