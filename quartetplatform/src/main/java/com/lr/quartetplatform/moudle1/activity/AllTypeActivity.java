package com.lr.quartetplatform.moudle1.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseActivity;
import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.HomeTypeBean;
import com.lr.quartetplatform.moudle1.adapter.ClassificationAdapter;

import java.util.ArrayList;

public class AllTypeActivity extends BaseActivity {
    private TextView tvTitle;
    private ImageView ivBack;
    private ClassificationAdapter classificationAdapter;

    @Override
    protected void getNetStatus(boolean netStatus) {

    }

    @Override
    protected int getResId() {
        return R.layout.activity_all_type;
    }

    @Override
    protected void initView() {
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        RecyclerView rvAllType = findViewById(R.id.rvAllType);
        ivBack.setOnClickListener(this);
        classificationAdapter = new ClassificationAdapter(AllTypeActivity.this);
        rvAllType.setAdapter(classificationAdapter);
        classificationAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

            }
        });
        tvTitle.setText(R.string.classification);
        if (mBundle != null) {
            ArrayList<HomeTypeBean> allTypeList = mBundle.getParcelableArrayList("allType");
            classificationAdapter.setHomeType(allTypeList);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack) {
            finish();
        }
    }
}
