package com.example.EatStreet.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.EatStreet.Logic.AddUserLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.json.JSONException;

public class AddUserActivity extends AppCompatActivity {
    private final AddUserLogic myVolleyListener = new AddUserLogic(this, appController.myServerRequest);

    TextView  name_, password_, realname_, email_, address_, mobilephone_;
    String name, password, realname, email, address, mobilephone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        name_ = (TextView) findViewById(R.id.add_user_name);
        password_ = (TextView) findViewById(R.id.add_user_password);
        realname_ = (TextView) findViewById(R.id.add_user_realname);
        email_ = (TextView) findViewById(R.id.add_user_email);
        address_ = (TextView) findViewById(R.id.add_user_address);
        mobilephone_ = (TextView) findViewById(R.id.add_user_mobilephone);
    }

    public void submit(View view) {
        name = name_.getText().toString().trim();
        password = password_.getText().toString().trim();
        realname = realname_.getText().toString().trim();
        email = email_.getText().toString().trim();
        address = address_.getText().toString().trim();
        mobilephone = mobilephone_.getText().toString().trim();

        if (name.equals("") || password.equals("") || realname.equals("") || email.equals("") || address.equals("") || mobilephone.equals("")) {
            Toast.makeText(AddUserActivity.this, "User information cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            try {
                myVolleyListener.addUser(name, password, realname, email, address, mobilephone);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}