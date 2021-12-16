package com.example.EatStreet.Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface MyServerRequest {
    public void sendToServer(String url, JSONObject newUserObj, String methodType) throws JSONException;
    public void sendToServer(String url, JSONArray newUserArray, String methodType) throws JSONException;
    public void sendToServer(String url, JSONArray newUserArray, String methodType, MyVolleyListener listener) throws JSONException;
    public void addVolleyListener(MyVolleyListener logic);
}