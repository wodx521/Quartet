package com.lr.quartetplatform.moudle4.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ChatListBean;
import com.lr.quartetplatform.bean.GoodDetailBean;
import com.lr.quartetplatform.moudle4.activity.MyScheduleActivity;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class MySchedulePresenter extends BasePresenterImpl<MyScheduleActivity> {
    public void getConcern() {
        OkGoUtils.postRequest(UrlConstant.MY_CONSERN, "chatList", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<GoodDetailBean>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                List<GoodDetailBean> goodsList = (List<GoodDetailBean>) generalResult.data;
                mPresenterView.setMyConcern(goodsList);
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
