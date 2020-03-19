package com.lr.baselibrary.okgoutil;

import android.util.Log;

import com.lr.baselibrary.R;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


public abstract class CustomizeStringCallback extends StringCallback {

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        onRequestStart(request);
    }

    @Override
    public void onSuccess(Response<String> response) {
        String body = response.body();
        if (body == null) {
            return;
        }
        try {
            SimpleResponse simpleResponse = GsonUtils.fromJson(body, SimpleResponse.class);
            int resultCode = simpleResponse.code;
            GeneralResult generalResult = null;
            if (resultCode == 1) {
                generalResult = GsonUtils.fromJson(body, getResultType());
                onRequestSuccess(generalResult);
            } else {
                response.setException(new IllegalAccessException(simpleResponse.msg));
                onError(response);
            }
        } catch (Exception e) {
            response.setException(e);
            onError(response);
        }
    }

    @Override
    public void onError(Response<String> response) {
        Throwable exception = response.getException();
        exception.printStackTrace();
        SimpleResponse simpleResponse = null;
        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
//            UiTools.showToast(UiTools.getString(R.string.connect_fail));
            Log.e("stringCallBack", UiTools.getString(R.string.connect_fail));
        } else if (exception instanceof SocketTimeoutException) {
//            UiTools.showToast(UiTools.getString(R.string.connect_out_time));
            Log.e("stringCallBack",UiTools.getString(R.string.connect_out_time));
        } else if (exception instanceof IllegalAccessException) {
            simpleResponse = GsonUtils.fromJson(response.body(), SimpleResponse.class);
        } else {
            UiTools.showToast(UiTools.getString(R.string.server_error));
        }
        onRequestError(simpleResponse);
    }

    @Override
    public void onFinish() {
        onRequestFinish();
    }

    public abstract Type getResultType();

    public abstract void onRequestSuccess(GeneralResult generalResult);

    public abstract void onRequestError(SimpleResponse simpleResponse);

    public abstract void onRequestStart(Request<String, ? extends Request> request);

    public abstract void onRequestFinish();
}
