package com.lr.quartetplatform.moudle4.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.CustomInfoBean;
import com.lr.quartetplatform.moudle4.activity.RegisterActivity;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class RegisterPresenter extends BasePresenterImpl<RegisterActivity> {
    public void sendCode(String json) {
        OkGoUtils.postRequest(UrlConstant.INDEX_SENDSMS, "custom", json, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<Void>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                mPresenterView.sendSuccess();
            }

            @Override
            public void onRequestError(SimpleResponse simpleResponse) {

            }

            @Override
            public void onRequestStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onRequestFinish() {

            }
        });
    }
}
