package com.lr.baselibrary.base;


public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {
    protected T mPresenterView;

    @Override
    public void attachView(T page) {
        this.mPresenterView = page;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null;
    }

}
