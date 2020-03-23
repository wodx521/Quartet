package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;

import java.util.ArrayList;
import java.util.List;

public class PcInterfaceAdapter extends BaseRecycleViewAdapter {
    private List<String> imageList;

    public PcInterfaceAdapter(Context context) {
        super(context);
    }

    public List<String> getImageList() {
        if (imageList == null) {
            imageList = new ArrayList<>();
        }
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_image;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new InterfaceViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        InterfaceViewHolder interfaceViewHolder = (InterfaceViewHolder) viewHolder;
        GlideApp.with(mContext)
                .load(UrlConstant.IMAGE_BASE_URL + imageList.get(position))
                .into(interfaceViewHolder.ivImage);
    }

    @Override
    public int getItemCount() {
        if (imageList != null && imageList.size() > 0) {
            return imageList.size();
        }
        return 0;
    }

    static class InterfaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;

        public InterfaceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
