package com.main.store.ApiUtilites;

import com.main.store.models.ProductsResponse;
import com.main.store.models.SignUpParams;
import com.main.store.models.SignUpResponse;
import com.main.store.models.cart.CardResponse;
import com.main.store.models.cart.CartParams;
import com.main.store.models.category.Category;
import com.main.store.models.signIn.SignInParams;
import com.main.store.models.signIn.SignInResponse;
import com.main.store.models.user.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {

    @POST("/users")
    Call<SignUpResponse> signupResponse(@Body SignUpParams signUpParams);

    @POST("/auth/login")
    Call<SignInResponse> signinResponse(@Body SignInParams signInParams);

    @POST("/users?limit=1")
    Call<CardResponse> getCats(@Body CartParams cartParams);

    @GET("/products")
    Call<List<ProductsResponse>> getAllProducts();

    @GET("products/category/electronics")
    Call<List<ProductsResponse>> getElectorics();

    @GET("products/category/jewelery")
    Call<List<ProductsResponse>> getJewelery();

    @GET("products/category/men's clothing")
    Call<List<ProductsResponse>> getMen();

    @GET("products/category/women's clothing")
    Call<List<ProductsResponse>> getWomen();

    @GET("/users?limit=1")
    Call<UserResponse> getUser();
}
