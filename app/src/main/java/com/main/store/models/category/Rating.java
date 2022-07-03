package com.main.store.models.category;

public class Rating {
    String rate;
    String count;

    public Rating(String rate, String count) {
        this.rate = rate;
        this.count = count;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rate='" + rate + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
