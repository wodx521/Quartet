package com.lr.quartetplatform.moudle4.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.GoodDetailBean;
import com.lr.quartetplatform.moudle1.FirstFragment;
import com.lr.quartetplatform.moudle1.activity.GoodsDetailActivity;
import com.lr.quartetplatform.moudle1.adapter.RecommendAdapter;
import com.lr.quartetplatform.moudle4.presenter.MyFootPresenter;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

public class MyFootActivity extends BaseMvpActivity<MyFootPresenter> {
    private TextView tvTitle;
    private ImageView ivBack;
    private RecyclerView rvFoot;
    private ConstraintLayout clNoInfo;
    private TextView tvNotice;
    private RecommendAdapter recommendAdapter;
    private HttpParams httpParams = new HttpParams();
    private Bundle bundle = new Bundle();

    @Override
    protected MyFootPresenter getPresenter() {
        return new MyFootPresenter();
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.myFoot);
        recommendAdapter = new RecommendAdapter(MyFootActivity.this);
        rvFoot.setAdapter(recommendAdapter);

        recommendAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                List<GoodDetailBean> GoodDetailBeanList = recommendAdapter.getGoodDetailBeanList();
                GoodDetailBean goodDetailBean = GoodDetailBeanList.get(position);

                bundle.putParcelable("goodInfo",goodDetailBean);
                bundle.putString("id",goodDetailBean.getId());
                bundle.putString("profuceName",goodDetailBean.getName());
                startActivity(MyFootActivity.this,bundle, GoodsDetailActivity.class);
            }
        });
        httpParams.clear();
        httpParams.put("list", "2");
        mPresenter.getFoot(httpParams);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_my_foot;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        rvFoot = findViewById(R.id.rvFoot);
        clNoInfo = findViewById(R.id.clNoInfo);
        tvNotice = findViewById(R.id.tvNotice);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack) {
            finish();
        }
    }

    public void setMyFoot(List<GoodDetailBean> goodsList) {
        recommendAdapter.setGoodDetailBeanList(goodsList);
        if (goodsList != null && goodsList.size() > 0) {
            rvFoot.setVisibility(View.VISIBLE);
            clNoInfo.setVisibility(View.GONE);
        } else {
            rvFoot.setVisibility(View.GONE);
            clNoInfo.setVisibility(View.VISIBLE);
            tvNotice.setText(R.string.noFoot);
        }
    }

    public void loginTimeOut() {

    }
}
