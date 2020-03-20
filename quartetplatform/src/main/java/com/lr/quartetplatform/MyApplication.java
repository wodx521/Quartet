package com.lr.quartetplatform;

import android.content.Context;

import com.lr.baselibrary.application.GlobalApplication;
import com.lr.quartetplatform.reaml.RealmUtils;

public class MyApplication extends GlobalApplication {
    @Override
    protected Context getAppContext() {
        return this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RealmUtils.init(this);
    }
}
