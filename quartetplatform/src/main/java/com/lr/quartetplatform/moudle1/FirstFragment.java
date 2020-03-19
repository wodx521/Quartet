package com.lr.quartetplatform.moudle1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.GoodsInfoBean;
import com.lr.quartetplatform.bean.HomeInfoBean;
import com.lr.quartetplatform.bean.HomeTypeBean;
import com.lr.quartetplatform.moudle1.activity.CustomActivity;
import com.lr.quartetplatform.moudle1.activity.WebActivity;
import com.lr.quartetplatform.moudle1.adapter.ClassificationAdapter;
import com.lr.quartetplatform.moudle1.adapter.ImageAdapter;
import com.lr.quartetplatform.moudle1.adapter.RecommendAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;


public class FirstFragment extends BaseMvpFragment<FirstPresenter> {
    private Banner banner;
    private RecyclerView rvClassification;
    private ImageView ivBg;
    private TabLayout tlChoose;
    private TextView tvClick;
    private RecyclerView rvRecommend;
    private ImageAdapter imageAdapter;
    private List<String> homeNav;
    private List<HomeTypeBean> tempHomeType = new ArrayList<>();
    private RecommendAdapter recommendAdapter;
    private ClassificationAdapter classificationAdapter;
    private Bundle bundle = new Bundle();

    @Override
    protected FirstPresenter getPresenter() {
        return new FirstPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.banner);
        rvClassification = view.findViewById(R.id.rvClassification);
        ivBg = view.findViewById(R.id.ivBg);
        tlChoose = view.findViewById(R.id.tlChoose);
        tvClick = view.findViewById(R.id.tvClick);
        rvRecommend = view.findViewById(R.id.rvRecommend);
    }

    @Override
    protected void initClickListener() {
        tvClick.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        imageAdapter = new ImageAdapter(new ArrayList<String>());
        banner.setAdapter(imageAdapter);
        recommendAdapter = new RecommendAdapter(getActivity());
        rvRecommend.setAdapter(recommendAdapter);
        rvRecommend.setHasFixedSize(true);
        rvRecommend.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                // 查看源码可知State有三种状态：SCROLL_STATE_IDLE（静止）、SCROLL_STATE_DRAGGING（上升）、SCROLL_STATE_SETTLING（下落）
                if (newState == SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源，极大提升流畅度
                    recommendAdapter.setScrolling(false);
                    recommendAdapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
                } else
                    recommendAdapter.setScrolling(true);
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        classificationAdapter = new ClassificationAdapter(getActivity());
        rvClassification.setAdapter(classificationAdapter);
        GlideApp.with(FirstFragment.this)
                .load(R.drawable.home_middle)
                .into(ivBg);
        tlChoose.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (homeNav != null && homeNav.size() > 0) {
                    GlideApp.with(FirstFragment.this)
                            .load(UrlConstant.IMAGE_BASE_URL + homeNav.get(tab.getPosition()))
                            .into(ivBg);
                    if (tab.getPosition() == 0) {
                        tvClick.setText(R.string.inquire);
                    } else if (tab.getPosition() == 1) {
                        tvClick.setText(R.string.join);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mPresenter.getBanner();
        mPresenter.getHomeInfo();
        mPresenter.getGoodsList();
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_first;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvClick:
                if (tlChoose.getSelectedTabPosition() == 0) {
                    // 找软件
                    bundle.clear();
                    bundle.putString("url","http://nztadmin.jinjifuweng.com/#/pages/custom/custom");
                    startActivity(FirstFragment.this, bundle, CustomActivity.class);
                } else if (tlChoose.getSelectedTabPosition() == 1) {
                    // 加联盟
                    bundle.clear();
                    bundle.putString("url","http://nztadmin.jinjifuweng.com/#/pages/my/supplier");
                    startActivity(FirstFragment.this, bundle, WebActivity.class);
                }
                break;
            default:
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void setBanner(List<String> bannerList) {
        imageAdapter.setDatas(bannerList);
    }

    public void setHomeInfo(HomeInfoBean homeInfoBean) {
        tempHomeType.clear();
        List<HomeTypeBean> homeType = homeInfoBean.getHomeType();
        homeNav = homeInfoBean.getHomeNav();
        List<HomeTypeBean> homeRecommend = homeInfoBean.getHomeRecommend();
        if (homeType != null && homeType.size() > 0) {
            HomeTypeBean homeTypeBean = new HomeTypeBean();
            homeTypeBean.setImg("");
            homeTypeBean.setTitle("全部");
            tempHomeType.addAll(homeType.subList(0, 4));
            tempHomeType.add(homeTypeBean);
            classificationAdapter.setHomeType(tempHomeType);
        }
    }

    public void setGoodsInfo(List<GoodsInfoBean> goodsInfoBeanList) {
        recommendAdapter.setGoodsInfoBeanList(goodsInfoBeanList);
    }
}
