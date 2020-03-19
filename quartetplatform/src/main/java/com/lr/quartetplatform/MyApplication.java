package com.lr.quartetplatform;

import android.content.Context;

import com.lr.baselibrary.application.GlobalApplication;

public class MyApplication extends GlobalApplication {
    @Override
    protected Context getAppContext() {
        return this;
    }
}
