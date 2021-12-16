package com.example.EatStreet.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.EatStreet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderDetailAdapter extends BaseAdapter {
    Context ctx;
    JSONArray data;

    LayoutInflater inflater;

    public OrderDetailAdapter(Context ctx, JSONArray data) {
        this.ctx = ctx;
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
        return -1;    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_order_detail_item, null);
        TextView item_name_ = (TextView) view.findViewById(R.id.order_detail_name);
        TextView item_price_ = (TextView) view.findViewById(R.id.order_detail_price);
        TextView item_quantity_ = (TextView) view.findViewById(R.id.order_detail_quantity);
        TextView id_ = (TextView) view.findViewById(R.id.order_detail_id);
        try {
            JSONObject row  = data.getJSONObject(i);

            item_name_.setText("Name:" + row.getString("itemname"));
            item_price_.setText("Price: "+row.getDouble("itemprice"));
            item_quantity_.setText("Price: "+row.getDouble("quantity"));
            id_.setText("ID: "+row.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
