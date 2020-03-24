package com.lr.quartetplatform.moudle3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.ChatListBean;
import com.lr.quartetplatform.moudle3.activity.ChatDetailActivity;
import com.lr.quartetplatform.moudle3.adapter.ChatListAdapter;

import java.util.List;


public class ThirdFragment extends BaseMvpFragment<ThirdPresenter> {
    private RecyclerView rvChatList;
    private ChatListAdapter chatListAdapter;
    private ConstraintLayout clNoInfo;
    private TextView tvNotice;
    private Bundle bundle = new Bundle();
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mPresenter.getChatList();
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    protected ThirdPresenter getPresenter() {
        return new ThirdPresenter();
    }

    public void setChatList(List<ChatListBean> chatListBeans) {
        chatListAdapter.setChatListBeans(chatListBeans);
        if (chatListBeans != null && chatListBeans.size() > 0) {
            clNoInfo.setVisibility(View.GONE);
            rvChatList.setVisibility(View.VISIBLE);
        } else {
            clNoInfo.setVisibility(View.VISIBLE);
            rvChatList.setVisibility(View.GONE);
            tvNotice.setText(R.string.noMsg);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            OkGoUtils.cancelConnect("chatList");
            handler.removeCallbacks(runnable);
        } else {
            handler.post(runnable);
        }
    }

    @Override
    protected void initView(View view) {
        rvChatList = view.findViewById(R.id.rvChatList);
        clNoInfo = view.findViewById(R.id.clNoInfo);
        tvNotice = view.findViewById(R.id.tvNotice);
        rvChatList.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
        chatListAdapter = new ChatListAdapter(getActivity());
        rvChatList.setAdapter(chatListAdapter);
        chatListAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                List<ChatListBean> chatListBeans = chatListAdapter.getChatListBeans();
                if (chatListBeans != null && chatListBeans.size() > 0) {
                    ChatListBean chatListBean = chatListBeans.get(position);
                    bundle.putParcelable("chatUser", chatListBean);
                    startActivity(ThirdFragment.this, bundle, ChatDetailActivity.class);
                }
            }
        });
    }

    @Override
    public void onResume() {
        if (getUserVisibleHint()) {
            handler.post(runnable);
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        OkGoUtils.cancelConnect("chatList");
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_third;
    }
}
