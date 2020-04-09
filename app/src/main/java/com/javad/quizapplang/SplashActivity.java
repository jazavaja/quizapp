package com.javad.quizapplang;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.javad.quizapplang.utils.PrefManager;

public class SplashActivity extends AppCompatActivity {

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefManager= new PrefManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (prefManager.getState().equals("1")){

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                }else {

                    startActivity(new Intent(SplashActivity.this, SignUp.class));

                }

                finish();

            }
        },500);


    }
}
