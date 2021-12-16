package com.example.EatStreet.Logic;

import android.content.Intent;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.EatStreet.Activity.LoginActivity;
import com.example.EatStreet.Activity.MainPageActivity;
import com.example.EatStreet.IdHolder;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginLogic implements MyVolleyListener {

    public static IdHolder hold;
    LoginActivity r;
    MyServerRequest serverRequest;
    Integer id;

    public LoginLogic(LoginActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void loginUser(String username, String password) throws JSONException {
        String url = "/user/login";
        JSONObject newUserObj = new JSONObject();
        newUserObj.put("name", username);
        newUserObj.put("password", password);

        serverRequest.sendToServer(url, newUserObj, "POST");
    }

    @Override
    public void onSuccess(String res) {
        try {
            JSONObject resJson = new JSONObject(res);
            if (resJson.has("id")) {
                /*
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(res);
                String getID = m.replaceAll("").trim();
                id = Integer.valueOf(getID).intValue();
                hold = new IdHolder(id);
                 */
                id = resJson.getInt("id");
                hold = new IdHolder(id);
                Toast.makeText(r.getApplicationContext(), "Welcome to EatStreet!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(r.getApplicationContext(), MainPageActivity.class);
                r.startActivity(intent);
                r.finish();
            } else {
                Toast.makeText(r.getApplicationContext(), "Please check your username or password and try again!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(r.getApplicationContext(), LoginActivity.class);
                r.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

    }

    @Override
    public void onError(String errorMessage) {
        if (errorMessage != null)
            Toast.makeText(r.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(r.getApplicationContext(), "Please check your username or password and try again!", Toast.LENGTH_SHORT).show();

        Toast.makeText(r.getApplicationContext(), "Error with request, please try again", Toast.LENGTH_SHORT).show();
    }
}