package com.main.store.models.signUp;

public class Addresses {
    Geolocation geolocation;

    public Addresses(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "geolocation=" + geolocation +
                '}';
    }
}
