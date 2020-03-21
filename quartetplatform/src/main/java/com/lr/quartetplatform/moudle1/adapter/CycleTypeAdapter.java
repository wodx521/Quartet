package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.CycleTypeBean;

import java.util.ArrayList;
import java.util.List;

public class CycleTypeAdapter extends BaseRecycleViewAdapter {
    private List<CycleTypeBean> goodsDays;

    public CycleTypeAdapter(Context context) {
        super(context);
    }

    public List<CycleTypeBean> getHomeType() {
        if (goodsDays == null) {
            goodsDays = new ArrayList<>();
        }
        return goodsDays;
    }

    public void setHomeType(List<CycleTypeBean> homeType) {
        this.goodsDays = homeType;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_filter;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new CycleTypeViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        CycleTypeViewHolder productStyleViewHolder = (CycleTypeViewHolder) viewHolder;
        productStyleViewHolder.tv1.setText(goodsDays.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (goodsDays != null && goodsDays.size() > 0) {
            return goodsDays.size();
        }
        return 0;
    }

    static class CycleTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;

        public CycleTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }
}
