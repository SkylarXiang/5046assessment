package com.example.a5046assessment;

import android.app.Application;

import androidx.work.Configuration;
import androidx.work.WorkManager;

public class MyApplication extends Application {

    public void onCreate() {
        super.onCreate();
        Configuration configuration = new Configuration.Builder().build();
        WorkManager.initialize(this, configuration);
    }
}
