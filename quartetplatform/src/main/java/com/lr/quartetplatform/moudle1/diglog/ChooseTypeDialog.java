package com.lr.quartetplatform.moudle1.diglog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.utils.UiTools;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle1.adapter.ChooseTypeAdapter;

import java.util.List;
import java.util.Objects;

public class ChooseTypeDialog implements View.OnClickListener {
    private OrderClickListener orderClickListener;
    private Activity activity;
    private TextView tvCancel, tvConfirm;
    private RecyclerView rvChoose;
    private AlertDialog dialog;
    private View view;
    private List<String> content;
    private String type;
    private ChooseTypeAdapter chooseTypeAdapter;

    public ChooseTypeDialog(Activity activity) {
        this.activity = activity;
    }

    public void getDialog(List<String> content, String type) {
        this.content = content;
        this.type = type;
        if (dialog == null) {
            dialog = new AlertDialog.Builder(activity).create();
        }
        view = UiTools.parseLayout(R.layout.choose_style);

        tvCancel = view.findViewById(R.id.tvCancel);
        tvConfirm = view.findViewById(R.id.tvConfirm);
        rvChoose = view.findViewById(R.id.rvChoose);
        chooseTypeAdapter = new ChooseTypeAdapter(activity);
        rvChoose.setAdapter(chooseTypeAdapter);
        chooseTypeAdapter.setContent(content);
        chooseTypeAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                chooseTypeAdapter.setSelect(position);
            }
        });
        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.drawable.shape_white_bg);

        dialog.show();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, UiTools.getDeviceHeight(activity) / 3);
        dialog.setContentView(view, params);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.anim_menu_bottombar);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        // dialog显示时背景的昏暗程度
        attributes.dimAmount = 0.5f;
        attributes.width = UiTools.getDeviceWidth(activity);
//        attributes.height = UiTools.getDeviceHeight(activity) / 3;
        dialog.getWindow().setAttributes(attributes);
    }

    public void setOrderClickListener(OrderClickListener orderClickListener) {
        this.orderClickListener = orderClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCancel:
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
            case R.id.tvConfirm:
                if (content != null && content.size() > 0) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    int currentItem = chooseTypeAdapter.getSelect();
                    if (orderClickListener != null) {
                        orderClickListener.onSelectorContent(currentItem, type);
                    }
                }
                break;
            default:
        }
    }

    public interface OrderClickListener {
        void onSelectorContent(int position, String type);
    }
}
