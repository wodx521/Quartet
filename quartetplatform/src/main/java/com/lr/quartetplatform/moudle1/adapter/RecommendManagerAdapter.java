package com.lr.quartetplatform.moudle1.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.weight.CircleImageView;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ManagerBean;

import java.util.ArrayList;
import java.util.List;

public class RecommendManagerAdapter extends BaseRecycleViewAdapter {
    private List<ManagerBean> managerBeanList;

    public RecommendManagerAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_recommend_manager;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        RecommendManagerViewHolder recommendManagerViewHolder = new RecommendManagerViewHolder(view);
        recommendManagerViewHolder.ivMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 信息点击
                if (advisoryListener!=null) {
                    ManagerBean managerBean = managerBeanList.get((Integer) recommendManagerViewHolder.itemView.getTag());
                    advisoryListener.msgListener(managerBean);
                }
            }
        });
        recommendManagerViewHolder.ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 电话点击
                if (advisoryListener!=null) {
                    ManagerBean managerBean = managerBeanList.get((Integer) recommendManagerViewHolder.itemView.getTag());
                    advisoryListener.phoneListener(managerBean);
                }
            }
        });
        return recommendManagerViewHolder;
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        RecommendManagerViewHolder recommendManagerViewHolder = (RecommendManagerViewHolder) viewHolder;
        ManagerBean managerBean = managerBeanList.get(position);
        recommendManagerViewHolder.tvManagerName.setText(managerBean.getNickname());
        GlideApp.with(mContext)
                .load(UrlConstant.IMAGE_BASE_URL + managerBean.getAvatar())
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(recommendManagerViewHolder.ivHead);
    }

    public List<ManagerBean> getManagerBeanList() {
        if (managerBeanList == null) {
            managerBeanList = new ArrayList<>();
        }
        return managerBeanList;
    }

    public void setManagerBeanList(List<ManagerBean> managerBeanList) {
        this.managerBeanList = managerBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (managerBeanList != null && managerBeanList.size() > 0) {
            return managerBeanList.size();
        }
        return 0;
    }

    public interface AdvisoryListener {
        void msgListener( ManagerBean managerBean);

        void phoneListener( ManagerBean managerBean);
    }

   private AdvisoryListener advisoryListener;

    public void setAdvisoryListener(AdvisoryListener advisoryListener) {
        this.advisoryListener = advisoryListener;
    }

    static class RecommendManagerViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView ivHead;
        private TextView tvManagerName;
        private ImageView ivMsg, ivPhone;

        public RecommendManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHead = itemView.findViewById(R.id.ivHead);
            tvManagerName = itemView.findViewById(R.id.tvManagerName);
            ivMsg = itemView.findViewById(R.id.ivMsg);
            ivPhone = itemView.findViewById(R.id.ivPhone);
        }
    }
}
