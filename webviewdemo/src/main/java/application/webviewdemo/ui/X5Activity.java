package application.webviewdemo.ui;

import android.view.ViewGroup;
import android.view.ViewParent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.myjoke.baselibray.base.BaseActivity;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import application.webviewdemo.R;
import application.webviewdemo.R2;
import application.webviewdemo.util.WebViewConstant;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = WebViewConstant.X5Activity)
public class X5Activity extends BaseActivity {

    @BindView(R2.id.webView)
    WebView webView;


    Unbinder unbinder = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_x5;
    }

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        initWebViewSettings();

        webView.loadUrl("https://www.baidu.com");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            ViewParent viewParent = webView.getParent();
            if (viewParent != null) {
                ((ViewGroup) viewParent).removeView(webView);
            }
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }


    private void initWebViewSettings() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }
}
