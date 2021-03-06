package com.lr.quartetplatform.moudle1.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.baselibrary.utils.UiTools;
import com.lr.baselibrary.weight.CircleImageView;
import com.lr.quartetplatform.HtmlImageGetter;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.UrlConstant;
import com.lr.quartetplatform.bean.ChatListBean;
import com.lr.quartetplatform.bean.FeatureDetailBean;
import com.lr.quartetplatform.bean.GoodDetailBean;
import com.lr.quartetplatform.bean.ManagerBean;
import com.lr.quartetplatform.bean.SimiliarBean;
import com.lr.quartetplatform.moudle1.adapter.LabelAdapter;
import com.lr.quartetplatform.moudle1.adapter.MainFeaturesAdapter;
import com.lr.quartetplatform.moudle1.adapter.OtherRecommendAdapter;
import com.lr.quartetplatform.moudle1.adapter.PcInterfaceAdapter;
import com.lr.quartetplatform.moudle1.adapter.RecommendManagerAdapter;
import com.lr.quartetplatform.moudle1.presenter.GoodsDetailPresenter;
import com.lr.quartetplatform.moudle3.activity.ChatDetailActivity;
import com.lzy.okgo.model.HttpParams;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsDetailActivity extends BaseMvpActivity<GoodsDetailPresenter> {
    private ImageView ivBack, ivBg, ivFront, ivFront2;
    private RecyclerView rvFeatures, rvProductFeatures, rvMainFeatures, rvInterface1, rvInterface2,
            rvRecommend, rvOther;
    private CircleImageView ivHead;
    private TextView tvTitle, tvProductName, tvProductDes, tvProductPrice, tvViewDetail,
            tvReservation, tvManagerName, tvMsg, tvPhoneContact, tvProductFeatures;
    private HttpParams httpParams = new HttpParams();
    private RecommendManagerAdapter recommendManagerAdapter;
    private MainFeaturesAdapter mainFeaturesAdapter;
    private PcInterfaceAdapter pcInterfaceAdapter;
    private PcInterfaceAdapter pcInterfaceAdapter1;
    private OtherRecommendAdapter otherRecommendAdapter;
    private Bundle bundle = new Bundle();
    private String modao;
    private String id;
    private RxPermissions rxPermissions;
    private CheckedTextView tvIsConcern;

    @Override
    protected GoodsDetailPresenter getPresenter() {
        return new GoodsDetailPresenter();
    }

    @Override
    protected void initData() {
        rxPermissions = new RxPermissions(GoodsDetailActivity.this);

        // 推荐人列表
        recommendManagerAdapter = new RecommendManagerAdapter(GoodsDetailActivity.this);
        rvRecommend.setAdapter(recommendManagerAdapter);
        recommendManagerAdapter.setAdvisoryListener(new RecommendManagerAdapter.AdvisoryListener() {
            @Override
            public void msgListener(ManagerBean managerBean) {
                // 推荐人列表发送消息
                httpParams.clear();
                httpParams.put("project_id", id);
                httpParams.put("bp_id", managerBean.getId());
                mPresenter.chatSeek(httpParams);
            }

            @Override
            public void phoneListener(ManagerBean managerBean) {
                // 推荐人列表打电话操作
                rxPermissions.request(Manifest.permission.CALL_PHONE)
                        .subscribe(granted -> {
                            if (granted) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + managerBean.getMobile()));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                // 权限被拒绝
                                Toast.makeText(GoodsDetailActivity.this, "权限被拒绝，无法使用", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // 核心特色
        mainFeaturesAdapter = new MainFeaturesAdapter(GoodsDetailActivity.this);
        rvMainFeatures.setAdapter(mainFeaturesAdapter);
        // pc 截面图
        pcInterfaceAdapter = new PcInterfaceAdapter(GoodsDetailActivity.this);
        rvInterface1.setAdapter(pcInterfaceAdapter);
        // app 界面图
        pcInterfaceAdapter1 = new PcInterfaceAdapter(GoodsDetailActivity.this);
        rvInterface2.setAdapter(pcInterfaceAdapter1);

        // 类似应用推荐
        otherRecommendAdapter = new OtherRecommendAdapter(GoodsDetailActivity.this);
        rvOther.setAdapter(otherRecommendAdapter);

        otherRecommendAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                ArrayList<SimiliarBean> similiar = otherRecommendAdapter.getSimiliar();
                if (similiar != null && similiar.size() > 0) {
                    SimiliarBean similiarBean = similiar.get(position);
                    bundle.putString("id", similiarBean.getId());
                    bundle.putString("profuceName", similiarBean.getName());
                    startActivity(GoodsDetailActivity.this, bundle, GoodsDetailActivity.class);
                }
            }
        });

        if (mBundle != null) {
            httpParams.clear();
            GoodDetailBean goodDetailBean = mBundle.getParcelable("goodInfo");
            String profuceName = mBundle.getString("profuceName");
            id = mBundle.getString("id");
            if (goodDetailBean != null) {
                tvTitle.setText(goodDetailBean.getName());
                httpParams.put("id", goodDetailBean.getId());
                httpParams.put("project_id", goodDetailBean.getId());
            } else {
                httpParams.put("id", id);
                httpParams.put("project_id", id);
                tvTitle.setText(profuceName);
            }
            mPresenter.getGoodsDetail(httpParams);
            mPresenter.getShopBps(httpParams);
        }
    }

    @Override
    protected int getResId() {
        return R.layout.activity_good_detail;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        ivBg = findViewById(R.id.ivBg);
        ivFront = findViewById(R.id.ivFront);
        ivFront2 = findViewById(R.id.ivFront2);
        tvProductName = findViewById(R.id.tvProductName);
        tvIsConcern = findViewById(R.id.tvIsConcern);
        tvProductFeatures = findViewById(R.id.tvProductFeatures);
        tvProductDes = findViewById(R.id.tvProductDes);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvViewDetail = findViewById(R.id.tvViewDetail);
        rvFeatures = findViewById(R.id.rvFeatures);
        rvProductFeatures = findViewById(R.id.rvProductFeatures);
        tvReservation = findViewById(R.id.tvReservation);
        rvMainFeatures = findViewById(R.id.rvMainFeatures);
        rvInterface1 = findViewById(R.id.rvInterface1);
        rvInterface2 = findViewById(R.id.rvInterface2);
        rvRecommend = findViewById(R.id.rvRecommend);
        rvOther = findViewById(R.id.rvOther);
        ivHead = findViewById(R.id.ivHead);
        tvManagerName = findViewById(R.id.tvManagerName);
        tvMsg = findViewById(R.id.tvMsg);
        tvPhoneContact = findViewById(R.id.tvPhoneContact);
    }

    @Override
    protected void initClickListener() {
        ivBack.setOnClickListener(this);
        ivBg.setOnClickListener(this);
        ivFront.setOnClickListener(this);
        ivFront2.setOnClickListener(this);
        tvViewDetail.setOnClickListener(this);
        tvReservation.setOnClickListener(this);
        tvMsg.setOnClickListener(this);
        tvPhoneContact.setOnClickListener(this);
        tvIsConcern.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivBg:
            case R.id.ivFront:
            case R.id.ivFront2:
            case R.id.tvViewDetail:
                // 查看详情跳转磨刀图
                if (UiTools.noEmpty(modao)) {
                    bundle.putString("url", modao);
                    startActivity(GoodsDetailActivity.this, bundle, WebActivity.class);
                }
                break;
            case R.id.tvMsg:
                // 在线咨询
                List<ManagerBean> managerBeanList = recommendManagerAdapter.getManagerBeanList();
                if (managerBeanList != null && managerBeanList.size() > 0) {
                    ManagerBean managerBean = managerBeanList.get(0);
                    httpParams.clear();
                    httpParams.put("project_id", id);
                    httpParams.put("bp_id", managerBean.getId());
                    mPresenter.chatSeek(httpParams);
                }

                break;
            case R.id.tvPhoneContact:
                // 电话咨询
                rxPermissions.request(Manifest.permission.CALL_PHONE)
                        .subscribe(granted -> {
                            if (granted) {
                                List<ManagerBean> managerBeanList1 = recommendManagerAdapter.getManagerBeanList();
                                if (managerBeanList1 != null && managerBeanList1.size() > 0) {
                                    ManagerBean managerBean = managerBeanList1.get(0);
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + managerBean.getMobile()));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            } else {
                                // 权限被拒绝
                                Toast.makeText(GoodsDetailActivity.this, "权限被拒绝，无法使用", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.tvReservation:
                // 预约一对一咨询
                bundle.clear();
                bundle.putString("id", id);
                startActivity(GoodsDetailActivity.this, bundle, ReservationActivity.class);
                break;
            case R.id.tvIsConcern:
                httpParams.clear();
                httpParams.put("id", id);
                mPresenter.setConcern(httpParams);
                break;
            default:
        }
    }

    public void setGoodDetail(GoodDetailBean goodDetailBean) {
        GlideApp.with(GoodsDetailActivity.this)
                .asBitmap()//强制Glide返回一个Bitmap对象
                .load(UrlConstant.IMAGE_BASE_URL + goodDetailBean.getHomeimage())
//                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int width = resource.getWidth();
                        int height = resource.getHeight();
//                            Log.e("width_height", "width " + width + "--------height " + height);
                        if (width >= height) {
                            ivBg.setImageResource(R.drawable.ic_pc);
                            ivFront.setVisibility(View.INVISIBLE);
                            ivFront2.setVisibility(View.VISIBLE);
                            ivFront2.setImageBitmap(resource);
                        } else {
                            ivBg.setImageResource(R.drawable.ic_mobile);
                            ivFront2.setVisibility(View.INVISIBLE);
                            ivFront.setVisibility(View.VISIBLE);
                            ivFront.setImageBitmap(resource);
                        }
                    }
                });
        String concern = goodDetailBean.getConcern();
        tvIsConcern.setChecked("1".equals(concern));
        tvProductName.setText(goodDetailBean.getName());
        tvProductDes.setText(goodDetailBean.getIntroduct());
        tvProductPrice.setText("￥" + goodDetailBean.getPrice());

        modao = goodDetailBean.getModao();

        LabelAdapter labelAdapter = new LabelAdapter(GoodsDetailActivity.this, goodDetailBean.getLabel());
        rvFeatures.setAdapter(labelAdapter);
        // 手机版界面图
        String appimages = goodDetailBean.getAppimages();
        if (UiTools.noEmpty(appimages)) {
            String[] split = appimages.split(",");
            pcInterfaceAdapter1.setImageList(Arrays.asList(split));
        }
        String pcimages = goodDetailBean.getPcimages();
        if (UiTools.noEmpty(pcimages)) {
            // pc 界面图
            String[] split = pcimages.split(",");
            pcInterfaceAdapter.setImageList(Arrays.asList(split));
        }

        ArrayList<SimiliarBean> similiar = goodDetailBean.getSimiliar();
        otherRecommendAdapter.setSimiliar(similiar);

        // 产品介绍
        tvProductFeatures.setText(Html.fromHtml(goodDetailBean.getFunctioncontent(),
                new HtmlImageGetter(GoodsDetailActivity.this, tvProductFeatures, true), null));
        // 特色
        ArrayList<FeatureDetailBean> featureDetail = goodDetailBean.getFeatureDetail();
        mainFeaturesAdapter.setFeatureDetail(featureDetail);
    }

    public void setBps(List<ManagerBean> managerBeanList) {
        // 推荐经理人
        recommendManagerAdapter.setManagerBeanList(managerBeanList);
        if (managerBeanList != null && managerBeanList.size() > 0) {
            ManagerBean managerBean = managerBeanList.get(0);
            GlideApp.with(GoodsDetailActivity.this)
                    .load(UrlConstant.IMAGE_BASE_URL + managerBean.getAvatar())
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(ivHead);
            tvManagerName.setText(managerBean.getNickname());
//            tvMsg.setHint();
//            tvPhoneContact.setHint(managerBean.getMobile());
        }
    }

    public void setConcernSuccess() {
        if (tvIsConcern.isChecked()) {
            tvIsConcern.setChecked(false);
        } else {
            tvIsConcern.setChecked(true);
        }
    }

    public void chatSeek(String data) {
        ChatListBean chatListBean = new ChatListBean();
        List<ManagerBean> managerBeanList = recommendManagerAdapter.getManagerBeanList();
        if (managerBeanList != null && managerBeanList.size() > 0) {
            ManagerBean managerBean = managerBeanList.get(0);
            chatListBean.setBpPhone(managerBean.getMobile());
            chatListBean.setBpName(managerBean.getNickname());
        }
        chatListBean.setChatId(data);
        bundle.putParcelable("chatUser", chatListBean);
        startActivity(GoodsDetailActivity.this, bundle, ChatDetailActivity.class);
    }
}
