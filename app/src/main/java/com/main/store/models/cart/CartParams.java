package com.main.store.models.cart;

import java.util.ArrayList;

public class CartParams {
    String userId;
    ArrayList<CartProducts> products;

    public CartParams(String userId, ArrayList<CartProducts> products) {
        this.userId = userId;
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<CartProducts> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<CartProducts> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartParams{" +
                "userId='" + userId + '\'' +
                ", products=" + products +
                '}';
    }
}
