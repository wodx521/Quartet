package com.lr.quartetplatform.moudle4;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.UserMe;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

public class FourPresenter extends BasePresenterImpl<FourFragment> {

    public void getUserIndex() {
        OkGoUtils.postRequest(UrlConstant.USER_INDEX, "userIndex", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<UserMe>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                UserMe userMe = (UserMe) generalResult.data;
                mPresenterView.getUserMe(userMe);
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
