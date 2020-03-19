package com.lr.quartet;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.lr.baselibrary.application.GlobalApplication;

public class MyApplication extends GlobalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }

    @Override
    protected Context getAppContext() {
        return this;
    }
}
