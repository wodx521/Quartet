package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;

import java.util.ArrayList;
import java.util.List;

public class UploadAdapter extends BaseRecycleViewAdapter {
    private List<String> imageList;
    private ClickListener clickListener;

    public UploadAdapter(Context context) {
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
        return R.layout.item_upload;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        UploadViewHolder uploadViewHolder = new UploadViewHolder(view);
        uploadViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.deleteListener((Integer) uploadViewHolder.itemView.getTag());
                }
            }
        });
        uploadViewHolder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.addListener((Integer) uploadViewHolder.itemView.getTag());
                }
            }
        });
        return uploadViewHolder;
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        UploadViewHolder uploadViewHolder = (UploadViewHolder) viewHolder;
        if (UiTools.noEmpty(imageList.get(position))) {
            uploadViewHolder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            uploadViewHolder.ivDelete.setVisibility(View.INVISIBLE);
        }
        GlideApp.with(mContext)
                .load(UrlConstant.IMAGE_BASE_URL + imageList.get(position))
                .placeholder(R.drawable.add_picture)
                .into(uploadViewHolder.ivImage);
    }

    @Override
    public int getItemCount() {
        if (imageList != null && imageList.size() > 0) {
            if (imageList.size() > 4) {
                return 4;
            }
            return imageList.size();
        }
        return 0;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void addListener(int position);

        void deleteListener(int position);
    }

    static class UploadViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage, ivDelete;

        public UploadViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
