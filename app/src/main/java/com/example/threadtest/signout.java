package com.example.threadtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class signout {

    @Expose
    @SerializedName("success")
    public boolean success ;

    @SerializedName("id")
    private String id;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public signout(boolean success, String id) {
        this.success = success;
        this.id = id;
    }
}
