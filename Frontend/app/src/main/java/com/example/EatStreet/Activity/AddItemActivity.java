package com.example.EatStreet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.EatStreet.Logic.AddItemPageLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {
    private final AddItemPageLogic myVolleyListener = new AddItemPageLogic(this, appController.myServerRequest);

    private MaterialButton submit_bt, add_item_image_bt;

    String name, quantity, price;
    Image image;

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }

    public void submit(View view){
        try {
            myVolleyListener.save();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
        name = findViewById(R.id.add_user_name).toString().trim();
        quantity = findViewById(R.id.add_user_password).toString().trim();
        price = findViewById(R.id.add_user_realname).toString().trim();

        if(!name.equals("") && !quantity.equals("") && !price.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(AddItemActivity.this, StoreOwnerActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("fail")) {
                        Toast.makeText(AddItemActivity.this, "Add item failed, please check if item already existed", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddItemActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("quantity", quantity);
                    data.put("price", price);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(AddItemActivity.this, "item information cannot be empty", Toast.LENGTH_SHORT).show();
        }

         */
    }
}