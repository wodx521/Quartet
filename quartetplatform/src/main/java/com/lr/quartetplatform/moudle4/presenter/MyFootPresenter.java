package com.lr.quartetplatform.moudle4.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.GoodsInfoBean;
import com.lr.quartetplatform.moudle4.activity.MyFootActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class MyFootPresenter extends BasePresenterImpl<MyFootActivity> {
    public void getFoot(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.SHOP_MYGOODSLIST, "myFoot", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<GoodsInfoBean>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                List<GoodsInfoBean> goodsList = (List<GoodsInfoBean>) generalResult.data;
                mPresenterView.setMyFoot(goodsList);
            }

            @Override
            public void onRequestError(SimpleResponse simpleResponse) {
                if (simpleResponse != null) {
                    if (UiTools.noEmpty(simpleResponse.msg)) {
                        UiTools.showToast(simpleResponse.msg);
                    }
                    if (simpleResponse.code == 401 && mPresenterView != null) {
                        mPresenterView.loginTimeOut();
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
}
