package com.lr.quartetplatform.moudle4.activity;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.quartetplatform.R;

public class ForgetPassActivity extends BaseMvpActivity<ForgetPassPresenter> {
    @Override
    protected ForgetPassPresenter getPresenter() {
        return new ForgetPassPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getResId() {
        return R.layout.activity_forget_pass;
    }

    @Override
    protected void initView() {

    }
}
