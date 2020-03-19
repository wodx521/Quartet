package com.lr.quartetplatform.moudle2;

import android.view.View;

import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.quartetplatform.R;


public class SecondFragment extends BaseMvpFragment<SecondPresneter> {

    @Override
    protected SecondPresneter getPresenter() {
        return new SecondPresneter();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_second;
    }
}
