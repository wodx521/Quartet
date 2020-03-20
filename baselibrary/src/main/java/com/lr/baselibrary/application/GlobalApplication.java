package com.lr.baselibrary.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.LogUtils;
import com.lr.baselibrary.utils.UiTools;


public abstract class GlobalApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getAppContext();
//        SkinCompatManager.withoutActivity(this)
//                .addInflater(new SkinAppCompatViewInflater())           // 基础控件换肤初始化
//                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
//                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
//                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
//                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
//                .setSkinWindowBackgroundEnable(true)                   // 关闭windowBackground换肤，默认打开[可选]
//                .setSkinAllActivityEnable(true)
//                .loadSkin();
        LogUtils.initLogUtils(this);
//        OkGoUtils.initOkGo(this,"");
        UiTools.initUiTools(this);
    }

    public static Context getContext() {
        return context;
    }

    protected abstract Context getAppContext();
}