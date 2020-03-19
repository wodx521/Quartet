package com.lr.quartetplatform.moudle4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.baselibrary.weight.CircleImageView;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle4.activity.LoginActivity;

public class FourFragment extends BaseMvpFragment<FourPresenter> {
    private CircleImageView ivHead;
    private TextView tvRegister;
    private ImageView ivSetting;
    private TextView tvFoot;
    private TextView tvCustom;
    private TextView tvSchedule;
    private TextView tvShop;

    @Override
    protected FourPresenter getPresenter() {
        return new FourPresenter();
    }

    @Override
    protected void initView(View view) {
        ivHead = view.findViewById(R.id.ivHead);
        tvRegister = view.findViewById(R.id.tvRegister);
        ivSetting = view.findViewById(R.id.ivSetting);
        tvFoot = view.findViewById(R.id.tvFoot);
        tvCustom = view.findViewById(R.id.tvCustom);
        tvSchedule = view.findViewById(R.id.tvSchedule);
        tvShop = view.findViewById(R.id.tvShop);
    }

    @Override
    protected void initClickListener() {
        ivHead.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        tvFoot.setOnClickListener(this);
        tvCustom.setOnClickListener(this);
        tvSchedule.setOnClickListener(this);
        tvShop.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_four;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHead:
            case R.id.ivSetting:

                break;
            case R.id.tvRegister:
                startActivity(FourFragment.this,null, LoginActivity.class);

                break;
            case R.id.tvFoot:

                break;
            case R.id.tvCustom:

                break;
            case R.id.tvSchedule:

                break;
            case R.id.tvShop:

                break;
            default:
        }
    }
}
