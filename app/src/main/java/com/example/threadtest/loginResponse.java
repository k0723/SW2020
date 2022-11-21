package com.example.threadtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.Cookie;
import okhttp3.CookieJar;

public class loginResponse {
    @Expose
    @SerializedName("success")
    public boolean success ;

    @SerializedName("id")
    public String id;

    @SerializedName("password")
    public String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public loginResponse(boolean success, String id, String password) {
        this.success = success;
        this.password = password;
        this.id = id;
    }
}
