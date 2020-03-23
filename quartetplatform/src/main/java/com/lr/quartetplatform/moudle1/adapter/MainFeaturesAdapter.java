package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.FeatureDetailBean;

import java.util.ArrayList;

public class MainFeaturesAdapter extends BaseRecycleViewAdapter {
    private ArrayList<FeatureDetailBean> featureDetail;

    public MainFeaturesAdapter(Context context) {
        super(context);
    }

    public ArrayList<FeatureDetailBean> getFeatureDetail() {
        if (featureDetail == null) {
            featureDetail = new ArrayList<>();
        }
        return featureDetail;
    }

    public void setFeatureDetail(ArrayList<FeatureDetailBean> featureDetail) {
        this.featureDetail = featureDetail;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_main_features;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new MainFeatureViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        MainFeatureViewHolder mainFeatureViewHolder = (MainFeatureViewHolder) viewHolder;
        FeatureDetailBean featureDetailBean = featureDetail.get(position);
        mainFeatureViewHolder.tvPosition.setText(position + 1 + "");
        mainFeatureViewHolder.tvMainFeatures.setText(featureDetailBean.getTitle());
        mainFeatureViewHolder.tvMainFeaturesDes.setText(featureDetailBean.getDesc());
    }

    @Override
    public int getItemCount() {
        if (featureDetail != null && featureDetail.size() > 0) {
            return featureDetail.size();
        }
        return 0;
    }

    static class MainFeatureViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPosition, tvMainFeatures, tvMainFeaturesDes;

        public MainFeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            tvMainFeatures = itemView.findViewById(R.id.tvMainFeatures);
            tvMainFeaturesDes = itemView.findViewById(R.id.tvMainFeaturesDes);
        }
    }
}
