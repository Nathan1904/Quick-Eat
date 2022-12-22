package com.android.eatproject;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final int SPLASH_SCREEN_TIMEOUT= 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.sanji);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },  SPLASH_SCREEN_TIMEOUT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();

    }
}