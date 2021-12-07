package dev.jocey.testwebview.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appsflyer.AppsFlyerLib;
import com.onesignal.OneSignal;

import dev.jocey.testwebview.R;
import presenter.Presenter;
import util.MyWebClient;

public class WebActivity extends AppCompatActivity implements ViewWV {
    private WebView webView;
    private Presenter presenter;
    private AppsFlyerLib appsFlyerLib;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
        presenter = new Presenter(getSharedPreferences("settings", MODE_PRIVATE));
        openWeb(presenter.getLink());

    }

    public void init() {
        appsFlyerLib = AppsFlyerLib.getInstance().init(String.valueOf(R.string.apps_flyer_dev_key), null, this);
        Log.d("myLog", "AppsFlyerLib: " + appsFlyerLib.getAppsFlyerUID(this));


//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);
        OneSignal.initWithContext(this);
        OneSignal.setAppId("fca6d40f-e09f-4b7b-b4b3-fc18ee26468d");

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
    }

    @Override
    protected void onStop() {
        Log.d("myLog", "save link: " + webView.getUrl());
        presenter.saveLink(webView.getUrl());
        super.onStop();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void openWeb(String link) {
        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebClient(this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
    }


    @Override
    public void showOffline() {
        startActivity(new Intent(this, OfflineActivity.class));
        finish();
    }

    @Override
    public void saveLink(String link) {
        Log.d("myLog", "save link: " + link);
        presenter.saveLink(link);
    }
}
