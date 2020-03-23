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
import com.lr.quartetplatform.bean.GoodDetailBean;
import com.lr.quartetplatform.bean.HomeTypeBean;
import com.lr.quartetplatform.moudle1.adapter.RecommendAdapter;
import com.lr.quartetplatform.moudle1.diglog.ChooseFilterDialog;
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
    private List<GoodDetailBean> tempGoodsInfoList;
    private FilterTypeBean mFilterTypeBean;
    private ChooseFilterDialog chooseFilterDialog;

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
                tvTitle.setHint(typeInfo.getTitle());
            }
        }
        // 更多过滤器弹窗
        chooseFilterDialog = new ChooseFilterDialog(TypeDetailActivity.this);
        chooseFilterDialog.setOrderClickListener(new ChooseFilterDialog.OrderClickListener() {
            @Override
            public void onConfirmListener(int type, String position) {
                if (type == 1) {
                    httpParams.put("type", position);
                    tvTitle.setText(position);
                } else {
                    httpParams.put("type", UiTools.getText(tvTitle));
                }

                if (type == 2) {
                    httpParams.put("langlist", position);
                } else {
                    httpParams.put("langlist", "");
                }

                if (type == 3) {
                    httpParams.put("days", position);
                } else {
                    httpParams.put("days", "");
                }
                httpParams.put("sort_page_view", "");
                httpParams.put("sort_days", "");
                mPresenter.getTypeDetail(httpParams);
            }

            @Override
            public void onResetListener() {
                tvTitle.setText(UiTools.getHint(tvTitle));
                httpParams.put("sort_page_view", "");
                httpParams.put("sort_days", "");
                httpParams.put("type", UiTools.getHint(tvTitle));
                httpParams.put("days", "");
                httpParams.put("langlist", "");
                mPresenter.getTypeDetail(httpParams);
            }
        });

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
        List<GoodDetailBean> GoodDetailBeanList;
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
                    recommendAdapter.setGoodDetailBeanList(tempGoodsInfoList);
                }
                break;
            case R.id.tvViewNum:
                GoodDetailBeanList = recommendAdapter.getGoodDetailBeanList();
                if (count1 % 2 == 0) {
                    Collections.sort(GoodDetailBeanList, new ViewUpSort());
                } else {
                    Collections.sort(GoodDetailBeanList, new ViewDownSort());
                }
                tvViewNum.setSelected(true);
                tvCycle.setSelected(false);
                tvViewNum.setChecked(count1 % 2 == 0);
                count1 += 1;
                recommendAdapter.setGoodDetailBeanList(GoodDetailBeanList);
                break;
            case R.id.tvCycle:
                GoodDetailBeanList = recommendAdapter.getGoodDetailBeanList();
                if (count2 % 2 == 0) {
                    Collections.sort(GoodDetailBeanList, new CycleUpSort());
                } else {
                    Collections.sort(GoodDetailBeanList, new CycleDownSort());
                }
                tvCycle.setSelected(true);
                tvViewNum.setSelected(false);
                tvCycle.setChecked(count2 % 2 == 0);
                count2 += 1;
                recommendAdapter.setGoodDetailBeanList(GoodDetailBeanList);
                break;
            case R.id.tvMore:
                if (mFilterTypeBean != null) {
                    chooseFilterDialog.getDialog(mFilterTypeBean);
                }
                break;
            default:
        }
    }

    public void setGoodsInfo(List<GoodDetailBean> GoodDetailBeanList) {
        this.tempGoodsInfoList = GoodDetailBeanList;
        recommendAdapter.setGoodDetailBeanList(GoodDetailBeanList);
    }

    public void setFilterList(FilterTypeBean filterTypeBean) {
        mFilterTypeBean = filterTypeBean;
    }
}
