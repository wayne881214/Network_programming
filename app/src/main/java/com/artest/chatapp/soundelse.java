package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class soundelse extends AppCompatActivity {

    ImageButton a, b, c, d, e, f, g, h, i;
    private SoundPool mSoundPool = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSoundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 0);
        int A = mSoundPool.load(soundelse.this, R.raw.aa, 1);
        int B = mSoundPool.load(soundelse.this, R.raw.bb, 2);
        int C = mSoundPool.load(soundelse.this, R.raw.cc, 3);
        int D = mSoundPool.load(soundelse.this, R.raw.dd, 4);
        int E = mSoundPool.load(soundelse.this, R.raw.ee, 5);
        int F = mSoundPool.load(soundelse.this, R.raw.ff, 6);
        int G = mSoundPool.load(soundelse.this, R.raw.gg, 7);
        int H = mSoundPool.load(soundelse.this, R.raw.hh, 8);
        int I = mSoundPool.load(soundelse.this, R.raw.ii, 9);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundelse);
        a = (ImageButton) findViewById(R.id.a);
        b = (ImageButton) findViewById(R.id.b);
        c = (ImageButton) findViewById(R.id.c);
        d = (ImageButton) findViewById(R.id.d);
        e = (ImageButton) findViewById(R.id.e);
        f = (ImageButton) findViewById(R.id.f);
        g = (ImageButton) findViewById(R.id.g);
        h = (ImageButton) findViewById(R.id.h);
        i = (ImageButton) findViewById(R.id.i);
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
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(D, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(E, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(F, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(G, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(H, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSoundPool.play(I, 5.0f, 5.0f, 0, 0, 1.0f);
            }
        });

    }
}
