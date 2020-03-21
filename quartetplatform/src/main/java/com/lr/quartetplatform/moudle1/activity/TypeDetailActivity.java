package com.lr.quartetplatform.moudle1.activity;

import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.CycleDownSort;
import com.lr.quartetplatform.CycleUpSort;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.ViewDownSort;
import com.lr.quartetplatform.ViewUpSort;
import com.lr.quartetplatform.bean.FilterTypeBean;
import com.lr.quartetplatform.bean.GoodsInfoBean;
import com.lr.quartetplatform.bean.HomeTypeBean;
import com.lr.quartetplatform.moudle1.adapter.RecommendAdapter;
import com.lr.quartetplatform.moudle1.presenter.TypeDetailPresenter;
import com.lzy.okgo.model.HttpParams;

import java.util.Collections;
import java.util.List;

public class TypeDetailActivity extends BaseMvpActivity<TypeDetailPresenter> {
    private ImageView ivBack;
    private TextView tvTitle, tvSortTotal, tvMore;
    private CheckedTextView tvViewNum, tvCycle;
    private RecyclerView rvAllType;
    private RecommendAdapter recommendAdapter;
    private HttpParams httpParams = new HttpParams();
    private int count1 = 0;
    private int count2 = 0;
    private List<GoodsInfoBean> tempGoodsInfoList;
    private FilterTypeBean mFilterTypeBean;

    @Override
    protected TypeDetailPresenter getPresenter() {
        return new TypeDetailPresenter();
    }

    @Override
    protected void initData() {
        if (mBundle != null) {
            HomeTypeBean typeInfo = mBundle.getParcelable("typeInfo");
            if (typeInfo != null) {
                tvTitle.setText(typeInfo.getTitle());
            }
        }
        recommendAdapter = new RecommendAdapter(TypeDetailActivity.this);
        rvAllType.setAdapter(recommendAdapter);

        recommendAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

            }
        });
        httpParams.put("sort_page_view", "");
        httpParams.put("sort_days", "");
        httpParams.put("type", UiTools.getText(tvTitle));
        httpParams.put("days", "");
        httpParams.put("langlist", "");
        mPresenter.getTypeDetail(httpParams);

        mPresenter.getFilter();
    }

    @Override
    protected int getResId() {
        return R.layout.activity_type_detail;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        tvSortTotal = findViewById(R.id.tvSortTotal);
        tvViewNum = findViewById(R.id.tvViewNum);
        tvCycle = findViewById(R.id.tvCycle);
        tvMore = findViewById(R.id.tvMore);
        rvAllType = findViewById(R.id.rvAllType);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvSortTotal.setOnClickListener(this);
        tvViewNum.setOnClickListener(this);
        tvCycle.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        List<GoodsInfoBean> goodsInfoBeanList;
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvSortTotal:
                count1 = 0;
                count2 = 0;
                tvViewNum.setSelected(false);
                tvCycle.setSelected(false);
                if (tempGoodsInfoList != null && tempGoodsInfoList.size() > 0) {
                    recommendAdapter.setGoodsInfoBeanList(tempGoodsInfoList);
                }
                break;
            case R.id.tvViewNum:
                goodsInfoBeanList = recommendAdapter.getGoodsInfoBeanList();
                if (count1 % 2 == 0) {
                    Collections.sort(goodsInfoBeanList, new ViewUpSort());
                } else {
                    Collections.sort(goodsInfoBeanList, new ViewDownSort());
                }
                tvViewNum.setSelected(true);
                tvCycle.setSelected(false);
                tvViewNum.setChecked(count1 % 2 == 0);
                count1 += 1;
                recommendAdapter.setGoodsInfoBeanList(goodsInfoBeanList);
                break;
            case R.id.tvCycle:
                goodsInfoBeanList = recommendAdapter.getGoodsInfoBeanList();
                if (count2 % 2 == 0) {
                    Collections.sort(goodsInfoBeanList, new CycleUpSort());
                } else {
                    Collections.sort(goodsInfoBeanList, new CycleDownSort());
                }
                tvCycle.setSelected(true);
                tvViewNum.setSelected(false);
                tvCycle.setChecked(count2 % 2 == 0);
                count2 += 1;
                recommendAdapter.setGoodsInfoBeanList(goodsInfoBeanList);
                break;
            case R.id.tvMore:


                break;
            default:
        }
    }

    public void setGoodsInfo(List<GoodsInfoBean> goodsInfoBeanList) {
        this.tempGoodsInfoList = goodsInfoBeanList;
        recommendAdapter.setGoodsInfoBeanList(goodsInfoBeanList);
    }

    public void setFilterList(FilterTypeBean filterTypeBean) {
        mFilterTypeBean = filterTypeBean;
    }
}
