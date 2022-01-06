package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class Into extends AppCompatActivity {
    ImageView name, bg;
    LottieAnimationView lottie, logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into);
        logo = findViewById(R.id.logo);
        name = findViewById(R.id.name);
        bg = findViewById(R.id.bg);
        lottie = findViewById(R.id.lottie);

        bg.animate().translationY(-2500).setDuration(1000).setStartDelay(5000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(5000);
        name.animate().translationY(1400).setDuration(1000).setStartDelay(5000);
        lottie.animate().translationY(1400).setDuration(1000).setStartDelay(5000);

        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    Thread.sleep(6000);
                    Intent intent = new Intent(Into.this, Welcome.class);
                    startActivity(intent);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();

    }


}