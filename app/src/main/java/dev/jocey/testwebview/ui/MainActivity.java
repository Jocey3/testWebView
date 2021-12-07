package dev.jocey.testwebview.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import dev.jocey.testwebview.R;

public class MainActivity extends AppCompatActivity {
    private Button btnConfirm;
    private Button btnExit;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnConfirm.setOnClickListener(view -> {
            if (isConnected())
                startActivity(new Intent(this, WebActivity.class));
            else startActivity(new Intent(this, OfflineActivity.class));
        });
        btnExit.setOnClickListener(view -> finishAndRemoveTask());

    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null;

    }

    public void init() {
        btnConfirm = findViewById(R.id.btn_confirm);
        btnExit = findViewById(R.id.btn_exit);
    }
}
