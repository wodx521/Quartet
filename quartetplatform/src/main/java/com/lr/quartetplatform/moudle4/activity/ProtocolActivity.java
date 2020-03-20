package com.lr.quartetplatform.moudle4.activity;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseActivity;
import com.lr.quartetplatform.R;

public class ProtocolActivity extends BaseActivity {
    @Override
    protected void getNetStatus(boolean netStatus) {

    }

    @Override
    protected int getResId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initView() {
        TextView tvTitle = findViewById(R.id.tvTitle);
        ImageView ivBack = findViewById(R.id.ivBack);
        TextView tvProtocolContent = findViewById(R.id.tvProtocolContent);
        ivBack.setOnClickListener(this);
//        tvProtocolContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (mBundle != null) {
            String protocolContent = mBundle.getString("protocolContent");
            tvProtocolContent.setText(Html.fromHtml(protocolContent));
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack) {
            finish();
        }
    }
}
