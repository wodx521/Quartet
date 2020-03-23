package com.lr.quartetplatform.moudle2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.NewsBannerBean;
import com.lr.quartetplatform.moudle2.activity.NewsDetailActivity;
import com.lr.quartetplatform.moudle2.adapter.NewsListAdapter;
import com.lzy.okgo.model.HttpParams;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;


public class SecondFragment extends BaseMvpFragment<SecondPresneter> {
    private ImageView ivTop;
    private SmartRefreshLayout srlRefresh;
    private TextView tvTop;
    private RecyclerView rvNews;
    private HttpParams httpParams = new HttpParams();
    private NewsListAdapter newsListAdapter;
    private List<NewsBannerBean> tempNewslist = new ArrayList<>();
    private Bundle bundle = new Bundle();
    private String postId;

    @Override
    protected SecondPresneter getPresenter() {
        return new SecondPresneter();
    }

    @Override
    protected void initView(View view) {
        ivTop = view.findViewById(R.id.ivTop);
        tvTop = view.findViewById(R.id.tvTop);
        srlRefresh = view.findViewById(R.id.srlRefresh);
        rvNews = view.findViewById(R.id.rvNews);
    }

    @Override
    protected void initClickListener() {
        ivTop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.ivTop) {
            if (UiTools.noEmpty(postId)) {
                bundle.putString("id", postId);
                startActivity(SecondFragment.this, bundle, NewsDetailActivity.class);
            }
        }
    }

    @Override
    protected void initData() {
        httpParams.put("column", "id,post_id,title,author_name,cover,published_at");
        mPresenter.getNewsBanner(httpParams);

        mPresenter.getNews(httpParams);

        newsListAdapter = new NewsListAdapter(getActivity());
        rvNews.setAdapter(newsListAdapter);
        rvNews.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        newsListAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                List<NewsBannerBean> newsList = newsListAdapter.getNewsList();
                if (newsList != null && newsList.size() > 0) {
                    NewsBannerBean newsBannerBean = newsList.get(position);
                    bundle.putString("id", newsBannerBean.getPostId());
                    startActivity(SecondFragment.this, bundle, NewsDetailActivity.class);
                }
            }
        });

        srlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                NewsBannerBean newsBannerBean = tempNewslist.get(tempNewslist.size() - 1);
                httpParams.put("minId", newsBannerBean.getId());
                httpParams.put("time", System.currentTimeMillis());
                httpParams.put("pageSize", 10);
                mPresenter.getNews(httpParams);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                tempNewslist.clear();
                httpParams.put("column", "id,post_id,title,author_name,cover,published_at");
                mPresenter.getNews(httpParams);
            }
        });
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_second;
    }

    public void setNewsBanner(NewsBannerBean newsBannerBean) {
        String title = newsBannerBean.getTitle();
        String cover = newsBannerBean.getCover();
        postId = newsBannerBean.getPostId();
        tvTop.setText(title);
        GlideApp.with(SecondFragment.this)
                .load(cover)
                .into(ivTop);
    }

    public void setNewsList(List<NewsBannerBean> newsList) {
        if (srlRefresh.getState() == RefreshState.Refreshing ||
                srlRefresh.getState() == RefreshState.Loading) {
            srlRefresh.finishRefresh();
            srlRefresh.finishLoadMore();
        }
        tempNewslist.addAll(newsList);
        newsListAdapter.setNewsList(tempNewslist);
    }
}
