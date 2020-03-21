package com.lr.quartetplatform.moudle1.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.FilterTypeBean;
import com.lr.quartetplatform.bean.GoodsInfoBean;
import com.lr.quartetplatform.moudle1.activity.TypeDetailActivity;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class TypeDetailPresenter extends BasePresenterImpl<TypeDetailActivity> {
    public void getTypeDetail(HttpParams httpParams) {
        OkGoUtils.postRequest(UrlConstant.SHOP_GOODSLIST, "shop_list", httpParams, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<GoodsInfoBean>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                List<GoodsInfoBean> goodsInfoBeanList = (List<GoodsInfoBean>) generalResult.data;
                mPresenterView.setGoodsInfo(goodsInfoBeanList);
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

    public void getFilter() {
        OkGoUtils.postRequest(UrlConstant.INDEX_GOODSFILTER, "filterType", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<FilterTypeBean>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                FilterTypeBean filterTypeBean = (FilterTypeBean) generalResult.data;
                mPresenterView.setFilterList(filterTypeBean);
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
