package com.lr.quartetplatform.moudle3;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ChatListBean;
import com.lr.quartetplatform.bean.HomeInfoBean;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class ThirdPresenter extends BasePresenterImpl<ThirdFragment> {
    public void getChatList() {
        OkGoUtils.postRequest(UrlConstant.CHAT_LIST, "chatList", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<ChatListBean>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                List<ChatListBean> chatListBeans = (List<ChatListBean>) generalResult.data;
                mPresenterView.setChatList(chatListBeans);
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
