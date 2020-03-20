package com.lr.quartetplatform.moudle4.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.SpUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.RegisterBean;
import com.lr.quartetplatform.moudle4.presenter.LoginPresenter;
import com.lr.quartetplatform.reaml.RealmUtils;
import com.lzy.okgo.model.HttpParams;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> {
    private TextView tvTitle;
    private ImageView ivBack;
    private EditText etPhone;
    private EditText etPass;
    private TextView tvForget;
    private TextView tvLogin;
    private TextView tvRegister;
    private HttpParams httpParams = new HttpParams();

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.login);
        tvRegister.setText(Html.fromHtml(UiTools.getString(R.string.registerNow)));
    }

    @Override
    protected int getResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        etPhone = findViewById(R.id.etPhone);
        etPass = findViewById(R.id.etPass);
        tvForget = findViewById(R.id.tvForget);
        tvLogin = findViewById(R.id.tvLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvForget.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }
private Bundle bundle = new Bundle();
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvForget:
                bundle.clear();
                bundle.putString("type","1");
                startActivity(LoginActivity.this, bundle, ForgetPassActivity.class);
                break;
            case R.id.tvLogin:
                String pass = UiTools.getText(etPass);
                String phone = UiTools.getText(etPhone);
                if (UiTools.noEmpty(phone, pass)) {
                    httpParams.clear();
                    httpParams.put("account", phone);
                    httpParams.put("password", pass);
                    mPresenter.login(httpParams);
                } else {
                    if (!UiTools.noEmpty(phone)) {
                        UiTools.showToast(R.string.inputAccount);
                    } else if (!UiTools.noEmpty(pass)) {
                        UiTools.showToast(R.string.inputPass);
                    }
                }
                break;
            case R.id.tvRegister:
                startActivity(LoginActivity.this, null, RegisterActivity.class);
                break;
            default:
        }
    }

    public void loginSuccess(RegisterBean registerBean) {
        String phone = UiTools.getText(etPhone);
        SpUtils.put("phone",phone);
        SpUtils.put("token",registerBean.getUserInfo().getToken());
        String registerInfo = GsonUtils.toJson(registerBean);
        RealmUtils.putCache(phone + UrlConstant.CACHE_CONSTANT, registerInfo);
        setResult(RESULT_OK);
        finish();
    }
}
