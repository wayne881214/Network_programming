package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    Thread.sleep(2000);
                    Intent intent = new Intent(Welcome.this, Login.class);
                    startActivity(intent);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}