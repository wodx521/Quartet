package com.lr.quartetplatform.moudle1.presenter;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.bean.GeneralResult;
import com.lr.baselibrary.bean.SimpleResponse;
import com.lr.baselibrary.okgoutil.CustomizeStringCallback;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.GoodDetailBean;
import com.lr.quartetplatform.bean.GoodDetailBean;
import com.lr.quartetplatform.bean.HomeInfoBean;
import com.lr.quartetplatform.moudle1.FirstFragment;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;
import java.util.List;

public class FirstPresenter extends BasePresenterImpl<FirstFragment> {
    public void getBanner() {
            OkGoUtils.postRequest(UrlConstant.BANNER, "banner", null, new CustomizeStringCallback() {
                @Override
                public Type getResultType() {
                    return new TypeToken<GeneralResult<List<String>>>() {
                    }.getType();
                }

                @Override
                public void onRequestSuccess(GeneralResult generalResult) {
                    List<String> bannerList = (List<String>) generalResult.data;
                    mPresenterView.setBanner(bannerList);
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

    public void getHomeInfo() {
        OkGoUtils.postRequest(UrlConstant.HOME_INFO, "homeInfo", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<HomeInfoBean>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                HomeInfoBean homeInfoBean = (HomeInfoBean) generalResult.data;
                mPresenterView.setHomeInfo(homeInfoBean);
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

    public void getGoodsList() {
        OkGoUtils.postRequest(UrlConstant.GOODS_LIST, "goodsList", null, new CustomizeStringCallback() {
            @Override
            public Type getResultType() {
                return new TypeToken<GeneralResult<List<GoodDetailBean>>>() {
                }.getType();
            }

            @Override
            public void onRequestSuccess(GeneralResult generalResult) {
                List<GoodDetailBean> GoodDetailBeanList = (List<GoodDetailBean>) generalResult.data;
                mPresenterView.setGoodsInfo(GoodDetailBeanList);
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
