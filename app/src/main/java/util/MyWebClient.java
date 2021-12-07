package util;

import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import dev.jocey.testwebview.ui.ViewWV;

public class MyWebClient extends WebViewClient {
    private ViewWV viewWV;
    private String link = "https://support.appsflyer.com/hc/ru";

    public MyWebClient(ViewWV view) {
        viewWV = view;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        link = url;
        super.onPageFinished(view, url);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        viewWV.saveLink(link);
        Log.d("myLog", "Error " + error.getDescription() + " " + error.getErrorCode());
        viewWV.showOffline();
    }
}
