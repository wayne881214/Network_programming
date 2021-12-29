package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.os.Bundle;

public class instrument extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument);
    }
=======
import android.content.Intent;


import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class instrument extends AppCompatActivity {
    ImageButton a,b,c,d;
    private SoundPool mSoundPool = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSoundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 0);
        int A = mSoundPool.load(instrument.this,R.raw.musica, 1);
        int B = mSoundPool.load(instrument.this,R.raw.musicb, 2);
        int C = mSoundPool.load(instrument.this,R.raw.musicc, 3);
        int D = mSoundPool.load(instrument.this,R.raw.musicc, 4);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument);
        a= (ImageButton) findViewById(R.id.aButton);
        b= (ImageButton) findViewById(R.id.bButton);
        c= (ImageButton) findViewById(R.id.cButton);
        d= (ImageButton) findViewById(R.id.dButton);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(A, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(B, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(C, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(D, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
    }

>>>>>>> eebc1a3bd3b5ca238e026e24178767bdc409aefd
}