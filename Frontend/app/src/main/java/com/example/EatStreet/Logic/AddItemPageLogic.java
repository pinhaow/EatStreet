package com.example.EatStreet.Logic;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.EatStreet.Activity.AddItemActivity;
import com.example.EatStreet.Activity.StoreOwnerActivity;
import com.example.EatStreet.Activity.ViewAllItemActivity;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;
import com.example.EatStreet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddItemPageLogic implements MyVolleyListener {

    AddItemActivity r;
    MyServerRequest serverRequest;

    public AddItemPageLogic(AddItemActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void save() throws JSONException {
        String url = "/item/" + LoginLogic.hold.getId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("storeid", 0);
        jsonObject.put("name", getTextById(R.id.add_item_name));
        jsonObject.put("quantity", Integer.parseInt(getTextById(R.id.add_item_quantity)));
        jsonObject.put("price", Double.parseDouble(getTextById(R.id.add_item_price)));

        serverRequest.sendToServer(url, jsonObject, "POST");
    }

    private String getTextById(int id){
        return ((TextView)r.findViewById(id)).getText().toString().trim();
    }

    @Override
    public void onSuccess(String res) {
        Intent intent = new Intent(r.getApplicationContext(), ViewAllItemActivity.class);
        r.startActivity(intent);
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