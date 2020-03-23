package com.lr.quartetplatform.moudle2.activity;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lr.baselibrary.base.BaseMvpActivity;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.quartetplatform.HtmlImageGetter;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.NewsDetailBean;
import com.lr.quartetplatform.moudle2.presenter.NewsDetailPresenter;

public class NewsDetailActivity extends BaseMvpActivity<NewsDetailPresenter> {
    private ImageView ivTop;
    private TextView tvTop, tvNewsContent, tvTitle;
    private ImageView ivBack;

    @Override
    protected NewsDetailPresenter getPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    protected void initData() {
        if (mBundle != null) {
            String id = mBundle.getString("id");
            mPresenter.getNewsDetail(id);
        }
    }

    @Override
    protected int getResId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initView() {
        ivTop = findViewById(R.id.ivTop);
        tvTop = findViewById(R.id.tvTop);
        tvTitle = findViewById(R.id.tvTitle);
        ivBack = findViewById(R.id.ivBack);
        tvNewsContent = findViewById(R.id.tvNewsContent);

        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivBack) {
            finish();
        }
    }

    public void setNewsDetail(NewsDetailBean newsDetailBean) {
        GlideApp.with(NewsDetailActivity.this)
                .load(newsDetailBean.getCover())
                .into(ivTop);
        tvTop.setText(newsDetailBean.getTitle());
        tvTitle.setText(newsDetailBean.getTitle());
        tvNewsContent.setText(Html.fromHtml(newsDetailBean.getContent(), new HtmlImageGetter(NewsDetailActivity.this, tvNewsContent, false), null));
    }
}
