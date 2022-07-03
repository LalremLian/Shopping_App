package com.main.store.models.signIn;

public class SignInResponse {
    String token;

    public SignInResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SignInResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
