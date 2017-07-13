package com.aai.inventorymanagement.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aai.inventorymanagement.Others.Constants;
import com.aai.inventorymanagement.R;
import com.aai.inventorymanagement.Utilities.SharedPreferenceManager;

public class SplashActivity extends AppCompatActivity implements Runnable {

    int DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(this,DELAY);
    }

    @Override
    public void run() {

        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(SplashActivity.this);
        boolean loginStatus = sharedPreferenceManager.getBoolean(Constants.LOGINTOKEN_KEY, Constants.USER_NOTLOGINED);

        if (loginStatus == Constants.USER_NOTLOGINED)
            startActivity( new Intent(SplashActivity.this , LoginActivity.class));
        else
            startActivity( new Intent(SplashActivity.this , HomeActivity.class));

        finish();
    }
}
