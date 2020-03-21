package com.lr.quartetplatform.moudle2.presenter;

import com.lr.baselibrary.base.BasePresenterImpl;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.NewsBannerBean;
import com.lr.quartetplatform.bean.NewsDetailBean;
import com.lr.quartetplatform.moudle2.activity.NewsDetailActivity;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class NewsDetailPresenter extends BasePresenterImpl<NewsDetailActivity> {
    public void getNewsDetail(String id) {
        OkGoUtils.getRequest(UrlConstant.NEWS_DETAIL + id, "newsBanner", null, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (UiTools.noEmpty(body)) {
                    NewsDetailBean newsDetailBean = GsonUtils.fromJson(body, NewsDetailBean.class);
                    mPresenterView.setNewsDetail(newsDetailBean);
                }
            }
        });
    }
}
