package com.example.EatStreet.Logic;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.EatStreet.Activity.ViewAllItemActivity;
import com.example.EatStreet.Adapter.ViewAllItemPageAdapter;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;

public class ViewAllItemPageLogic implements MyVolleyListener {

    ViewAllItemActivity r;
    MyServerRequest serverRequest;

    public ViewAllItemPageLogic(ViewAllItemActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void getItems(long userId) throws JSONException {
        String url = "/store/items/" + userId;
        JSONArray jsonArray = new JSONArray();

        serverRequest.sendToServer(url, jsonArray, "GET");
    }

    @Override
    public void onSuccess(String res) {
        Toast.makeText(r.getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

        ViewAllItemPageAdapter orderListAdapter = new ViewAllItemPageAdapter(r.getApplicationContext(), arr);

        r.listView.setAdapter(orderListAdapter);

        r.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
            }
        });

    }

    @Override
    public void onError(String errorMessage) {
        if (errorMessage != null)
            Toast.makeText(r.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();

        Toast.makeText(r.getApplicationContext(), "Cannot register the account, please try again", Toast.LENGTH_SHORT).show();
    }
}