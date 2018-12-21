package com.dmtech.tabdemo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    public static Context sAppContext;

    public static Context getAppContext() {
        return sAppContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        sAppContext = this;
    }


}
