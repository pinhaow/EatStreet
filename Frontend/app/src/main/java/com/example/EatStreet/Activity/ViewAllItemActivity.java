package com.example.EatStreet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.EatStreet.Logic.LoginLogic;
import com.example.EatStreet.Logic.ViewAllItemPageLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.json.JSONException;

public class ViewAllItemActivity extends AppCompatActivity {
    private final ViewAllItemPageLogic myVolleyListener = new ViewAllItemPageLogic(this, appController.myServerRequest);
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_item);
        listView = (ListView) findViewById(R.id.view_all_item_page);
        try {
            myVolleyListener.getItems(LoginLogic.hold.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addItem(View view){
        Intent intent = new Intent(ViewAllItemActivity.this, AddItemActivity.class);
        startActivity(intent);
    }
}