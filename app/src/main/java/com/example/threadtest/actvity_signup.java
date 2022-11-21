package com.example.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class actvity_signup extends AppCompatActivity {

    EditText edit_signup_id,edit_signup_name,edit_signup_password,edit_signup_deviceid,edit_signup_email;
    Button bt_signupset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        edit_signup_id = findViewById(R.id.edit_singup_id);
        edit_signup_name = findViewById(R.id.edit_signup_name);
        edit_signup_password = findViewById(R.id.edit_signup_password);
        edit_signup_deviceid = findViewById(R.id.edit_signup_deviceId);
        edit_signup_email = findViewById(R.id.edit_signup_email);
        bt_signupset = findViewById(R.id.bt_signUp_set);

        bt_signupset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SigninRequest signinRequest = new SigninRequest(
                        edit_signup_id.getText().toString(),
                        edit_signup_name.getText().toString(),
                        edit_signup_password.getText().toString(),
                        edit_signup_deviceid.getText().toString(),
                        edit_signup_email.getText().toString());

                retrofit_client.getApiService().postsignin(signinRequest)
                        .enqueue(new Callback<SigninRequest>(){
                            //콜백 받는 부분
                            @Override
                            public void onResponse(Call<SigninRequest> call, Response<SigninRequest> response) {
                                if(response.isSuccessful())
                                {
                                    SigninRequest result = response.body();
                                    Log.d("0","성공");
                                }

                                Log.d("status code", String.valueOf(response.code()));
                                String ToastSigninText = edit_signup_id.getText().toString();
                                Toast ToastSignin = Toast.makeText(actvity_signup.this,ToastSigninText+"님 회원가입 되었습니다.", Toast.LENGTH_SHORT);
                                ToastSignin.show();
                            }

                            @Override
                            public void onFailure(Call<SigninRequest> call, Throwable t) {
                                Log.d("1","실패");
                            }
                        });
            }
        });
    }
}