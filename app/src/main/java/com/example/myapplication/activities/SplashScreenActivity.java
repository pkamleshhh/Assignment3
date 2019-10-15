package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.myapplication.R;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView tvSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSplash=findViewById(R.id.tv_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,HomeActivity.class));
                finish();
            }
        },3000);

    }
}
