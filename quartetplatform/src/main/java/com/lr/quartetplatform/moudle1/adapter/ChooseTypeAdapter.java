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

public class ChooseTypeAdapter extends BaseRecycleViewAdapter {
    private List<String> content;

    public ChooseTypeAdapter(Context context) {
        super(context);
    }

    public List<String> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_choose;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new ChooseTypeViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        ChooseTypeViewHolder chooseTypeViewHolder = (ChooseTypeViewHolder) viewHolder;
        chooseTypeViewHolder.tvChoose.setText(content.get(position));
    }

    @Override
    public int getItemCount() {
        if (content != null && content.size() > 0) {
            return content.size();
        }
        return 0;
    }

    static class ChooseTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChoose;

        public ChooseTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChoose = itemView.findViewById(R.id.tvChoose);
        }
    }
}
