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
import com.lr.quartetplatform.moudle1.activity.GoodsDetailActivity;
import com.lr.quartetplatform.moudle1.adapter.RecommendAdapter;
import com.lr.quartetplatform.moudle4.presenter.MySchedulePresenter;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

public class MyScheduleActivity extends BaseMvpActivity<MySchedulePresenter> {
    private TextView tvTitle;
    private ImageView ivBack;
    private RecyclerView rvFoot;
    private ConstraintLayout clNoInfo;
    private TextView tvNotice;
    private RecommendAdapter recommendAdapter;
    private HttpParams httpParams = new HttpParams();
    private Bundle bundle = new Bundle();

    @Override
    protected MySchedulePresenter getPresenter() {
        return new MySchedulePresenter();
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.myConcern);
        recommendAdapter = new RecommendAdapter(MyScheduleActivity.this);
        rvFoot.setAdapter(recommendAdapter);

        recommendAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                List<GoodDetailBean> GoodDetailBeanList = recommendAdapter.getGoodDetailBeanList();
                GoodDetailBean goodDetailBean = GoodDetailBeanList.get(position);
                bundle.putParcelable("goodInfo", goodDetailBean);
                bundle.putString("id", goodDetailBean.getId());
                bundle.putString("profuceName", goodDetailBean.getName());
                startActivity(MyScheduleActivity.this, bundle, GoodsDetailActivity.class);
            }
        });
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

    @Override
    protected void onResume() {
        mPresenter.getConcern();
        super.onResume();
    }

    public void loginTimeOut() {

    }

    public void setMyConcern(List<GoodDetailBean> goodsList) {
        recommendAdapter.setGoodDetailBeanList(goodsList);
        if (goodsList != null && goodsList.size() > 0) {
            rvFoot.setVisibility(View.VISIBLE);
            clNoInfo.setVisibility(View.GONE);
        } else {
            rvFoot.setVisibility(View.GONE);
            clNoInfo.setVisibility(View.VISIBLE);
            tvNotice.setText(R.string.noConcern);
        }
    }
}
