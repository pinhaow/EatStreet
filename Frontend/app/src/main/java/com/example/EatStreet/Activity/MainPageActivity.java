package com.example.EatStreet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.EatStreet.IdHolder;
import com.example.EatStreet.Logic.LoginLogic;
import com.example.EatStreet.Logic.ServerRequest;
import com.example.EatStreet.R;

public class MainPageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



    }

    public void Driver(View view){
        LoginLogic.hold.setUserType(IdHolder.UserType.Driver);
        Intent intent = new Intent(MainPageActivity.this, OrderListActivity.class);
        startActivity(intent);
    }

    public void StoreOwner(View view){
        LoginLogic.hold.setUserType(IdHolder.UserType.Owner);
        Intent intent = new Intent(MainPageActivity.this, StoreOwnerActivity.class);
        startActivity(intent);
    }

    public void User(View view){
        LoginLogic.hold.setUserType(IdHolder.UserType.User);
        Intent intent = new Intent(MainPageActivity.this, StoreListActivity.class);
        startActivity(intent);
    }

    public void Chat(View view) {
        Intent intent = new Intent(MainPageActivity.this, WebSocketActivity.class);
        startActivity(intent);
        //Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(ServerRequest.HOST_ADDRESS));
        //startActivity(launchBrowser);
    }
}