package com.example.jahanveenarang.mjsongs;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {


    @GET("search?term=Michael+jackson")
    Call<MainClassList> getdetail();


}
