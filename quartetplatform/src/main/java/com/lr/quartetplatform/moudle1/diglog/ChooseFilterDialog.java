package com.lr.quartetplatform.moudle1.diglog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.CycleTypeBean;
import com.lr.quartetplatform.bean.FilterTypeBean;
import com.lr.quartetplatform.moudle1.adapter.ChooseTypeAdapter;
import com.lr.quartetplatform.moudle1.adapter.CycleTypeAdapter;
import com.lr.quartetplatform.moudle1.adapter.LanguageTypeAdapter;
import com.lr.quartetplatform.moudle1.adapter.ProductStyleAdapter;

import java.util.List;
import java.util.Objects;

public class ChooseFilterDialog implements View.OnClickListener {
    private OrderClickListener orderClickListener;
    private Activity activity;
    private CheckedTextView tvProductStyle, tvTechnology, tvCycle;
    private RecyclerView rvProductStyle, rvTechnologyType, rvDevelopCycle;
    private TextView tvReset, tvConfirm, tvCancel;
    private AlertDialog dialog;
    private View view;
    private FilterTypeBean mFilterTypeBean;
    private ChooseTypeAdapter chooseTypeAdapter;
    private ProductStyleAdapter productStyleAdapter;
    private LanguageTypeAdapter languageTypeAdapter;
    private CycleTypeAdapter cycleTypeAdapter;

    public ChooseFilterDialog(Activity activity) {
        this.activity = activity;
    }

    public void getDialog(FilterTypeBean filterTypeBean) {
        this.mFilterTypeBean = filterTypeBean;
        if (dialog == null) {
            dialog = new AlertDialog.Builder(activity).create();
        }
        view = UiTools.parseLayout(R.layout.choose_filter);

        tvCancel = view.findViewById(R.id.tvCancel);
        tvProductStyle = view.findViewById(R.id.tvProductStyle);
        rvProductStyle = view.findViewById(R.id.rvProductStyle);
        tvTechnology = view.findViewById(R.id.tvTechnology);
        rvTechnologyType = view.findViewById(R.id.rvTechnologyType);
        tvCycle = view.findViewById(R.id.tvCycle);
        rvDevelopCycle = view.findViewById(R.id.rvDevelopCycle);
        tvReset = view.findViewById(R.id.tvReset);
        tvConfirm = view.findViewById(R.id.tvConfirm);
        List<CycleTypeBean> goodsDays = filterTypeBean.getGoodsDays();
        List<String> homeType = filterTypeBean.getHomeType();
        List<String> language = filterTypeBean.getLanguage();

        productStyleAdapter = new ProductStyleAdapter(activity);
        rvProductStyle.setAdapter(productStyleAdapter);
        productStyleAdapter.setHomeType(homeType);
        productStyleAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                productStyleAdapter.setSelect(position);
            }
        });

        languageTypeAdapter = new LanguageTypeAdapter(activity);
        rvTechnologyType.setAdapter(languageTypeAdapter);
        languageTypeAdapter.setHomeType(language);
        languageTypeAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                languageTypeAdapter.setSelect(position);
            }
        });

        cycleTypeAdapter = new CycleTypeAdapter(activity);
        rvDevelopCycle.setAdapter(cycleTypeAdapter);
        cycleTypeAdapter.setHomeType(goodsDays);
        cycleTypeAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                cycleTypeAdapter.setSelect(position);
            }
        });

        tvProductStyle.setOnClickListener(this);
        tvTechnology.setOnClickListener(this);
        tvCycle.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        tvReset.setOnClickListener(this);

        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.drawable.shape_white_bg);

        dialog.show();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.getWindow().setWindowAnimations(R.style.anim_up_down);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        // dialog显示时背景的昏暗程度
        attributes.dimAmount = 0.5f;
        attributes.width = UiTools.getDeviceWidth(activity);
        dialog.getWindow().setAttributes(attributes);
    }

    public void setOrderClickListener(OrderClickListener orderClickListener) {
        this.orderClickListener = orderClickListener;
    }

    @Override
    public void onClick(View v) {
        String chooseContent;
        switch (v.getId()) {
            case R.id.tvReset:
                if (orderClickListener != null) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    orderClickListener.onResetListener();
                }
                break;
            case R.id.tvCancel:
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
            case R.id.tvConfirm:
                if (orderClickListener != null) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    if (tvCycle.isChecked()) {
                        int select = cycleTypeAdapter.getSelect();
                        List<CycleTypeBean> homeType = cycleTypeAdapter.getHomeType();
                        CycleTypeBean cycleTypeBean = homeType.get(select);
                        chooseContent = cycleTypeBean.getValue();
                        orderClickListener.onConfirmListener(3, chooseContent);
                        return;
                    }
                    if (tvTechnology.isChecked()) {
                        int select = languageTypeAdapter.getSelect();
                        List<String> homeType = languageTypeAdapter.getHomeType();
                        chooseContent = homeType.get(select);
                        orderClickListener.onConfirmListener(2, chooseContent);
                        return;
                    }
                    if (tvProductStyle.isChecked()) {
                        int select = productStyleAdapter.getSelect();
                        List<String> homeType = productStyleAdapter.getHomeType();
                        chooseContent = homeType.get(select);
                        orderClickListener.onConfirmListener(1, chooseContent);
                        return;
                    }
                }
                break;
            case R.id.tvProductStyle:
                tvCycle.setChecked(false);
                tvTechnology.setChecked(false);
                tvProductStyle.setChecked(rvProductStyle.getVisibility() == View.GONE);

                rvDevelopCycle.setVisibility(View.GONE);
                rvTechnologyType.setVisibility(View.GONE);
                if (rvProductStyle.getVisibility() == View.VISIBLE) {
                    rvProductStyle.setVisibility(View.GONE);
                } else {
                    rvProductStyle.setVisibility(View.VISIBLE);
                }

                productStyleAdapter.setSelect(-1);
                languageTypeAdapter.setSelect(-1);
                cycleTypeAdapter.setSelect(-1);
                break;
            case R.id.tvTechnology:
                tvCycle.setChecked(false);
                tvProductStyle.setChecked(false);
                tvTechnology.setChecked(rvTechnologyType.getVisibility() == View.GONE);

                rvProductStyle.setVisibility(View.GONE);
                rvDevelopCycle.setVisibility(View.GONE);
                if (rvTechnologyType.getVisibility() == View.VISIBLE) {
                    rvTechnologyType.setVisibility(View.GONE);
                } else {
                    rvTechnologyType.setVisibility(View.VISIBLE);
                }

                productStyleAdapter.setSelect(-1);
                languageTypeAdapter.setSelect(-1);
                cycleTypeAdapter.setSelect(-1);
                break;
            case R.id.tvCycle:
                tvProductStyle.setChecked(false);
                tvTechnology.setChecked(false);
                tvCycle.setChecked(rvDevelopCycle.getVisibility() == View.GONE);

                rvProductStyle.setVisibility(View.GONE);
                rvTechnologyType.setVisibility(View.GONE);
                if (rvDevelopCycle.getVisibility() == View.VISIBLE) {
                    rvDevelopCycle.setVisibility(View.GONE);
                } else {
                    rvDevelopCycle.setVisibility(View.VISIBLE);
                }

                productStyleAdapter.setSelect(-1);
                languageTypeAdapter.setSelect(-1);
                cycleTypeAdapter.setSelect(-1);
                break;
            default:
        }
    }

    public interface OrderClickListener {
        void onConfirmListener(int type, String position);

        void onResetListener();
    }
}
