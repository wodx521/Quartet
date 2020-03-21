package com.lr.quartetplatform.moudle2;

import com.google.gson.reflect.TypeToken;
import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.NewsBannerBean;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.List;

public class SecondPresneter extends BasePresenterImpl<SecondFragment> {
    public void getNewsBanner(HttpParams httpParams) {
        OkGoUtils.getRequest(UrlConstant.NEWS_BANNER, "newsBanner", httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (UiTools.noEmpty(body)) {
                    NewsBannerBean newsBannerBean = GsonUtils.fromJson(body, NewsBannerBean.class);
                    mPresenterView.setNewsBanner(newsBannerBean);
                }
            }
        });
    }

    public void getNews(HttpParams httpParams) {
        OkGoUtils.getRequest(UrlConstant.NEWS_LIST, "newsList", httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (UiTools.noEmpty(body)) {
                    List<NewsBannerBean> newsList = GsonUtils.fromJson(body, new TypeToken<List<NewsBannerBean>>() {
                    }.getType());
                    mPresenterView.setNewsList(newsList);
                }
            }
        });
    }
}
