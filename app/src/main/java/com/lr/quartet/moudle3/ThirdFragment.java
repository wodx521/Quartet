package com.lr.quartet.moudle3;

import android.view.View;

import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.quartet.R;

public class ThirdFragment extends BaseMvpFragment<ThirdPresenter> {
    @Override
    protected ThirdPresenter getPresenter() {
        return new ThirdPresenter();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_first;
    }
}
