package com.lr.quartetplatform.moudle3.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.okgoutil.OkGoUtils;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.SpUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ChatInfoBean;
import com.lr.quartetplatform.bean.ChatListBean;
import com.lr.quartetplatform.bean.DataCache;
import com.lr.quartetplatform.bean.RegisterBean;
import com.lr.quartetplatform.bean.UserInfo;
import com.lr.quartetplatform.moudle3.adapter.ChatInfoAdapter;
import com.lr.quartetplatform.moudle3.presenter.ChatDetailPresenter;
import com.lr.quartetplatform.reaml.RealmUtils;
import com.lzy.okgo.model.HttpParams;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

public class ChatDetailActivity extends BaseMvpActivity<ChatDetailPresenter> {
    private TextView tvTitle, tvCallPhone, tvSend;
    private ImageView ivBack;
    private RecyclerView rvChat;
    private EditText etChatContent;
    private String bpPhone;
    private RxPermissions rxPermissions;
    private HttpParams httpParams = new HttpParams();
    private ChatInfoAdapter chatInfoAdapter;
    private String chatId;
    private String type;
    private boolean isBottom = true;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (UiTools.noEmpty(chatId)) {
                httpParams.clear();
                httpParams.put("id", chatId);
                mPresenter.getChatInfo(httpParams);
                handler.postDelayed(this, 3000);
            }
        }
    };

    @Override
    protected ChatDetailPresenter getPresenter() {
        return new ChatDetailPresenter();
    }

    @Override
    protected void initData() {
        String phone = (String) SpUtils.get("phone", "");
        if (UiTools.noEmpty(phone)) {
            DataCache dataCache = RealmUtils.queryData(phone + UrlConstant.CACHE_CONSTANT);
            if (dataCache != null) {
                String cacheContent = dataCache.getCacheContent();
                RegisterBean registerBean = GsonUtils.fromJson(cacheContent, RegisterBean.class);
                if (registerBean != null && registerBean.getUserInfo() != null) {
                    UserInfo userInfo = registerBean.getUserInfo();
                    String groupId = userInfo.getGroupId();
                    if ("2".equals(groupId)) {
                        type = "2";
                    } else {
                        type = "1";
                    }
                }
            }
        }

        rxPermissions = new RxPermissions(ChatDetailActivity.this);
        if (mBundle != null) {
            ChatListBean chatListBean = mBundle.getParcelable("chatUser");
            if (chatListBean != null) {
                tvTitle.setText(chatListBean.getBpName());
                bpPhone = chatListBean.getBpPhone();
                chatId = chatListBean.getChatId();
//                httpParams.clear();
//                httpParams.put("id", chatId);
//                mPresenter.getChatInfo(httpParams);
            }
        }

        chatInfoAdapter = new ChatInfoAdapter(ChatDetailActivity.this);
        rvChat.setAdapter(chatInfoAdapter);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_chat_detail;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        rvChat = findViewById(R.id.rvChat);
        tvCallPhone = findViewById(R.id.tvCallPhone);
        etChatContent = findViewById(R.id.etChatContent);
        tvSend = findViewById(R.id.tvSend);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        tvCallPhone.setOnClickListener(this);
        tvSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvCallPhone:
                // 电话咨询
                rxPermissions.request(Manifest.permission.CALL_PHONE)
                        .subscribe(granted -> {
                            if (granted) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + bpPhone));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                // 权限被拒绝
                                Toast.makeText(ChatDetailActivity.this, "权限被拒绝，无法使用", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.tvSend:
                String content = UiTools.getText(etChatContent);
                if (UiTools.noEmpty(content)) {
                    httpParams.clear();
                    httpParams.put("id", chatId);
                    httpParams.put("content", content);
                    httpParams.put("type", type);
                    mPresenter.sendChatInfo(httpParams);
                } else {
                    UiTools.showToast(R.string.inputChatContent);
                }
                break;
            default:
        }
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable);
        OkGoUtils.cancelConnect("chatList");
        super.onPause();
    }

    @Override
    protected void onResume() {
        handler.post(runnable);
        super.onResume();
    }

    public void setChatInfo(List<ChatInfoBean> chatInfoBeans) {
        chatInfoAdapter.setChatInfoBeans(chatInfoBeans);
        if (isBottom) {
            isBottom = false;
            rvChat.scrollToPosition(chatInfoAdapter.getChatInfoBeans().size() - 1);
        }
    }

    public void sendSuccess() {
        isBottom = true;
        etChatContent.setText("");
        httpParams.clear();
        httpParams.put("id", chatId);
        mPresenter.getChatInfo(httpParams);
    }
}
