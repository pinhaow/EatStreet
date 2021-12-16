package com.example.EatStreet.Logic;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.EatStreet.Activity.OrderDetailActivity;
import com.example.EatStreet.Activity.OrderListActivity;
import com.example.EatStreet.Adapter.OrderListAdapter;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderListPageLogic implements MyVolleyListener {

    OrderListActivity r;
    MyServerRequest serverRequest;

    public OrderListPageLogic(OrderListActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void getItems(long orderlistId) throws JSONException {
        String url = "/orderlist/all/" + orderlistId;
        JSONArray jsonArray = new JSONArray();

        serverRequest.sendToServer(url, jsonArray, "GET");
    }

    @Override
    public void onSuccess(String res) {
        Toast.makeText(r.getApplicationContext(), "Deleted from cart!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

        OrderListAdapter orderListAdapter = new OrderListAdapter(r.getApplicationContext(), arr);

        r.listView.setAdapter(orderListAdapter);

        r.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
                Intent intent = new Intent(r.getApplicationContext(), OrderDetailActivity.class);
                //based on item add info to intent
                JSONObject jsonObject = (JSONObject) adapter.getItemAtPosition(position);
                intent.putExtra("orderlist_id", adapter.getItemIdAtPosition(position));

                //based on item add info to intent
                try {
                    intent.putExtra("store_name", jsonObject.getString("storename"));
                    intent.putExtra("pick_up", jsonObject.getString("pickup"));
                    intent.putExtra("terminal", jsonObject.getString("terminal"));
                    intent.putExtra("id", jsonObject.getLong("id"));
                    intent.putExtra("user_contact", jsonObject.getString("usercontact"));
                    intent.putExtra("driver_name", jsonObject.getString("drivername"));
                    intent.putExtra("driver_contact", jsonObject.getString("drivercontact"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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