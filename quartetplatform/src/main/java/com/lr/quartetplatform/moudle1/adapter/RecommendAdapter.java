package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.GoodsInfoBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends BaseRecycleViewAdapter {
    private List<GoodsInfoBean> goodsInfoBeanList;

    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_recommend;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new RecommendViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, final int position) {
        final RecommendViewHolder recommendViewHolder = (RecommendViewHolder) viewHolder;
        GoodsInfoBean goodsInfoBean = goodsInfoBeanList.get(position);
        String name = goodsInfoBean.getName();
        String homeImage = goodsInfoBean.getHomeimage();
        String price = goodsInfoBean.getPrice();
        String type = goodsInfoBean.getType();
        String pageView = goodsInfoBean.getPageView();
        String discount = goodsInfoBean.getDiscount();
        List<String> label = goodsInfoBean.getLabel();
        String days = goodsInfoBean.getDays();

        recommendViewHolder.tvProduceName.setText(name);
        recommendViewHolder.tvType.setText(type);
        recommendViewHolder.tvPrice.setText(String.format(mContext.getString(R.string.defaultDay), days));
        recommendViewHolder.tvViews.setText(pageView);
        if (UiTools.noEmpty(discount)) {
            recommendViewHolder.tvDiscount.setVisibility(View.VISIBLE);
            BigDecimal discountBig = new BigDecimal(discount);
            recommendViewHolder.tvDiscount.setText(discountBig.multiply(BigDecimal.TEN).toPlainString() + "折");
        } else {
            recommendViewHolder.tvDiscount.setVisibility(View.GONE);
        }
        LabelAdapter labelAdapter = new LabelAdapter(mContext, label);
        recommendViewHolder.rvFeatures.setAdapter(labelAdapter);
        labelAdapter.notifyDataSetChanged();
        if (UiTools.noEmpty(homeImage) && !isScrolling && position == (int) recommendViewHolder.itemView.getTag()) {
            GlideApp.with(mContext)
                    .asBitmap()//强制Glide返回一个Bitmap对象
                    .load(UrlConstant.IMAGE_BASE_URL + homeImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            Log.e("width_height", "width " + width + "--------height " + height);
                            if (width > height) {
                                recommendViewHolder.ivBack.setImageResource(R.drawable.good_pc_bg);
                                recommendViewHolder.ivFront.setVisibility(View.INVISIBLE);
                                recommendViewHolder.ivFront2.setVisibility(View.VISIBLE);
                                recommendViewHolder.ivFront2.setImageBitmap(resource);
                            } else if (width < height) {
                                recommendViewHolder.ivBack.setImageResource(R.drawable.good_phone_bg);
                                recommendViewHolder.ivFront2.setVisibility(View.INVISIBLE);
                                recommendViewHolder.ivFront.setVisibility(View.VISIBLE);
                                recommendViewHolder.ivFront.setImageBitmap(resource);
                            }
                        }
                    });
        }
//        else {
//            GlideApp.with(mContext).clear(recommendViewHolder.ivFront2);
//            GlideApp.with(mContext).clear(recommendViewHolder.ivFront);
//        }
    }

    public List<GoodsInfoBean> getGoodsInfoBeanList() {
        if (goodsInfoBeanList == null) {
            goodsInfoBeanList = new ArrayList<>();
        }
        return goodsInfoBeanList;
    }

    public void setGoodsInfoBeanList(List<GoodsInfoBean> goodsInfoBeanList) {
        this.goodsInfoBeanList = goodsInfoBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (goodsInfoBeanList != null && goodsInfoBeanList.size() > 0) {
            return goodsInfoBeanList.size();
        }
        return 0;
    }

    static class RecommendViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivBack, ivFront, ivFront2;
        private RecyclerView rvFeatures;
        private TextView tvDiscount, tvProduceName, tvType, tvPrice, tvViews;

        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBack = itemView.findViewById(R.id.ivBack);
            ivFront = itemView.findViewById(R.id.ivFront);
            ivFront2 = itemView.findViewById(R.id.ivFront2);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            tvProduceName = itemView.findViewById(R.id.tvProduceName);
            tvType = itemView.findViewById(R.id.tvType);
            rvFeatures = itemView.findViewById(R.id.rvFeatures);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvViews = itemView.findViewById(R.id.tvViews);
        }
    }
}
