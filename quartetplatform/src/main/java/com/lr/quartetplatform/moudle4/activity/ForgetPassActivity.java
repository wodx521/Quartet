package com.lr.quartetplatform.moudle4.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.utils.CountDownUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.BuildConfig;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lzy.okgo.model.HttpParams;

public class ForgetPassActivity extends BaseMvpActivity<ForgetPassPresenter> {
    private ImageView ivBack;
    private CheckBox cb1, cb2;
    private EditText etPhone, etCode, etPass, etPassAgain;
    private TextView tvTitle, tvSend, tvChange;
    private HttpParams httpParams = new HttpParams();
    private String type;
    private CountDownUtils countDownUtils;

    @Override
    protected ForgetPassPresenter getPresenter() {
        return new ForgetPassPresenter();
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.changePass);
        if (mBundle != null) {
            type = mBundle.getString("type");
        }
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
        return R.layout.activity_forget_pass;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        etPhone = findViewById(R.id.etPhone);
        etCode = findViewById(R.id.etCode);
        tvSend = findViewById(R.id.tvSend);
        etPass = findViewById(R.id.etPass);
        cb1 = findViewById(R.id.cb1);
        etPassAgain = findViewById(R.id.etPassAgain);
        cb2 = findViewById(R.id.cb2);
        tvChange = findViewById(R.id.tvChange);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        cb1.setOnClickListener(this);
        cb2.setOnClickListener(this);
        tvChange.setOnClickListener(this);
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
                    httpParams.put("event", "resetpwd");
                    mPresenter.sendCode(httpParams);
                } else {
                    UiTools.showToast(R.string.inputPhone);
                }
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
            case R.id.tvChange:
                httpParams.clear();
                phone = UiTools.getText(etPhone);
                code = UiTools.getText(etCode);
                pass = UiTools.getText(etPass);
                passAgain = UiTools.getText(etPassAgain);

                if (UiTools.noEmpty(phone, code, pass, passAgain) && pass.equals(passAgain)) {
                    httpParams.put("code", code);
                    httpParams.put("newpassword", pass);
                    httpParams.put("renewpassword", passAgain);
                    httpParams.put("mobile", phone);
                    httpParams.put("type", type);
                    mPresenter.changePass(httpParams);
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
                    }
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

    public void changeSuccess() {
        finish();
    }
}
