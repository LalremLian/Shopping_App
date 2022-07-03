package com.main.store.models;

import com.main.store.models.signUp.Addresses;
import com.main.store.models.signUp.Geolocation;

import java.util.ArrayList;

public class SignUpResponse {
    String _id;
    String id;
    String email;
    String password;

    public SignUpResponse(String _id, String id, String email, String password) {
        this._id = _id;
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "_id='" + _id + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
