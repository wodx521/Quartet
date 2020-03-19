package com.lr.baselibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        View.OnClickListener, View.OnLongClickListener,View.OnTouchListener {

    protected Context mContext;
    protected LayoutInflater inflater;
    protected int defItem = -1;
    private View view;
    protected boolean isScrolling = false;
    public BaseRecycleViewAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = inflater.inflate(getItemRes(), viewGroup, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if (defItem != -1) {
            if (defItem == position) {
                holder.itemView.setSelected(true);
            } else {
                holder.itemView.setSelected(false);
            }
        }
        bindClickListener(holder, position);
    }

//    public View getItemView(int position){
//
//        return ;
//    }
    //设置布局文件
    protected abstract int getItemRes();

    //获取viewholder
    protected abstract RecyclerView.ViewHolder getViewHolder(View view);

    //绑定点击监听事件
    protected abstract void bindClickListener(RecyclerView.ViewHolder viewHolder, int position);

    public void setSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }

    public int getSelect() {
        return defItem;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            onItemClickListener.onItemClickListener(v, (Integer) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return onItemLongClickListener != null && onItemLongClickListener.onItemLongClickListener(v, (Integer) v.getTag());
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    /*设置点击事件*/
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /*设置长按事件*/
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClickListener(View view, int position);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
