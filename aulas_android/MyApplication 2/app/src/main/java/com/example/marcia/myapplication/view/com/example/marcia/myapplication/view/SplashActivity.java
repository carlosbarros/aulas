package com.example.marcia.myapplication.view.com.example.marcia.myapplication.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.marcia.myapplication.R;

public class SplashActivity extends Activity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler h = new Handler();
        h.postDelayed(this,1500);
    }

    @Override
    public void run() {
        startLogin();
        finish();
    }

    public void startLogin()
    {
        Intent telaLogin = new Intent(getApplicationContext(),LoginActivity.class);
        telaLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        telaLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(telaLogin);
    }
}
