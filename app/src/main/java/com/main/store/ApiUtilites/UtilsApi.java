package com.main.store.ApiUtilites;

public class UtilsApi {

   //public static final String BASE_URL_API = "http://api.fakeshop-api.com"; //live
   public static final String BASE_URL_API = "https://fakestoreapi.com"; //live

   public static BaseApiService getOthersAPIService(){
      return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
   }
}
