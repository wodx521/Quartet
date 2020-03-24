package com.lr.quartetplatform.moudle3.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.weight.CircleImageView;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ChatListBean;

import java.util.ArrayList;
import java.util.List;

public class ChatListAdapter extends BaseRecycleViewAdapter {
    private List<ChatListBean> chatListBeans;

    public ChatListAdapter(Context context) {
        super(context);
    }

    public List<ChatListBean> getChatListBeans() {
        if (chatListBeans == null) {
            chatListBeans = new ArrayList<>();
        }
        return chatListBeans;
    }

    public void setChatListBeans(List<ChatListBean> chatListBeans) {
        this.chatListBeans = chatListBeans;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_chat;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new ChatListViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        ChatListViewHolder chatListViewHolder = (ChatListViewHolder) viewHolder;
        ChatListBean chatListBean = chatListBeans.get(position);
        chatListViewHolder.tvName.setText(chatListBean.getBpName());
        chatListViewHolder.tvRecentContent.setText(chatListBean.getContent());
        chatListViewHolder.tvTime.setText(chatListBean.getCreatetime());
        GlideApp.with(mContext)
                .load(UrlConstant.IMAGE_BASE_URL + chatListBean.getBpPic())
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(chatListViewHolder.ivHead);
    }

    @Override
    public int getItemCount() {
        if (chatListBeans != null && chatListBeans.size() > 0) {
            return chatListBeans.size();
        }
        return 0;
    }

    static class ChatListViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView ivHead;
        private TextView tvName, tvRecentContent, tvTime;

        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHead = itemView.findViewById(R.id.ivHead);
            tvName = itemView.findViewById(R.id.tvName);
            tvRecentContent = itemView.findViewById(R.id.tvRecentContent);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
