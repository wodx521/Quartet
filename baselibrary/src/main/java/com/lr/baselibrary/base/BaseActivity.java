package com.lr.baselibrary.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.gyf.immersionbar.ImmersionBar;
import com.lr.baselibrary.R;
import com.lr.baselibrary.manager.ActivityManage;
import com.lr.baselibrary.receiver.NetStatusReceiver;
import com.lr.baselibrary.weight.SimpleMultiStateView;


public abstract class BaseActivity extends AppCompatActivity implements BaseView, View.OnClickListener {

    protected Bundle mBundle;
    protected SimpleMultiStateView mSimpleMultiStateView;
    private NetStatusReceiver netStatusReceiver;

    public void startActivity(Context context, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        context.startActivity(intent);
    }

    public void compatStartActivity(Context context, Bundle intentBundle, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (intentBundle != null) {
            intent.putExtra("bundle", intentBundle);
        }
        ActivityCompat.startActivity(context, intent, bundle);
    }

    public void startActivityForResult(Context context, Bundle bundle, int requestCode, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, requestCode);
        }
    }

    public void compatStartActivityForResult(Context context, Bundle intentBundle, Bundle bundle, int requestCode, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        if (intentBundle != null) {
            intent.putExtra("bundle", intentBundle);
        }
        if (context instanceof BaseActivity) {
            ActivityCompat.startActivityForResult((BaseActivity) context, intent, requestCode, bundle);
        }
    }

//    @NonNull
//    @Override
//    public AppCompatDelegate getDelegate() {
//        return SkinAppCompatDelegateImpl.get(this, this);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        highApiEffects();
        ActivityManage.getInstance().addActivity(this);
        setStatusBar();
        setContentView(getResId());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netStatusReceiver = new NetStatusReceiver();
        registerReceiver(netStatusReceiver, intentFilter);
        mBundle = getIntent().getBundleExtra("bundle");
        initView();
        initStateView();
        netStatusReceiver.setNetStatusListener(new NetStatusReceiver.NetStatusListener() {
            @Override
            public void onNetChange(boolean netStatus) {
                getNetStatus(netStatus);
            }

            @Override
            public void onWifi(boolean isWifiConneted) {

            }

            @Override
            public void onMobile(boolean isMobileData) {

            }
        });
    }

    protected abstract void getNetStatus(boolean netStatus);

    protected void setStatusBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.libWhite)     //状态栏颜色，不写默认透明色
                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .autoStatusBarDarkModeEnable(true, 0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//                .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
                .removeSupportAllView() //移除全部view支持
                .navigationBarWithKitkatEnable(true)  //是否可以修改安卓4.4和emui3.1手机导航栏颜色，默认为true
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
                .addTag("tag")  //给以上设置的参数打标记
                .getTag("tag")  //根据tag获得沉浸式参数
                .init();  //必须调用方可沉浸式
    }

    //设置布局id
    protected abstract int getResId();

    //初始化view
    protected abstract void initView();

    private void initStateView() {
        if (mSimpleMultiStateView == null) return;
        mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                .setRetryResource(R.layout.view_retry)
                .setLoadingResource(R.layout.view_loading)
                .setNoNetResource(R.layout.view_nonet)
                .build()
                .setonReLoadlistener(this::onRetry);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netStatusReceiver);
        ActivityManage.getInstance().removeActivity(this);
    }

    /**
     * 点击空白区域隐藏键盘.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN) {  //把操作放在用户点击的时候
            View v = getCurrentFocus();      //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, me)) { //判断用户点击的是否是输入框以外的区域

                hideKeyboard(v.getWindowToken());   //收起键盘
            }
        }
        return super.dispatchTouchEvent(me);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        //判断得到的焦点控件是否包含EditText
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],    //得到输入框在屏幕中上下左右的位置
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击位置如果是EditText的区域，忽略它，不收起键盘。
                return false;
            } else {
                v.clearFocus();
                return true;
            }
        }
        // 如果焦点不是EditText则忽略
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null) {
                im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    // 初始化点击事件
    protected void initClickListener() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffects() {
        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    protected void viewGone(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.GONE);
            }
        }
    }

    protected void viewVisible(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    protected void viewInvisible(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void showLoading() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showLoadingView();
        }
    }

    @Override
    public void showSuccess() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showContent();
        }
    }

    @Override
    public void showFaild() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showErrorView();
        }
    }

    @Override
    public void showNoNet() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showNoNetView();
        }
    }

    @Override
    public void onRetry() {
        initDatas();
    }

    /**
     * 初始化数据
     */
    public void initDatas() {

    }

    @Override
    public void onClick(View v) {

    }

}
