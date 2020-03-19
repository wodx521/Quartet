package com.lr.baselibrary.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lr.baselibrary.R;
import com.lr.baselibrary.weight.SimpleMultiStateView;


public abstract class BaseFragment extends Fragment implements BaseView, View.OnClickListener {
   private SimpleMultiStateView mSimpleMultiStateView;

    public void startActivity(Fragment fragment, Bundle bundle, Class<?> cls) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        fragment.startActivity(intent);
    }

    public void startActivityForResult(Fragment fragment, Bundle bundle, int requestCode, Class<?> cls) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        if (getResId() > 0) {
            return inflater.inflate(getResId(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initStateView();
        initClickListener();
        initData();

    }

    protected abstract void initView(View view);

    private void initStateView() {
        if (mSimpleMultiStateView == null) return;
        mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                .setRetryResource(R.layout.view_retry)
                .setLoadingResource(R.layout.view_loading)
                .setNoNetResource(R.layout.view_nonet)
                .build()
                .setonReLoadlistener(this::onRetry);
    }

    protected void initClickListener() {

    }

    protected abstract void initData();

    public abstract void getNetStatus(boolean isConnect);

    /**
     * 初始化数据的空实现，fragment切换加载数据的时候重写
     */
    protected void initDataOnUserVisible() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getResId();

    protected void viewGone(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.GONE);
            }
        }
    }

    protected void viewVisible(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    protected void viewInvisible(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void showLoading() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showLoadingView();
        }
    }

    @Override
    public void showSuccess() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showContent();
        }
    }

    @Override
    public void showFaild() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showErrorView();
        }
    }

    @Override
    public void showNoNet() {
        if (mSimpleMultiStateView != null) {
            mSimpleMultiStateView.showNoNetView();
        }
    }

    @Override
    public void onRetry() {
        initDataOnUserVisible();
    }

    @Override
    public void onClick(View v) {

    }
}
