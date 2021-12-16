package com.example.EatStreet.Logic;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.EatStreet.Activity.StoreListActivity;
import com.example.EatStreet.Adapter.StoreListAdapter;
import com.example.EatStreet.Activity.StorePageActivity;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StoreListLogic implements MyVolleyListener {

    StoreListActivity r;
    MyServerRequest serverRequest;

    public StoreListLogic(StoreListActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void addStore(String name, String password, String realname, String email, String address, String mobilephone) throws JSONException {
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

    public void getStores() throws JSONException {
        String url = "/store/all";
        JSONArray jsonArray = new JSONArray();

        serverRequest.sendToServer(url, jsonArray, "GET");
    }

    @Override
    public void onSuccess(String res) {
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

        StoreListAdapter orderListAdapter = new StoreListAdapter(r.getApplicationContext(), arr);

        r.listView.setAdapter(orderListAdapter);

        r.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
                Intent intent = new Intent(r.getApplicationContext(), StorePageActivity.class);
                //based on item add info to intent
                intent.putExtra("store_id", adapter.getItemIdAtPosition(position));
                r.startActivity(intent);
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