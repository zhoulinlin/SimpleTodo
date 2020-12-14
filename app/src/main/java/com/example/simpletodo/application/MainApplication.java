package com.example.simpletodo.application;

import android.app.Application;

import com.example.simpletodo.tools.AppGlobal;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppGlobal.getInstance().setApplicationContext(this);
    }

}

