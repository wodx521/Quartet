package com.lr.quartetplatform.moudle4.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle4.presenter.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> {
    private TextView tvTitle;
    private ImageView ivBack;
    private EditText etPhone;
    private EditText etPass;
    private TextView tvForget;
    private TextView tvLogin;
    private TextView tvRegister;

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.login);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvForget:
                startActivity(LoginActivity.this, null, ForgetPassActivity.class);
                break;
            case R.id.tvLogin:

                break;
            case R.id.tvRegister:
                startActivity(LoginActivity.this, null, RegisterActivity.class);
                break;
            default:
        }
    }
}
