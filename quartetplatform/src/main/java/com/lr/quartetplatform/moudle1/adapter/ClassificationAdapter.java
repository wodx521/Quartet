package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.HomeTypeBean;

import java.util.ArrayList;
import java.util.List;

public class ClassificationAdapter extends BaseRecycleViewAdapter {
    private List<HomeTypeBean> homeType;

    public ClassificationAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_home_cat;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new ClassificationViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        ClassificationViewHolder classificationViewHolder = (ClassificationViewHolder) viewHolder;
        HomeTypeBean homeTypeBean = homeType.get(position);
        classificationViewHolder.tv1.setText(homeTypeBean.getTitle());
        if (UiTools.noEmpty(homeTypeBean.getImg())) {
            GlideApp.with(mContext)
                    .asBitmap()
                    .load(UrlConstant.IMAGE_BASE_URL + homeTypeBean.getImg())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            classificationViewHolder.ivIcon.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        } else {
            GlideApp.with(mContext)
                    .asBitmap()
                    .load(R.drawable.all)
                    .into(classificationViewHolder.ivIcon);
        }
    }

    public List<HomeTypeBean> getHomeType() {
        if (homeType == null) {
            homeType = new ArrayList<>();
        }
        return homeType;
    }

    public void setHomeType(List<HomeTypeBean> homeType) {
        this.homeType = homeType;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (homeType != null && homeType.size() > 0) {
            return homeType.size();
        }
        return 0;
    }

    static class ClassificationViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private ImageView ivIcon;

        public ClassificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}
