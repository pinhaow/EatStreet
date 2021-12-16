package com.example.EatStreet.Adapter;


import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.EatStreet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderListAdapter extends BaseAdapter {
    Context ctx;
    JSONArray data;

    LayoutInflater inflater;

    public OrderListAdapter(Context ctx, JSONArray data){
        this.ctx=ctx;
        this.data = data;
        inflater=LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return data.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        try {
            return data.getJSONObject(i).getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_order_list_item, null);
        TextView store_name_ = (TextView) view.findViewById(R.id.order_list_store_name);
        TextView pickup_ = (TextView) view.findViewById(R.id.order_list_pickup);
        TextView terminal_ = (TextView) view.findViewById(R.id.order_list_terminal);
        TextView status_ = (TextView) view.findViewById(R.id.order_list_status);
        TextView id_ = (TextView) view.findViewById(R.id.order_list_id);
        JSONObject row  = null;
        try {
            row = data.getJSONObject(i);

            store_name_.setText("Store Name:" + row.getString("storename"));
            pickup_.setText("Pick Up Address: "+row.getString("pickup"));
            terminal_.setText("Terminal Address: "+row.getString("terminal"));
            status_.setText("Terminal Address: "+row.getString("status"));
            id_.setText("Order ID: "+row.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
