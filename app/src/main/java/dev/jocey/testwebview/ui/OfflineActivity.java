package dev.jocey.testwebview.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.jocey.testwebview.R;

public class OfflineActivity extends AppCompatActivity {
    Button btnRepeat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        btnRepeat = findViewById(R.id.btn_repeat);
        btnRepeat.setOnClickListener(view -> {
            if (isConnected()) startActivity(new Intent(this, WebActivity.class));
        });
    }

    @Override
    public void onBackPressed() {

    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null;

    }
}
