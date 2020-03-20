package com.lr.quartetplatform;

import android.content.Context;
import android.text.TextUtils;

import com.lr.baselibrary.application.GlobalApplication;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.SpUtils;
import com.lr.quartetplatform.bean.DataCache;
import com.lr.quartetplatform.bean.RegisterBean;
import com.lr.quartetplatform.bean.UserInfo;
import com.lr.quartetplatform.reaml.RealmUtils;

public class MyApplication extends GlobalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmUtils.init(this);
        OkGoUtils.initOkGo(this, new HeaderIntercep());
    }

    @Override
    protected Context getAppContext() {
        return this;
    }
}
