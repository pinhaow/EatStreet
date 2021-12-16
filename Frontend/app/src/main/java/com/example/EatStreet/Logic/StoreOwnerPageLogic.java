package com.example.EatStreet.Logic;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EatStreet.Activity.StoreOwnerActivity;
import com.example.EatStreet.Activity.ViewAllItemActivity;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;
import com.example.EatStreet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StoreOwnerPageLogic implements MyVolleyListener {

    StoreOwnerActivity r;
    MyServerRequest serverRequest;

    public StoreOwnerPageLogic(StoreOwnerActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void getInfo(long userId) throws JSONException {
        String url = "/store/mine/" + userId;
        JSONObject jsonObject = new JSONObject();

        serverRequest.sendToServer(url, jsonObject, "GET");
    }

    @Override
    public void onSuccess(String res) {
        try {
            JSONObject jsonObject = new JSONObject(res);

            getTextViewById(R.id.store_owner_name_tx).setText(jsonObject.getString("name"));;
            getTextViewById(R.id.store_owner_location_tx).setText(jsonObject.getString("address"));
            getTextViewById(R.id.store_owner_phone_tx).setText(jsonObject.getString("phone"));

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(r.getApplicationContext(), "no store!", Toast.LENGTH_SHORT).show();
        }
    }

    private TextView getTextViewById(int id) {
        return ((TextView) r.findViewById(id));
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

    public void update() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ownerid", LoginLogic.hold.getId());
            jsonObject.put("name", getTextViewById(R.id.store_owner_name_tx).getText().toString().trim());
            jsonObject.put("address", getTextViewById(R.id.store_owner_location_tx).getText().toString().trim());
            jsonObject.put("phone", getTextViewById(R.id.store_owner_phone_tx).getText().toString().trim());
            serverRequest.sendToServer("/store", jsonObject, "PUT");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
            Intent intent = new Intent(r.getApplicationContext(), ViewAllItemActivity.class);
            r.startActivity(intent);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }
}