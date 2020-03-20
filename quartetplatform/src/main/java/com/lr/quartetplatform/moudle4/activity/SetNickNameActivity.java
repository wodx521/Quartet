package com.lr.quartetplatform.moudle4.activity;

import android.text.TextUtils;
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
import com.lr.quartetplatform.bean.DataCache;
import com.lr.quartetplatform.bean.RegisterBean;
import com.lr.quartetplatform.bean.UserInfo;
import com.lr.quartetplatform.bean.UserMe;
import com.lr.quartetplatform.moudle4.presenter.SetNickNamePresenter;
import com.lr.quartetplatform.reaml.RealmUtils;
import com.lzy.okgo.model.HttpParams;

import java.util.LinkedHashMap;
import java.util.List;

public class SetNickNameActivity extends BaseMvpActivity<SetNickNamePresenter> {
    private ImageView ivBack;
    private EditText etNick;
    private TextView tvSubmit, tvTitle;
    private HttpParams httpParams = new HttpParams();
    private String type;

    @Override
    protected SetNickNamePresenter getPresenter() {
        return new SetNickNamePresenter();
    }

    @Override
    protected void initData() {
        if (mBundle != null) {
            type = mBundle.getString("type");
            String phone = (String) SpUtils.get("phone", "");
            DataCache dataCache = RealmUtils.queryData(phone + UrlConstant.CACHE_USER);
            if (dataCache != null && UiTools.noEmpty(dataCache.getCacheContent())) {
                UserMe userMe = GsonUtils.fromJson(dataCache.getCacheContent(), UserMe.class);
                String nickname = userMe.getNickname();
                String bio = userMe.getBio();
                if ("1".equals(type)) {
                    etNick.setHint(nickname);
                    tvTitle.setText(R.string.setNick);
                } else if ("2".equals(type)) {
                    etNick.setHint(bio);
                    tvTitle.setText(R.string.setSignature);
                }
            }
        }
    }

    @Override
    protected int getResId() {
        return R.layout.activity_set_nick_name;
    }

    @Override
    protected void initView() {
        ivBack = findViewById(R.id.ivBack);
        etNick = findViewById(R.id.etNick);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubmit = findViewById(R.id.tvSubmit);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSubmit:
                String nickName = UiTools.getText(etNick);
                if (UiTools.noEmpty(nickName)) {
                    if ("1".equals(type)) {
                        httpParams.put("nickname", nickName);
                    } else if ("2".equals(type)) {
                        httpParams.put("setqm", nickName);
                    }
                    mPresenter.setUserInfo(httpParams);
                } else {
                    UiTools.showToast(R.string.inputNickname);
                }
                break;
            default:
        }
    }

    public void setSuccess(HttpParams json) {
        LinkedHashMap<String, List<String>> urlParamsMap = json.urlParamsMap;
        List<String> stringList = urlParamsMap.get("nickname");
        String nickName = stringList.get(0);
        if (UiTools.noEmpty(nickName)) {
            String phone = (String) SpUtils.get("phone", "");
            DataCache dataCache = RealmUtils.queryData(phone + UrlConstant.CACHE_CONSTANT);
            if (dataCache != null && UiTools.noEmpty(dataCache.getCacheContent())) {
                RegisterBean registerBean = GsonUtils.fromJson(dataCache.getCacheContent(), RegisterBean.class);
                if (registerBean.getUserInfo() != null) {
                    UserInfo userInfo = registerBean.getUserInfo();

                    userInfo.setNickname(stringList.get(0));
                    RealmUtils.putCache(phone + UrlConstant.CACHE_CONSTANT, GsonUtils.toJson(registerBean));
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }
    }
}
