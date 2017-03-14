package com.junior.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Thanggun99 on 15/03/2017.
 */

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        this.context = this;
        super.onCreate();
    }

    public static Context getContext() {
        return context;
    }
}
