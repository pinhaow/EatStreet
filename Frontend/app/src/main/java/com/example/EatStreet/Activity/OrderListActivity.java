package com.example.EatStreet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.example.EatStreet.Adapter.OrderListAdapter;
import com.example.EatStreet.IdHolder;
import com.example.EatStreet.Logic.LoginLogic;
import com.example.EatStreet.Logic.OrderListPageLogic;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.json.JSONException;

public class OrderListActivity extends AppCompatActivity {
    private final OrderListPageLogic myVolleyListener = new OrderListPageLogic(this, appController.myServerRequest);

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        listView = (ListView) findViewById(R.id.order_list);

        try {
            if (LoginLogic.hold.getUserType() == IdHolder.UserType.Driver)
                myVolleyListener.getItems(0);
            else
                myVolleyListener.getItems(LoginLogic.hold.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        JsonArrayRequest get_orders_request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                JSONArray order_array = response;
//                try {
//                    for (int i = 0; i < order_array.length(); i++) {
//                        JSONObject order = order_array.getJSONObject(i);
//                        if (order.getString("condition").equals("1")) {
//                            id[count] = order.getString("id");
//                            store_name[count] = order.getString("store_name");
//                            pick_up[count] = order.getString("pick_up");
//                            terminal[count] = order.getString("terminal");
//                            driver_contact[count] = order.getString("driver_contact");
//                            driver_name[count] = order.getString("driver_contact");
//                            user_contact[count] = order.getString("user_contact");
//                            arrive_time[count] = order.getString("arrive_time");
//                        }
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(OrderList.this, error.getMessage(),Toast.LENGTH_LONG);
//            }
//        });
//        requestqueue.add(get_orders_request);

/*
        listView = (ListView)findViewById(R.id.order_list);
        OrderListAdapter orderListAdapter = new OrderListAdapter(getApplicationContext(), store_name, pick_up, terminal, id);
        listView.setAdapter(orderListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
                Intent intent = new Intent(OrderListActivity.this, OrderDetail.class);
                //based on item add info to intent
                intent.putExtra("store_name", "0");
                intent.putExtra("pick_up", "1");
                intent.putExtra("terminal", "2");
                intent.putExtra("id", "3");
                intent.putExtra("user_contact", "4");
                intent.putExtra("driver_contact", "5");
                intent.putExtra("driver_name", "6");
                intent.putExtra("arrive_time", "7");
                startActivity(intent);
            }
        });
*/
    }
}