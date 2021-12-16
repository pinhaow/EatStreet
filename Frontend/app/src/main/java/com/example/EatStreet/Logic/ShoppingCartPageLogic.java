package com.example.EatStreet.Logic;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.EatStreet.Activity.OrderListActivity;
import com.example.EatStreet.Activity.ShoppingCartPageActivity;
import com.example.EatStreet.Adapter.ShoppingCartPageAdapter;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShoppingCartPageLogic implements MyVolleyListener {

    ShoppingCartPageActivity r;
    MyServerRequest serverRequest;

    public ShoppingCartPageLogic(ShoppingCartPageActivity r, MyServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void getItems(long userId) throws JSONException {
        String url = "/cart/all/" + userId;
        JSONArray jsonArray = new JSONArray();

        serverRequest.sendToServer(url, jsonArray, "GET");
    }

    public void confirm(){
        JSONObject jsonObject = new JSONObject();
        try {
            serverRequest.sendToServer("/cart/confirm/" + LoginLogic.hold.getId(), jsonObject, "POST");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
            Intent intent = new Intent(r.getApplicationContext(), OrderListActivity.class);
            r.startActivity(intent);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }

    }

    @Override
    public void onSuccess(String res) {
        Toast.makeText(r.getApplicationContext(), "Deleted from cart!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(JSONArray arr) throws JSONException {

        ShoppingCartPageAdapter orderListAdapter = new ShoppingCartPageAdapter(r.getApplicationContext(), arr);

        r.listView.setAdapter(orderListAdapter);

        r.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
                /*
                JSONObject jsonObject = (JSONObject) adapter.getItemAtPosition(position);
                try {
                    JSONObject jsonCart = new JSONObject();
                    jsonCart.put("userid", LoginLogic.hold.getId());
                    jsonCart.put("itemname", jsonObject.getString("name"));
                    jsonCart.put("itemprice", jsonObject.getDouble("price"));
                    jsonCart.put("quantity", 1);
                    serverRequest.sendToServer("/cart", jsonCart, "POST");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                 */

                JSONObject jsonCart = new JSONObject();
                try {
                    serverRequest.sendToServer("/cart/" + adapter.getItemIdAtPosition(position), jsonCart, "DELETE");
                    ((ShoppingCartPageAdapter)adapter.getAdapter()).removeAt(position);
                    //adapter.removeView(v);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onError(String errorMessage) {
        System.out.println(errorMessage);
        /*
        if (errorMessage != null)
            Toast.makeText(r.getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();

        Toast.makeText(r.getApplicationContext(), "Cannot register the account, please try again", Toast.LENGTH_SHORT).show();
         */
    }
}