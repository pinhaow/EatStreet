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

public class StoreListAdapter extends BaseAdapter {
    Context ctx;
    JSONArray data;

    LayoutInflater inflater;

    public StoreListAdapter(Context ctx, JSONArray data) {
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
            return data.getJSONObject(i).getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_store_list_item, null);
        TextView store_name_ = (TextView) view.findViewById(R.id.store_list_name);
        TextView store_address_ = (TextView) view.findViewById(R.id.store_list_address);
        TextView store_phone_ = (TextView) view.findViewById(R.id.store_list_phone);
        TextView id_ = (TextView) view.findViewById(R.id.store_list_id);
        try {
            JSONObject row  = data.getJSONObject(i);

            store_name_.setText("Restaurant Name:" + row.getString("name"));
            store_address_.setText("Restaurant Location: "+row.getString("address"));
            store_phone_.setText("Contact phone number: "+row.getString("phone"));
            id_.setText("Restaurant ID: "+row.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
