package com.isever.sergn.homeproject.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class LifeCycleApp {
    private static final String TAG = "myLogsLifeCycle";

    @SuppressLint("ShowToast")
    public LifeCycleApp(String nameCycle, Context context){
        Toast.makeText(context, nameCycle, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "///////////////////////////////////");
        Log.i(TAG, nameCycle);
        Log.i(TAG, "///////////////////////////////////");
    }
}
