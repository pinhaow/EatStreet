package com.example.EatStreet.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.EatStreet.Logic.StorePageLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.json.JSONException;

public class StorePageActivity extends AppCompatActivity {
    private final StorePageLogic myVolleyListener = new StorePageLogic(this, appController.myServerRequest);

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        listView = (ListView) findViewById(R.id.store_page);

        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null)
                myVolleyListener.getItems(bundle.getLong("store_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showCart(View view) {
        Intent intent = new Intent(getApplicationContext(), ShoppingCartPageActivity.class);
        startActivity(intent);
    }
}