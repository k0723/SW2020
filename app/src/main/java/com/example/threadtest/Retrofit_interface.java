package com.example.threadtest;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_interface {
    final String Base_URL = "http://158.247.235.236:1200";

    @POST("posts/{hour}")
    Call<data_model> sethour(
            @Path("hour") String hour);

    @GET("posts/{hour}")
    Call<data_model> gethour(
            @Path("hour") String hour);


}
