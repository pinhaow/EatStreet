package com.example.EatStreet.Logic;

import android.content.Intent;
import android.widget.Toast;

import com.example.EatStreet.Activity.AddUserActivity;
import com.example.EatStreet.Activity.LoginActivity;
import com.example.EatStreet.Activity.MainPageActivity;
import com.example.EatStreet.IdHolder;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUserLogic implements MyVolleyListener {

    public static IdHolder hold;
    AddUserActivity r;
    MyServerRequest serverRequest;
    Integer id;

    public AddUserLogic(AddUserActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void addUser(String name, String password, String realname, String email, String address, String mobilephone) throws JSONException {
        String url = "/user";
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("name", name);
        newUserObj.put("password", password);
        newUserObj.put("realname", realname);
        newUserObj.put("email", email);
        newUserObj.put("address", address);
        newUserObj.put("mobilephone", mobilephone);

        serverRequest.sendToServer(url, newUserObj, "POST");
    }

    @Override
    public void onSuccess(String res) {
        if (res.contains("id")) {
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(res);
            String getID = m.replaceAll("").trim();
            id = Integer.valueOf(getID).intValue();
            hold = new IdHolder(id);
            Toast.makeText(r.getApplicationContext(), "Welcome to EatStreet!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(r.getApplicationContext(), LoginActivity.class);
            r.startActivity(intent);
            r.finish();
        } else {
            Toast.makeText(r.getApplicationContext(), "Please check your username or password and try again!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(r.getApplicationContext(), LoginActivity.class);
            r.startActivity(intent);
        }
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

    }

    @Override
    public void onError(String errorMessage) {
        if (errorMessage != null)
            Toast.makeText(r.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();

        Toast.makeText(r.getApplicationContext(), "Cannot register the account, please try again", Toast.LENGTH_SHORT).show();
    }
}