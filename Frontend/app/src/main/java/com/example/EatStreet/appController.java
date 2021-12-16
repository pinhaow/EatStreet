package com.example.EatStreet;

import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.EatStreet.Interface.MyServerRequest;
import com.example.EatStreet.Logic.ServerRequest;

public class appController extends Application {
    public static final String TAG = appController.class.getSimpleName();
    public static final MyServerRequest myServerRequest = new ServerRequest();

    private RequestQueue mRequestQueue;
    private static appController mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance=this;
    }

    public static synchronized appController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }
}