package com.lr.quartetplatform.moudle4.presenter;

import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.moudle4.SettingActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

public class SettingPresenter extends BasePresenterImpl<SettingActivity> {
    public void uploadImage(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.COMMON_UPLOAD, "upload", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return null;
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {

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
