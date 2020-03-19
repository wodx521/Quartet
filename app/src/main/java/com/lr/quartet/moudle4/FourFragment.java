package com.lr.quartet.moudle4;

import android.view.View;

import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.quartet.R;

public class FourFragment extends BaseMvpFragment<FourPresenter> {
    @Override
    protected FourPresenter getPresenter() {
        return new FourPresenter();
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
