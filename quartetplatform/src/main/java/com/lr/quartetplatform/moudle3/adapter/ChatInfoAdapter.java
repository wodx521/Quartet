package com.lr.quartetplatform.moudle3.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.glidetools.GlideRequest;
import com.lr.baselibrary.weight.CircleImageView;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ChatInfoBean;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatInfoAdapter extends RecyclerView.Adapter {
    private static final int SELF = 2;
    private static final int OTHER = 1;
    private Activity activity;
    private List<ChatInfoBean> chatInfoBeans;
    private LayoutInflater inflater;
    private View view;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public ChatInfoAdapter(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    public List<ChatInfoBean> getChatInfoBeans() {
        if (chatInfoBeans == null) {
            chatInfoBeans = new ArrayList<>();
        }
        return chatInfoBeans;
    }

    public void setChatInfoBeans(List<ChatInfoBean> chatInfoBeans) {
        this.chatInfoBeans = chatInfoBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.item_other, parent, false);
        return new ChatInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatInfoViewHolder chatInfoViewHolder = (ChatInfoViewHolder) holder;
        int itemViewType = getItemViewType(position);
        ChatInfoBean chatInfoBean = chatInfoBeans.get(position);
        String content = chatInfoBean.getContent();
        String pic = chatInfoBean.getPic();
        String createtime = chatInfoBean.getCreatetime();
        Date date = simpleDateFormat.parse(createtime, new ParsePosition(0));
        if (chatInfoBeans.size() > 1 && position > 0) {
            ChatInfoBean chatInfoBean1 = chatInfoBeans.get(position - 1);
            String createtime1 = chatInfoBean1.getCreatetime();
            Date date1 = simpleDateFormat.parse(createtime1, new ParsePosition(0));
            long time1 = date1.getTime();
            long time = date.getTime();
            if (time - time1 > 300000) {
                chatInfoViewHolder.tvTime.setVisibility(View.VISIBLE);
            } else {
                chatInfoViewHolder.tvTime.setVisibility(View.GONE);
            }
        } else {
            chatInfoViewHolder.tvTime.setVisibility(View.VISIBLE);
        }

        chatInfoViewHolder.tvTime.setText(createtime);
        GlideRequest<Drawable> glideRequest = GlideApp.with(activity)
                .load(UrlConstant.IMAGE_BASE_URL + pic)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar);
        if (itemViewType == SELF) {
            // 右边消息
            chatInfoViewHolder.llLeft.setVisibility(View.GONE);
            chatInfoViewHolder.rlRight.setVisibility(View.VISIBLE);
            chatInfoViewHolder.tvRightContent.setText(content);
            glideRequest.into(chatInfoViewHolder.ivRightIcon);
        } else {
            // 左边消息
            chatInfoViewHolder.llLeft.setVisibility(View.VISIBLE);
            chatInfoViewHolder.rlRight.setVisibility(View.GONE);
            chatInfoViewHolder.tvLeftContent.setText(content);
            glideRequest.into(chatInfoViewHolder.ivLeftAvatar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chatInfoBeans != null && chatInfoBeans.size() > 0) {
            ChatInfoBean chatInfoBean = chatInfoBeans.get(position);
            if ("1".equals(chatInfoBean.getType())) {
                // 客户自己发的消息
                return SELF;
            } else {
                return OTHER;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (chatInfoBeans != null && chatInfoBeans.size() > 0) {
            return chatInfoBeans.size();
        }
        return 0;
    }

    static class ChatInfoViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llLeft;
        private RelativeLayout rlRight;
        private CircleImageView ivLeftAvatar, ivRightIcon;
        private ImageView ivChatImage, ivRightImage;
        private TextView tvLeftContent, tvRightContent, tvTime;

        public ChatInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            llLeft = itemView.findViewById(R.id.llLeft);
            ivLeftAvatar = itemView.findViewById(R.id.ivLeftAvatar);
            tvLeftContent = itemView.findViewById(R.id.tvLeftContent);
            ivChatImage = itemView.findViewById(R.id.ivChatImage);
            rlRight = itemView.findViewById(R.id.rlRight);
            ivRightImage = itemView.findViewById(R.id.ivRightImage);
            tvRightContent = itemView.findViewById(R.id.tvRightContent);
            ivRightIcon = itemView.findViewById(R.id.ivRightAvatar);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

}
