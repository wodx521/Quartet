package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.SimiliarBean;

import java.util.ArrayList;

public class OtherRecommendAdapter extends BaseRecycleViewAdapter {
    private ArrayList<SimiliarBean> similiar;

    public OtherRecommendAdapter(Context context) {
        super(context);
    }

    public ArrayList<SimiliarBean> getSimiliar() {
        if (similiar == null) {
            similiar = new ArrayList<>();
        }
        return similiar;
    }

    public void setSimiliar(ArrayList<SimiliarBean> similiar) {
        this.similiar = similiar;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_other_project;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new OtherRecommendViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        OtherRecommendViewHolder otherRecommendViewHolder = (OtherRecommendViewHolder) viewHolder;
        SimiliarBean similiarBean = similiar.get(position);
        otherRecommendViewHolder.tvProductName.setText(similiarBean.getName());
        if (UiTools.noEmpty(similiarBean.getHomeimage())) {
            GlideApp.with(mContext)
                    .asBitmap()//强制Glide返回一个Bitmap对象
                    .load(UrlConstant.IMAGE_BASE_URL + similiarBean.getHomeimage())
//                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
//                            Log.e("width_height", "width " + width + "--------height " + height);
                            if (width >= height) {
                                otherRecommendViewHolder.ivBack.setImageResource(R.drawable.good_pc_bg);
                                otherRecommendViewHolder.ivFront.setVisibility(View.INVISIBLE);
                                otherRecommendViewHolder.ivFront2.setVisibility(View.VISIBLE);
                                otherRecommendViewHolder.ivFront2.setImageBitmap(resource);
                            } else {
                                otherRecommendViewHolder.ivBack.setImageResource(R.drawable.good_phone_bg);
                                otherRecommendViewHolder.ivFront2.setVisibility(View.INVISIBLE);
                                otherRecommendViewHolder.ivFront.setVisibility(View.VISIBLE);
                                otherRecommendViewHolder.ivFront.setImageBitmap(resource);
                            }
                        }
                    });
        }

    }

    @Override
    public int getItemCount() {
        if (similiar != null && similiar.size() > 0) {
            return similiar.size();
        }
        return 0;
    }

    static class OtherRecommendViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivBack, ivFront, ivFront2;
        private TextView tvProductName;

        public OtherRecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBack = itemView.findViewById(R.id.ivBack);
            ivFront = itemView.findViewById(R.id.ivFront);
            ivFront2 = itemView.findViewById(R.id.ivFront2);
            tvProductName = itemView.findViewById(R.id.tvProductName);
        }
    }
}
