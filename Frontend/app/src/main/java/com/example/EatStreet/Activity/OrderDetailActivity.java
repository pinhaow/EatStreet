package com.example.EatStreet.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.EatStreet.IdHolder;
import com.example.EatStreet.Logic.LoginLogic;
import com.example.EatStreet.Logic.OrderDetailLogic;
import com.example.EatStreet.Logic.ServerRequest;
import com.example.EatStreet.R;
import com.example.EatStreet.appController;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class OrderDetailActivity extends AppCompatActivity {
    private final OrderDetailLogic myVolleyListener = new OrderDetailLogic(this, appController.myServerRequest);

    public ListView listView;

    private String store_name, pick_up, terminal, id, driver_contact, user_contact, arrive_time, driver_name, ws_url;
    private TextView store_name_, pick_up_, terminal_, id_, driver_contact_, user_contact_, driver_name_, t1_, e2_;

    private Long orderlistId;
    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        listView = (ListView) findViewById(R.id.order_detail_page);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {
                orderlistId = bundle.getLong("orderlist_id");
                myVolleyListener.getItems(orderlistId);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            store_name = bundle.getString("store_name");
            pick_up = bundle.getString("pick_up");
            terminal = bundle.getString("terminal");
            id = "" + bundle.getLong("id");
            driver_contact = bundle.getString("driver_contact");
            user_contact = bundle.getString("user_contact");
            arrive_time = bundle.getString("arrive_time");
            driver_name = bundle.getString("driver_name");
        }

        store_name_ = findViewById(R.id.order_resturant_name);
        pick_up_ = findViewById(R.id.order_pick_up_address);
        terminal_ = findViewById(R.id.order_deliver_address);
        id_ = findViewById(R.id.order_id);
        driver_contact_ = findViewById(R.id.order_driver_phonenumber);
        user_contact_ = findViewById(R.id.order_user_phonenumber);
        driver_name_ = findViewById(R.id.order_driver_name);
        t1_ = findViewById(R.id.tx1);
        e2_ = findViewById(R.id.et2);

        store_name_.setText("Restaurant Name: "+ store_name);
        pick_up_.setText("Pick Up Address: " + pick_up);
        terminal_.setText("Deliver To: "+terminal);
        id_.setText("Order Id: "+id);
        driver_contact_.setText("Driver Contact: "+driver_contact);
        user_contact_.setText("User Contact: "+user_contact);
        driver_name_.setText("Driver Name:"+driver_name);

        if(LoginLogic.hold.getUserType() == IdHolder.UserType.User){
            ws_url = ServerRequest.WS_HOST + user_contact;
        }else{
            ws_url = ServerRequest.WS_HOST + driver_contact;
        }



    }

    public void takeOrder(View view){
        if (LoginLogic.hold.getUserType() != IdHolder.UserType.Driver){
            Toast.makeText(OrderDetailActivity.this, "Only Driver can take order", Toast.LENGTH_LONG).show();
            return;
        }
        myVolleyListener.takeOrder(orderlistId);
        /*
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "/orderlist/take/" + orderlistId + "/" + LoginLogic.hold.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(OrderDetailActivity.this, "successful taking the order", Toast.LENGTH_SHORT).show();
                } else if (response.equals("fail")) {
                    Toast.makeText(OrderDetailActivity.this, "fail taking the order", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderDetailActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String ,String> data = new HashMap<>();
                data.put("id", id);
                data.put("driver_name", driver_name);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

         */
    }

    public void onSend(View view) {
        String msg = String.format("@%s %s",
                ((TextView) findViewById(R.id.et1)).getText().toString(),
                ((TextView) findViewById(R.id.et2)).getText().toString());
        try {
            cc.send(msg);
        } catch (Exception e) {
            Log.d("ExceptionSendMessage:", e.getMessage().toString());
        }
    }

    public void onConnect(View view) {

        Draft[] drafts = {
                new Draft_6455()
        };

        /**
         * If running this on an android device, make sure it is on the same network as your
         * computer, and change the ip address to that of your computer.
         * If running on the emulator, you can use localhost.
         */

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(ws_url), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s = t1_.getText().toString();
                    t1_.setText(s + " Server:" + message);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();
    }

    public void onSelUser(View view) {
        ((TextView) findViewById(R.id.et1)).setText(user_contact);
    }

    public void onSelDriver(View view) {
        ((TextView) findViewById(R.id.et1)).setText(driver_contact);
    }
}