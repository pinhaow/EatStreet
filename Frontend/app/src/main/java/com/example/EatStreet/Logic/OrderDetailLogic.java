package com.example.EatStreet.Logic;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.EatStreet.Activity.OrderDetailActivity;
import com.example.EatStreet.Adapter.OrderDetailAdapter;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderDetailLogic implements MyVolleyListener {

    OrderDetailActivity r;
    MyServerRequest serverRequest;

    public OrderDetailLogic(OrderDetailActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void getItems(long orderlistId) throws JSONException {
        String url = "/orderdetail/all/" + orderlistId;
        JSONArray jsonArray = new JSONArray();

        serverRequest.sendToServer(url, jsonArray, "GET");
    }

    @Override
    public void onSuccess(String res) {
        Toast.makeText(r.getApplicationContext(), "successful taking the order", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

        OrderDetailAdapter orderListAdapter = new OrderDetailAdapter(r.getApplicationContext(), arr);

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

    public void takeOrder(Long orderlistId){

        String url = "/orderlist/take/" + orderlistId + "/" + LoginLogic.hold.getId();
        JSONObject jsonObject = new JSONObject();

        try {
            serverRequest.sendToServer(url, jsonObject, "POST");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}