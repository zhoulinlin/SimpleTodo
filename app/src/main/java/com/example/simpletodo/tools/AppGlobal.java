package com.example.simpletodo.tools;

import android.content.Context;

public class AppGlobal {

    private static AppGlobal sInstance;

    private Context applicationContext;

    private AppGlobal() {
    }

    public static AppGlobal getInstance() {
        if (sInstance == null) {
            synchronized (AppGlobal.class) {
                if (sInstance == null) {
                    sInstance = new AppGlobal();
                }
            }
        }
        return sInstance;
    }

    public void setApplicationContext(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

}
