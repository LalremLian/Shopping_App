package com.main.store.models.cart;

import java.util.ArrayList;

public class CardResponse {
    String id;
    String userId;
    ArrayList<CartProducts> products;

    public CardResponse(String id, String userId, ArrayList<CartProducts> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "CardResponse{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", products=" + products +
                '}';
    }
}
