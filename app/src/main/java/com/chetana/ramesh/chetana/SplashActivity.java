package com.chetana.ramesh.chetana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
     new java.util.Timer().schedule(new java.util.TimerTask(){
            public void run() {
                startActivity(new android.content.Intent(SplashActivity.this, MainActivity.class));
            }
        }, 2000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SplashActivity.this.finish();
    }
}
