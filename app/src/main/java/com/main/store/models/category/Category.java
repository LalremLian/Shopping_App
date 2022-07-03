package com.main.store.models.category;

import java.util.ArrayList;
import java.util.List;

public class Category {
    String id;
    String title;
    String price;
    String descriptionws;
    String category;
    String image;

    public Category(String id, String title, String price, String descriptionws, String category, String image, ArrayList<Rating> rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.descriptionws = descriptionws;
        this.category = category;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescriptionws() {
        return descriptionws;
    }

    public void setDescriptionws(String descriptionws) {
        this.descriptionws = descriptionws;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", descriptionws='" + descriptionws + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
