package com.example.EatStreet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;
import com.example.EatStreet.Logic.LoginLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private final LoginLogic myVolleyListener = new LoginLogic(this, appController.myServerRequest);

    private MaterialButton login_bt, signup_bt;
    private String username, password;
    private TextView username_, password_;

    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //login page
        username_ = (TextView) findViewById(R.id.username);
        password_ = (TextView) findViewById(R.id.password);
        login_bt= (MaterialButton) findViewById(R.id.loginbtn);
        signup_bt=(MaterialButton) findViewById(R.id.signup_bt);

    }


    //real login function, need to change login1 to login
    public void login1(View view){
        username=username_.getText().toString().trim();
        password=password_.getText().toString().trim();
        if(!username.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("fail")) {
                        Toast.makeText(LoginActivity.this, "login failed, please check username or email", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                protected Map<String,String> getParams() throws AuthFailureError{
                    Map<String ,String> data = new HashMap<>();
                    data.put("name", username);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(this, "username or password cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view){
        username=username_.getText().toString().trim();
        password=password_.getText().toString().trim();
        if(!username.equals("") && !password.equals("")){
            try {
                myVolleyListener.loginUser(username, password);
                /*
                Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                startActivity(intent);
                finish();
                 */
            } catch (JSONException e) {
                e.printStackTrace();
            }
       }
        else{
            Toast.makeText(this, "username or password cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    //need to change mainactivity to registeractivity
    public void register(View view){
        Intent intent = new Intent(LoginActivity.this, AddUserActivity.class);
        startActivity(intent);
    }
}