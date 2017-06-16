package com.aai.inventorymanagement.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aai.inventorymanagement.R;

public class SplashActivity extends AppCompatActivity implements Runnable {

    int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(this,DELAY);
    }

    @Override
    public void run() {

        startActivity( new Intent(SplashActivity.this , HomeActivity.class));
    }
}
