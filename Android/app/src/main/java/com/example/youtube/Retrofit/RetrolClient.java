package com.example.youtube.Retrofit;

import com.example.youtube.Service.RetrofitInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrolClient {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.56.1:3000/";

    public RetrolClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }
    public RetrofitInterface getservice(){
        return  retrofit.create(RetrofitInterface.class);
    }
}
