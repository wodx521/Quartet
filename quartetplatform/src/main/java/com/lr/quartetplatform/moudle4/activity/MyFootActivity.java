package com.lr.quartetplatform.moudle4.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.GoodsInfoBean;
import com.lr.quartetplatform.moudle1.adapter.RecommendAdapter;
import com.lr.quartetplatform.moudle4.presenter.MyFootPresenter;
import com.lzy.okgo.model.HttpParams;

import java.util.List;

public class MyFootActivity extends BaseMvpActivity<MyFootPresenter> {
    private TextView tvTitle;
    private ImageView ivBack;
    private RecyclerView rvFoot;
    private RecommendAdapter recommendAdapter;
    private HttpParams httpParams = new HttpParams();

    @Override
    protected MyFootPresenter getPresenter() {
        return new MyFootPresenter();
    }

    @Override
    protected void initData() {
        recommendAdapter = new RecommendAdapter(MyFootActivity.this);
        rvFoot.setAdapter(recommendAdapter);

        recommendAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

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

    public void setMyFoot(List<GoodsInfoBean> goodsList) {
        recommendAdapter.setGoodsInfoBeanList(goodsList);
    }

    public void loginTimeOut() {

    }
}
