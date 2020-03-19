package com.lr.quartet.moudle1;

import android.view.View;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.lr.baselibrary.base.BaseMvpFragment;
import com.lr.quartet.R;

public class FirstFragment extends BaseMvpFragment<FirstPresenter> {
    private FrameLayout flWebContainer;
    private AgentWeb mAgentWeb;

    @Override
    protected FirstPresenter getPresenter() {
        return new FirstPresenter();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected void initView(View view) {
        flWebContainer = view.findViewById(R.id.fl_web_container);
    }

    @Override
    protected void initData() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(flWebContainer, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
//                .setWebChromeClient(mWebChromeClient)
//                .setAgentWebUIController(new UIController(MainActivity.this))
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .interceptUnkownUrl() //拦截找不到相关页面
                .createAgentWeb()
                .ready()
                .go("http://nztadmin.jinjifuweng.com/#/");

        WebSettings settings = mAgentWeb.getWebCreator().getWebView().getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        settings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.getAllowFileAccess();
        settings.setAllowFileAccess(true);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_first;
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

}
