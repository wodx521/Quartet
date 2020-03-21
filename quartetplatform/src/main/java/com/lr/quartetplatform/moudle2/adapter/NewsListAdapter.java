package com.lr.quartetplatform.moudle2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lr.baselibrary.base.BaseRecycleViewAdapter;
import com.lr.baselibrary.glidetools.GlideApp;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.bean.NewsBannerBean;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends BaseRecycleViewAdapter {
    private List<NewsBannerBean> newsList;

    public NewsListAdapter(Context context) {
        super(context);
    }

    public List<NewsBannerBean> getNewsList() {
        if (newsList == null) {
            newsList = new ArrayList<>();
        }
        return newsList;
    }

    public void setNewsList(List<NewsBannerBean> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @Override
    protected int getItemRes() {
        return R.layout.item_news;
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new NewsListViewHolder(view);
    }

    @Override
    protected void bindClickListener(RecyclerView.ViewHolder viewHolder, int position) {
        NewsListViewHolder newsListViewHolder = (NewsListViewHolder) viewHolder;
        NewsBannerBean newsBannerBean = newsList.get(position);
        newsListViewHolder.tvNewsTitle.setText(newsBannerBean.getTitle());
        newsListViewHolder.tvNewsAuthor.setText(newsBannerBean.getAuthorName());
        newsListViewHolder.tvNewsTime.setText(newsBannerBean.getPublishedAt());
        GlideApp.with(mContext)
                .load(newsBannerBean.getCover())
                .into(newsListViewHolder.ivNews);
    }

    @Override
    public int getItemCount() {
        if (newsList != null && newsList.size() > 0) {
            return newsList.size();
        }
        return 0;
    }

    static class NewsListViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivNews;
        private TextView tvNewsTitle, tvNewsAuthor, tvNewsTime;

        public NewsListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNews = itemView.findViewById(R.id.ivNews);
            tvNewsTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvNewsAuthor = itemView.findViewById(R.id.tvNewsAuthor);
            tvNewsTime = itemView.findViewById(R.id.tvNewsTime);
        }
    }
}
