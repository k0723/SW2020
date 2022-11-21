package com.example.threadtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class logout {
    @Expose
    @SerializedName("success")
    public boolean success ;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
