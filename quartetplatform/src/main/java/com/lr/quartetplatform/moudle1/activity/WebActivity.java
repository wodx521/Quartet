package com.lr.quartetplatform.moudle1.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.just.agentweb.AgentWeb;
import com.lr.quartetplatform.R;
import com.lr.quartetplatform.moudle1.AndroidInterfaceWeb;


@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends AppCompatActivity {
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle mBundle = getIntent().getBundleExtra("bundle");
        if (mBundle == null) {
            return;
        }
        String url = mBundle.getString("url");
        FrameLayout flWebContainer = findViewById(R.id.fl_web_container);
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
                .go(url);
        mAgentWeb.getJsAccessEntrace().quickCallJs("go");
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
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.getAllowFileAccess();
        settings.setAllowFileAccess(true);
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
}
