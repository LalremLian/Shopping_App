package com.main.store.models;

import com.main.store.models.signUp.Addresses;
import com.main.store.models.signUp.Name;

import java.util.ArrayList;

public class SignUpParams {
    String email;
    String password;
    Name name;
    Addresses address;

    public SignUpParams(String email, String password, Name name, Addresses address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SignUpParams{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name +
                ", address=" + address +
                '}';
    }
}
