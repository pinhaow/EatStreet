package com.example.EatStreet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.EatStreet.IdHolder;
import com.example.EatStreet.Logic.LoginLogic;
import com.example.EatStreet.Logic.OrderListPageLogic;
import com.example.EatStreet.Logic.StoreOwnerPageLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.json.JSONException;
import org.json.JSONObject;

public class StoreOwnerActivity extends AppCompatActivity {
    private final StoreOwnerPageLogic myVolleyListener = new StoreOwnerPageLogic(this, appController.myServerRequest);
    String name = "ABC";
    String location = "Lincoln Way";
    String contact = "1111111111";
    TextView name_, location_, contact_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner);

        try {
            myVolleyListener.getInfo(LoginLogic.hold.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
/*
        name_ = findViewById(R.id.store_owner_name);
        location_ = findViewById(R.id.store_owner_location);
        contact_ = findViewById(R.id.store_owner_phone);

        name_.setText("Restaurant Name: " + name);
        location_.setText("Restaurant Location: " + location);
        contact_.setText("Contact Phone Number: " + contact);

 */
    }

    public void viewAllItem(View view) {
        Intent intent = new Intent(StoreOwnerActivity.this, ViewAllItemActivity.class);
        startActivity(intent);
    }

    public void update(View view) {
        myVolleyListener.update();
    }
}