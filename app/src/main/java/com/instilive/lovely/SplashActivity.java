package com.instilive.lovely;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2500;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        sharedPrefManager=new SharedPrefManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPrefManager.isLoggedIn())
                {
                    moveToHomePageActivity();
                }

                else
                {
                    moveToMainActivity();
                }
            }
        },SPLASH_DISPLAY_LENGTH);
    }

    private void moveToHomePageActivity() {
        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }
    private void moveToMainActivity()
    {
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
