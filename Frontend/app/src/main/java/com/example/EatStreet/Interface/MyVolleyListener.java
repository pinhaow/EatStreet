package com.example.EatStreet.Interface;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public interface MyVolleyListener {
    public void onSuccess(String s) throws JSONException;
    public void onSuccess(JSONArray arr) throws JSONException;
    public void onError(String s);
}