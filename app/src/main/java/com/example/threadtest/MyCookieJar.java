package com.example.threadtest;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class MyCookieJar implements CookieJar {

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    private List<Cookie> cookies;

    @NonNull
    @Override
    public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
        if (cookies != null)
            return cookies;
        return new ArrayList<Cookie>();
    }

    @Override
    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
        this.cookies = list;
        Log.d("cookie",list.toString());
    }
}
