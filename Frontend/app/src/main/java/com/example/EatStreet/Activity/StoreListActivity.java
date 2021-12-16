package com.example.EatStreet.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.EatStreet.Logic.StoreListLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.json.JSONException;

public class StoreListActivity extends AppCompatActivity {
    private final StoreListLogic myVolleyListener = new StoreListLogic(this, appController.myServerRequest);

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        listView = (ListView) findViewById(R.id.store_list);

        try {
            myVolleyListener.getStores();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}