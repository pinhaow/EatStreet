package com.example.EatStreet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.EatStreet.Logic.LoginLogic;
import com.example.EatStreet.Logic.ShoppingCartPageLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.json.JSONException;

public class ShoppingCartPageActivity extends AppCompatActivity {
    private final ShoppingCartPageLogic myVolleyListener = new ShoppingCartPageLogic(this, appController.myServerRequest);

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart_page);

        listView = (ListView) findViewById(R.id.shoppingcart_page);

        try {
            myVolleyListener.getItems(LoginLogic.hold.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void confirm(View view) {
        myVolleyListener.confirm();
    }

    public void history(View view) {
        Intent intent = new Intent(getApplicationContext(), OrderListActivity.class);
        startActivity(intent);
    }
}