package com.lr.quartetplatform.moudle4;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.GsonUtils;
import com.lr.baselibrary.utils.SpUtils;
import com.lr.baselibrary.utils.UiTools;
import com.lr.baselibrary.weight.CircleImageView;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.DataCache;
import com.lr.quartetplatform.bean.RegisterBean;
import com.lr.quartetplatform.bean.UserMe;
import com.lr.quartetplatform.moudle4.activity.LoginActivity;
import com.lr.quartetplatform.moudle4.activity.SettingActivity;
import com.lr.quartetplatform.reaml.RealmUtils;

public class FourFragment extends BaseMvpFragment<FourPresenter> {
    private CircleImageView ivHead;
    private TextView tvRegister, tvNickName, tvFoot, tvCustom, tvSchedule, tvShop;
    private ImageView ivSetting;

    @Override
    protected FourPresenter getPresenter() {
        return new FourPresenter();
    }

    @Override
    protected void initView(View view) {
        ivHead = view.findViewById(R.id.ivHead);
        tvRegister = view.findViewById(R.id.tvRegister);
        tvNickName = view.findViewById(R.id.tvNickName);
        ivSetting = view.findViewById(R.id.ivSetting);
        tvFoot = view.findViewById(R.id.tvFoot);
        tvCustom = view.findViewById(R.id.tvCustom);
        tvSchedule = view.findViewById(R.id.tvSchedule);
        tvShop = view.findViewById(R.id.tvShop);

        tvNickName.setVisibility(View.GONE);
        tvRegister.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initClickListener() {
        ivHead.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        tvFoot.setOnClickListener(this);
        tvCustom.setOnClickListener(this);
        tvSchedule.setOnClickListener(this);
        tvShop.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String phone = (String) SpUtils.get("phone", "");
        if (UiTools.noEmpty(phone) && RealmUtils.queryData(phone + UrlConstant.CACHE_CONSTANT) != null) {
            DataCache dataCache = RealmUtils.queryData(phone + UrlConstant.CACHE_CONSTANT);
            RegisterBean registerBean = GsonUtils.fromJson(dataCache.getCacheContent(), RegisterBean.class);
            if (registerBean != null && registerBean.getUserInfo() != null) {
                mPresenter.getUserIndex();
                tvNickName.setVisibility(View.VISIBLE);
                tvRegister.setVisibility(View.GONE);
//                UserInfo userInfo = registerBean.getUserInfo();
//                String nickname = userInfo.getNickname();
//                String avatar = userInfo.getAvatar();
//                if ((avatar.startsWith("https://") || avatar.startsWith("http://"))) {
//                    GlideApp.with(FourFragment.this)
//                            .load(avatar)
//                            .placeholder(R.drawable.avatar)
//                            .error(R.drawable.avatar)
//                            .into(ivHead);
//                } else {
//                    GlideApp.with(FourFragment.this)
//                            .load(UrlConstant.IMAGE_BASE_URL + avatar)
//                            .placeholder(R.drawable.avatar)
//                            .error(R.drawable.avatar)
//                            .into(ivHead);
//                }
//                tvNickName.setText(nickname);
            } else {
                tvNickName.setVisibility(View.GONE);
                tvRegister.setVisibility(View.VISIBLE);
            }
        } else {
            tvNickName.setVisibility(View.GONE);
            tvRegister.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_four;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHead:
            case R.id.ivSetting:
                startActivityForResult(FourFragment.this, null, UrlConstant.LOGIN, SettingActivity.class);
                break;
            case R.id.tvRegister:
                startActivityForResult(FourFragment.this, null, UrlConstant.LOGIN, LoginActivity.class);
                break;
            case R.id.tvFoot:

                break;
            case R.id.tvCustom:

                break;
            case R.id.tvSchedule:

                break;
            case R.id.tvShop:

                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == UrlConstant.LOGIN) {
            mPresenter.getUserIndex();
//            String phone = (String) SpUtils.get("phone", "");
//            DataCache dataCache = RealmUtils.queryData(phone + UrlConstant.CACHE_CONSTANT);
//            RegisterBean registerBean = GsonUtils.fromJson(dataCache.getCacheContent(), RegisterBean.class);
//            UserInfo userInfo = registerBean.getUserInfo();
//            if (userInfo != null) {
//                String avatar = userInfo.getAvatar();
//                String mobile = userInfo.getMobile();
//                String nickName = userInfo.getNickname();
//                String userName = userInfo.getUsername();
//                if (UiTools.noEmpty(avatar)) {
//                    if ((avatar.startsWith("https://") || avatar.startsWith("http://"))) {
//                        GlideApp.with(FourFragment.this)
//                                .load(avatar)
//                                .placeholder(R.drawable.avatar)
//                                .error(R.drawable.avatar)
//                                .into(ivHead);
//                    } else {
//                        GlideApp.with(FourFragment.this)
//                                .load(UrlConstant.IMAGE_BASE_URL + avatar)
//                                .placeholder(R.drawable.avatar)
//                                .error(R.drawable.avatar)
//                                .into(ivHead);
//                    }
//                }
//                tvNickName.setVisibility(View.VISIBLE);
//                tvRegister.setVisibility(View.GONE);
//                if (UiTools.noEmpty(nickName)) {
//                    tvNickName.setText(nickName);
//                }
//            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getUserMe(UserMe userMe) {
        String phone = (String) SpUtils.get("phone", "");
        String userMeInfo = GsonUtils.toJson(userMe);
        RealmUtils.putCache(phone + UrlConstant.CACHE_USER, userMeInfo);
        tvNickName.setVisibility(View.VISIBLE);
        tvRegister.setVisibility(View.GONE);
        String avatar = userMe.getAvatar();
        if (UiTools.noEmpty(avatar)) {
            if ((avatar.startsWith("https://") || avatar.startsWith("http://"))) {
                GlideApp.with(FourFragment.this)
                        .load(avatar)
                        .placeholder(R.drawable.avatar)
                        .error(R.drawable.avatar)
                        .into(ivHead);
            } else {
                String imageUrl;
                if (avatar.startsWith("nztser.shienkeji.com")) {
                    imageUrl = "https://" + avatar;

                } else {
                    imageUrl = UrlConstant.IMAGE_BASE_URL + avatar;
                }
                GlideApp.with(FourFragment.this)
                        .load(imageUrl)
                        .placeholder(R.drawable.avatar)
                        .error(R.drawable.avatar)
                        .into(ivHead);
            }
        }
        String nickname = userMe.getNickname();
        tvNickName.setText(nickname);
    }

    public void loginTimeOut() {
        GlideApp.with(FourFragment.this)
                .load(R.drawable.avatar)
                .placeholder(R.drawable.avatar)
                .error(R.drawable.avatar)
                .into(ivHead);
        tvNickName.setVisibility(View.GONE);
        tvRegister.setVisibility(View.VISIBLE);
    }
}
