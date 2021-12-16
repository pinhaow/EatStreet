package com.example.EatStreet.Logic;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.EatStreet.appController;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Interface.MyVolleyListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerRequest implements MyServerRequest {
    public final static String WS_HOST = "ws://10.0.2.2:8080/ws/";
    public final static String HOST_ADDRESS = "http://10.0.2.2:8080";
    private final static String HOST_PREFIX = "http://10.0.2.2:8080/api/v1";
    private String tag_json_obj = "json_obj_req";
    private MyVolleyListener I;


    public void sendToServer(String url, JSONObject newUserObj, String methodType) throws JSONException{
        url = HOST_PREFIX + url;
        int method = Request.Method.GET;
        if(methodType.equals("POST")){
            method = Request.Method.POST;
        } else if (methodType.equals("DELETE")) {
            method = Request.Method.DELETE;
        } else if (methodType.equals("PUT")){
            method = Request.Method.PUT;
        }

        JsonObjectRequest registerUserRequest = new JsonObjectRequest(method, url, newUserObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            try {
                                I.onSuccess(response.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            I.onError("Null Response object received");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        I.onError(error.getMessage());
//                    error.printStackTrace();
                    }
                });
        appController.getInstance().addToRequestQueue(registerUserRequest, tag_json_obj);
    }

    @Override
    public void sendToServer(String url, JSONArray newUserArray, String methodType) throws JSONException {
        url = HOST_PREFIX + url;
        int method = Request.Method.GET;
        if(methodType.equals("POST")){
            method = Request.Method.POST;
        } else if (methodType.equals("DELETE")) {
            method = Request.Method.DELETE;
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(method, url, newUserArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            try {
                                I.onSuccess(response);
                                System.out.print("here");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            I.onError("Null Response object received");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        I.onError(error.getMessage());
//                    error.printStackTrace();
                    }
                });
        appController.getInstance().addToRequestQueue(jsonArrayRequest, tag_json_obj);
    }

    @Override
    public void sendToServer(String url, JSONArray newUserArray, String methodType, MyVolleyListener listener) throws JSONException {
        int method = Request.Method.GET;
        if(methodType.equals("POST")){
            method = Request.Method.POST;
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(method, url, newUserArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            try {
                                listener.onSuccess(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            listener.onError("Null Response object received");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.getMessage());
//                    error.printStackTrace();
                    }
                });
        appController.getInstance().addToRequestQueue(jsonArrayRequest, tag_json_obj);
    }



    @Override
    public void addVolleyListener(MyVolleyListener logic) {
        I = logic;
    }
}