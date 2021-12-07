package dev.jocey.testwebview.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.jocey.testwebview.R;
import presenter.Presenter;
import util.MyWebClient;

public class WebActivity extends AppCompatActivity implements ViewWV {
    private WebView webView;
    private Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        presenter = new Presenter(getSharedPreferences("settings", MODE_PRIVATE));
        openWeb(presenter.getLink());
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
