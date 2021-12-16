package com.example.EatStreet.Logic;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.EatStreet.Activity.StorePageActivity;
import com.example.EatStreet.Adapter.StorePageAdapter;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StorePageLogic implements MyVolleyListener {

    StorePageActivity r;
    MyServerRequest serverRequest;

    public StorePageLogic(StorePageActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void getItems(long storeId) throws JSONException {
        String url = "/item/all/" + storeId;
        JSONArray jsonArray = new JSONArray();

        serverRequest.sendToServer(url, jsonArray, "GET");
    }

    @Override
    public void onSuccess(String res) {
        Toast.makeText(r.getApplicationContext(), "Added to cart!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

        StorePageAdapter orderListAdapter = new StorePageAdapter(r.getApplicationContext(), arr);

        r.listView.setAdapter(orderListAdapter);

        r.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
                JSONObject jsonObject = (JSONObject) adapter.getItemAtPosition(position);
                try {
                    JSONObject jsonCart = new JSONObject();
                    jsonCart.put("userid", LoginLogic.hold.getId());
                    jsonCart.put("itemid", jsonObject.getLong("id"));
                    jsonCart.put("itemname", jsonObject.getString("name"));
                    jsonCart.put("itemprice", jsonObject.getDouble("price"));
                    jsonCart.put("quantity", 1);
                    serverRequest.sendToServer("/cart", jsonCart, "POST");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Intent intent = new Intent(r.getApplicationContext(), ShoppingCartPageActivity.class);
                //r.startActivity(intent);
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