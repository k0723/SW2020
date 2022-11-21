package com.example.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.CookieManager;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_login extends AppCompatActivity {

    Button bt_login,bt_signup;
    EditText edit_id,edit_password;
    public String cookie;
    public String userId;
    MyCookieJar cookieJar = new MyCookieJar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        bt_login = findViewById(R.id.bt_login);
        bt_signup = findViewById(R.id.bt_signUp);
        edit_id = findViewById(R.id.edit_id);
        edit_password = findViewById(R.id.edit_password);
        MyCookieJar cookieJar = new MyCookieJar();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_main = new Intent(activity_login.this, MainActivity.class);
                userId = edit_id.getText().toString();
                Log.d("id", userId);
                Log.d("password",edit_password.getText().toString());
                    retrofit_client.getApiService().getsignup(edit_id.getText().toString()
                                    ,edit_password.getText().toString())
                        .enqueue(new Callback<loginResponse>(){
                            @Override
                            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                                if(response.isSuccessful()&& response.body() != null)
                                {
                                    loginResponse result = response.body();
                                    cookie = response.headers().get("Set-Cookie");
                                    Log.d("성공", cookie);
                                    Log.d("1", String.valueOf(response.code()));

                                    // 화면에 로그인된 유저아이디 보여줄 때 userId

                                }
                                Log.d("성공여부",String.valueOf(response.isSuccessful()));
                            }

                            @Override
                            public void onFailure(Call<loginResponse> call, Throwable t) {
                                Log.d("접근","실패");
                            }
                        });
// searc
                        intent_main.putExtra("id",userId);
                        startActivity(intent_main);

            }
        });

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_signup = new Intent(activity_login.this, actvity_signup.class);
                startActivity(intent_signup);
            }
        });
    }
}