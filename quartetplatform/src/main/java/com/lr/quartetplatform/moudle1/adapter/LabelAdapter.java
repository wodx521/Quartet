package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.quartetplatform.R;

import java.util.List;

public class LabelAdapter extends BaseRecycleViewAdapter {
    private List<String> label;

    public LabelAdapter(Context context) {
        super(context);
    }

    public LabelAdapter(Context context, List<String> label) {
        super(context);
        this.label = label;
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_label;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new LabelViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        LabelViewHolder labelViewHolder = (LabelViewHolder) viewHolder;
        labelViewHolder.tv1.setText(label.get(position));
    }

    @Override
    public int getItemCount() {
        if (label != null && label.size() > 0) {
            return label.size();
        }
        return 0;
    }

    static class LabelViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;

        public LabelViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }
}
