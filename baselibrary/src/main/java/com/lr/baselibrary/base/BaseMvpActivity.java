package com.lr.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;


public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        mPresenter.attachView(this);
        initData();
        initClickListener();
    }

    // 获取Presenter对象
    protected abstract T getPresenter();

    // 初始化数据
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    protected void getNetStatus(boolean netStatus) {

    }
}
