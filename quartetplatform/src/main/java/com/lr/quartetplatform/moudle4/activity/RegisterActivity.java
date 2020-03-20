package com.lr.quartetplatform.moudle4.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
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
import com.lr.quartetplatform.bean.RegisterBean;
import com.lr.quartetplatform.moudle4.presenter.RegisterPresenter;
import com.lr.quartetplatform.reaml.RealmUtils;
import com.lzy.okgo.model.HttpParams;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> {
    private ImageView ivBack;
    private EditText etPhone, etCode, etPass, etPassAgain;
    private TextView tvSend, tvRegister, tvProtocol;
    private CheckBox cb1, cb2, cbCheck;
    private HttpParams httpParams = new HttpParams();
    private CountDownUtils countDownUtils;
    private Bundle bundle = new Bundle();

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
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cbCheck = findViewById(R.id.cbCheck);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvProtocol.setOnClickListener(this);
        cb1.setOnClickListener(this);
        cb2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone, code, pass, passAgain;
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSend:
                httpParams.clear();
                phone = UiTools.getText(etPhone);
                if (UiTools.noEmpty(phone)) {
                    httpParams.put("mobile", phone);
                    httpParams.put("event", "register");
                    mPresenter.sendCode(httpParams);
                } else {
                    UiTools.showToast(R.string.inputPhone);
                }
                break;
            case R.id.tvRegister:
                httpParams.clear();
                phone = UiTools.getText(etPhone);
                code = UiTools.getText(etCode);
                pass = UiTools.getText(etPass);
                passAgain = UiTools.getText(etPassAgain);

                if (UiTools.noEmpty(phone, code, pass, passAgain) && pass.equals(passAgain) && cbCheck.isChecked()) {
                    httpParams.put("code", code);
                    httpParams.put("invitaioncode", "");
                    httpParams.put("password", pass);
                    httpParams.put("repassword", passAgain);
                    httpParams.put("mobile", phone);
                    httpParams.put("flag_bp", "");
                    mPresenter.register(httpParams);
                } else {
                    if (!UiTools.noEmpty(phone)) {
                        UiTools.showToast(R.string.inputPhone);
                    } else if (!UiTools.noEmpty(code)) {
                        UiTools.showToast(R.string.inputCode);
                    } else if (!UiTools.noEmpty(pass)) {
                        UiTools.showToast(R.string.inputPass);
                    } else if (!UiTools.noEmpty(passAgain)) {
                        UiTools.showToast(R.string.loginPassAgain);
                    } else if (!pass.equals(passAgain)) {
                        UiTools.showToast(R.string.passTwiceError);
                    } else if (!cbCheck.isChecked()) {
                        UiTools.showToast(R.string.readProtocol);
                    }
                }
                break;
            case R.id.tvProtocol:
                mPresenter.protocol();
                break;
            case R.id.cb1:
                if (cb1.isChecked()) {
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.cb2:
                if (cb2.isChecked()) {
                    etPassAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPassAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
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

    public void registerSuccess(RegisterBean registerBean) {
        String registerInfo = GsonUtils.toJson(registerBean);
        RealmUtils.putCache("registerResultInfo", registerInfo);
        finish();
    }

    public void setProtocol(String protocol) {
        bundle.clear();
        bundle.putString("protocolContent", protocol);
        startActivity(RegisterActivity.this, bundle, ProtocolActivity.class);
    }
}
