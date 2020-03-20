package com.lr.quartetplatform.moudle4.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.utils.CountDownUtils;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.BuildConfig;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.params.SendParams;
import com.lr.quartetplatform.moudle4.presenter.RegisterPresenter;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> {
    private ImageView ivBack;
    private EditText etPhone, etCode, etPass, etPassAgain;
    private TextView tvSend, tvRegister, tvProtocol;
    private SendParams sendParams = new SendParams();
    private CountDownUtils countDownUtils;

    @Override
    protected RegisterPresenter getPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void initData() {
        countDownUtils = new CountDownUtils();
        countDownUtils.setTimeFinishListener(new CountDownUtils.CountTimeFinishListener() {
            @Override
            public void onTimeFinishListener() {
                tvSend.setEnabled(true);
            }
        });
    }

    @Override
    protected int getResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ivBack = findViewById(R.id.ivBack);
        etPhone = findViewById(R.id.etPhone);
        etCode = findViewById(R.id.etCode);
        tvSend = findViewById(R.id.tvSend);
        etPass = findViewById(R.id.etPass);
        etPassAgain = findViewById(R.id.etPassAgain);
        tvRegister = findViewById(R.id.tvRegister);
        tvProtocol = findViewById(R.id.tvProtocol);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvProtocol.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSend:
                String phone = UiTools.getText(etPhone);
                if (UiTools.noEmpty(phone)) {
                    sendParams.setEvent("register");
                    sendParams.setMobile(phone);
                    mPresenter.sendCode(GsonUtils.toJson(sendParams));
                } else {
                    UiTools.showToast(R.string.inputPhone);
                }
                break;
            case R.id.tvRegister:

                break;
            case R.id.tvProtocol:

                break;
            default:
        }
    }

    public void sendSuccess() {
        tvSend.setEnabled(false);
        if (BuildConfig.DEBUG) {
            countDownUtils.getTimer(UrlConstant.TEST_TIME, tvSend, UiTools.getString(R.string.send));
        } else {
            countDownUtils.getTimer(UrlConstant.RELEASE_TIME, tvSend, UiTools.getString(R.string.send));
        }
    }
}
