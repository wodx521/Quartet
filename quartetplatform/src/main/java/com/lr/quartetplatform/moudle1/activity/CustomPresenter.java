package com.lr.quartetplatform.moudle1.activity;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.CustomInfoBean;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

public class CustomPresenter extends BasePresenterImpl<CustomActivity> {
    public void getCustomInfo() {
        OkGoUtils.postRequest(UrlConstant.CUSTOMIZATION, "custom", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<CustomInfoBean>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                CustomInfoBean customInfoBean = (CustomInfoBean) generalResult.data;
                mPresenterView.setCustomInfo(customInfoBean);
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

    public void uploadImage(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.COMMON_UPLOAD, "uploadImage", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<String>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                String imageUrl = (String) generalResult.data;
                mPresenterView.setUploadResult(imageUrl);
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

    public void submitCustom(String json) {
        OkGoUtils.postRequest(UrlConstant.USER_CUSTOMIZATION_CREATE, "submitCustom", json, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<String>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                String imageUrl = (String) generalResult.data;
                mPresenterView.setUploadResult(imageUrl);
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
