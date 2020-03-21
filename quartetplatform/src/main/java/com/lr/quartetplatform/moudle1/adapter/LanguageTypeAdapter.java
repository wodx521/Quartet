package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.quartetplatform.R;

import java.util.ArrayList;
import java.util.List;

public class LanguageTypeAdapter extends BaseRecycleViewAdapter {
    private List<String> homeType;

    public LanguageTypeAdapter(Context context) {
        super(context);
    }

    public List<String> getHomeType() {
        if (homeType == null) {
            homeType = new ArrayList<>();
        }
        return homeType;
    }

    public void setHomeType(List<String> homeType) {
        this.homeType = homeType;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_filter;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new LanguageTypeViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        LanguageTypeViewHolder productStyleViewHolder = (LanguageTypeViewHolder) viewHolder;
        productStyleViewHolder.tv1.setText(homeType.get(position));
    }

    @Override
    public int getItemCount() {
        if (homeType != null && homeType.size() > 0) {
            return homeType.size();
        }
        return 0;
    }

    static class LanguageTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;

        public LanguageTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }
}
