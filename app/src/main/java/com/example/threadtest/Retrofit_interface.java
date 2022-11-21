package com.example.threadtest;

import com.google.gson.annotations.JsonAdapter;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_interface {
    final String Base_URL = "http://158.247.235.236:1200/";

    @POST("auth/signin")
    Call<SigninRequest> postsignin(
            @Body SigninRequest SigninRequest);

    @GET("auth/signup")
    Call<loginResponse> getsignup(
            @Query("id") String id, @Query("password") String password);


    @POST("alarm/insert")
    Call<alarmAdd> postHour(
            @Body  alarmAdd alarmAdd);

    @GET("alarm/search")
    Call<List<alarmsearch>> getAlarm(
    );


    @GET("auth/logout")
    Call<logout> getlogout(
            );

    @DELETE("auth/delete")
    Call<signout> deleteSign(
            @Query("id") String id);
}
