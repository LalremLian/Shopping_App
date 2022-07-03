package com.main.store.models.cart;

public class CartProducts {
    String productId;
    String quantity;

    public CartProducts(String productId, String quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartProducts{" +
                "productId='" + productId + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
