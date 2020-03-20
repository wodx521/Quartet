package com.lr.quartetplatform.moudle4.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.RegisterBean;
import com.lr.quartetplatform.moudle4.activity.RegisterActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class RegisterPresenter extends BasePresenterImpl<RegisterActivity> {
    public void sendCode(HttpParams json) {
        OkGoUtils.postRequest(UrlConstant.INDEX_SENDSMS, "custom", json, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<Void>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                if (UiTools.noEmpty(generalResult.msg)) {
                    UiTools.showToast(generalResult.msg);
                }
                mPresenterView.sendSuccess();
            }

            @Override
            public void onRequestError(SimpleResponse simpleResponse) {
                if (simpleResponse != null) {
                    if (UiTools.noEmpty(simpleResponse.msg)) {
                        UiTools.showToast(simpleResponse.msg);
                    }
                }
            }

            @Override
            public void onRequestStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onRequestFinish() {

            }
        });
    }

    public void register(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.USER_REGISTER, "register", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<RegisterBean>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                if (generalResult != null) {
                    if (UiTools.noEmpty(generalResult.msg)) {
                        UiTools.showToast(generalResult.msg);
                    }
                    RegisterBean registerBean = (RegisterBean) generalResult.data;
                    mPresenterView.registerSuccess(registerBean);
                }
            }

            @Override
            public void onRequestError(SimpleResponse simpleResponse) {
                if (simpleResponse != null) {
                    if (UiTools.noEmpty(simpleResponse.msg)) {
                        UiTools.showToast(simpleResponse.msg);
                    }
                }
            }

            @Override
            public void onRequestStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onRequestFinish() {

            }
        });
    }

    public void protocol() {
        OkGoUtils.postRequest(UrlConstant.USER_REGISTRATIONINFO, "protocol", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<String>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                String protocol = (String) generalResult.data;
                mPresenterView.setProtocol(protocol);
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
