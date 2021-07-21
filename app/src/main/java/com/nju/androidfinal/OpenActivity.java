package com.nju.androidfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


public class OpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            //跳转到首界面
            Intent intent = new Intent(OpenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1000);

    }
}
