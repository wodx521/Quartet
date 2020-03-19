package com.lr.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        //绑定View
        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract T getPresenter();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void getNetStatus(boolean isConnect) {

    }
}
