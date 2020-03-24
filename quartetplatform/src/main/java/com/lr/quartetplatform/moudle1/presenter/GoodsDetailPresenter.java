package com.lr.quartetplatform.moudle1.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.GoodDetailBean;
import com.lr.quartetplatform.bean.ManagerBean;
import com.lr.quartetplatform.moudle1.activity.GoodsDetailActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class GoodsDetailPresenter extends BasePresenterImpl<GoodsDetailActivity> {
    public void getGoodsDetail(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.SHOP_GOODSDETAIL, "goodDetail", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<GoodDetailBean>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                GoodDetailBean goodDetailBean = (GoodDetailBean) generalResult.data;
                mPresenterView.setGoodDetail(goodDetailBean);
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

    public void getShopBps(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.SHOP_BPS, "ShopBps", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<ManagerBean>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                List<ManagerBean> managerBeanList = (List<ManagerBean>) generalResult.data;
                mPresenterView.setBps(managerBeanList);
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

    public void setConcern(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.SET_CONCERN, "concern", httpParams, new CustomizeStringCallback() {
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
                mPresenterView.setConcernSuccess();
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

    public void chatSeek(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.CHATS_SEEK, "chatSeek", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<String>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                mPresenterView.chatSeek((String) generalResult.data);
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
