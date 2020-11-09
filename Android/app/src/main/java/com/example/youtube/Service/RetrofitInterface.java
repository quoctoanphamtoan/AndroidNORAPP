package com.example.youtube.Service;

import com.example.youtube.Resualt.LoginResult;
import com.example.youtube.Resualt.UserResual;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @GET("/list")
    Call<List<UserResual>> executeusers();

    @POST("/Register")
    Call<Void> executeSignup (@Body HashMap<String, String> map);
    @PUT("/update/{email}")
    Call<Void> disable (@Path("email") String email );

}
