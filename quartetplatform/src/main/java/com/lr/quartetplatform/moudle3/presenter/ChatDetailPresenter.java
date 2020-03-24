package com.lr.quartetplatform.moudle3.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ChatInfoBean;
import com.lr.quartetplatform.moudle3.activity.ChatDetailActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class ChatDetailPresenter extends BasePresenterImpl<ChatDetailActivity> {
    public void getChatInfo(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.CHAT_INFO, "chatList", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<ChatInfoBean>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                List<ChatInfoBean> chatInfoBeans = (List<ChatInfoBean>) generalResult.data;
                mPresenterView.setChatInfo(chatInfoBeans);
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

    public void sendChatInfo(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.USER_CHAT, "sendChat", httpParams, new CustomizeStringCallback() {
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
