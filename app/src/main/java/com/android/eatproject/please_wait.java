package com.android.eatproject;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class please_wait extends AppCompatActivity {
    private final int SPLASH_SCREEN_TIMEOUT= 5000;
    ViewFlipper v_flipper;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_please_wait);
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.sanjitheme);
        ActionBar actionBar = getSupportActionBar();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },  SPLASH_SCREEN_TIMEOUT);


        int[] images = {R.drawable.pizzas, R.drawable.splash,R.drawable.splash1, R.drawable.splash2, R.drawable.splash3};
        v_flipper= findViewById(R.id.v_flipper);

        //For loop
        /*for (int i= 0; i<images.length; i++){
            flipperImages(images{i});
        }*/

        for (int image:images){
            flipperImages(image);
        }
    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }
    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();

    }
}
