package com.lr.quartetplatform.moudle1.activity;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.CustomInfoBean;
import com.lr.quartetplatform.bean.CustomResult;
import com.lr.quartetplatform.bean.UploadBean;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

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
                return new TypeToken<GeneralResult<UploadBean>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                UploadBean imageUrl = (UploadBean) generalResult.data;
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

    public void submitCustom(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.USER_CUSTOMIZATION_CREATE, "submitCustom", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<CustomResult>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                mPresenterView.setCustomSuccess();
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
