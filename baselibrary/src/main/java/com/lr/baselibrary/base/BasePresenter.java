package com.lr.baselibrary.base;

public interface BasePresenter<T extends BaseView> {
    /**
     * 绑定视图
     *
     * @param page 视图page
     */
    void attachView(T page);

    /**
     * 解绑视图
     */
    void detachView();

}
