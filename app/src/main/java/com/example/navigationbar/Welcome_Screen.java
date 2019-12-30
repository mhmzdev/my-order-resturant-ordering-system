package com.example.navigationbar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome_Screen extends AppCompatActivity {
    private static int splashTimeOut = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome_Screen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splashTimeOut);
    }
}
