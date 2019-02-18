package com.isever.sergn.homeproject.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class MainService extends IntentService {

    public MainService() {
        super("MainService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }
}
