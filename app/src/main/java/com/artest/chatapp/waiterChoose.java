package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class waiterChoose extends AppCompatActivity {
    ImageButton a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_choose);
        a= (ImageButton) findViewById(R.id.aButton);
        b= (ImageButton) findViewById(R.id.bButton);
        c= (ImageButton) findViewById(R.id.cButton);
        d= (ImageButton) findViewById(R.id.dButton);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                startActivity(new Intent(waiterChoose.this, Customer.class));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(waiterChoose.this, addmusic.class));
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(waiterChoose.this, waiterRoomnumber.class));
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(waiterChoose.this, waiterorder.class));
            }
        });
    }
}