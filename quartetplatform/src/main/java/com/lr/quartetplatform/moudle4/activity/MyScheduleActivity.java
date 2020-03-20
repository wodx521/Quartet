package com.lr.quartetplatform.moudle4.activity;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle4.presenter.MySchedulePresenter;

public class MyScheduleActivity extends BaseMvpActivity<MySchedulePresenter> {
    @Override
    protected MySchedulePresenter getPresenter() {
        return new MySchedulePresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getResId() {
        return R.layout.activity_myschedule;
    }

    @Override
    protected void initView() {

    }
}
