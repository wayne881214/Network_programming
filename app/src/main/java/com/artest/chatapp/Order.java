package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Order extends AppCompatActivity {

    Button pizzaButton, burgerButton, colaButton, frenchfriesButton, friedchickenButton;
    String choices = "";
    Integer price = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        pizzaButton= (Button) findViewById(R.id.pizzaButton);
        burgerButton= (Button) findViewById(R.id.burgerButton);
        colaButton= (Button) findViewById(R.id.colaButton);
        frenchfriesButton= (Button) findViewById(R.id.frenchfriesButton);
        friedchickenButton= (Button) findViewById(R.id.friedchickenButton);
    }

    public void add_to_list(View v){
        if(v == findViewById(R.id.pizzaButton)){
            choices = choices + "披薩 (50元) X1" + "\n";
            price = price + 50;
        }
        else if(v == findViewById(R.id.burgerButton)){
            choices = choices + "漢堡 (45元) X1" + "\n";
            price = price + 45;
        }
        else if(v == findViewById(R.id.colaButton)){
            choices = choices + "可樂 (25元) X1" + "\n";
            price = price + 25;
        }
        else if(v == findViewById(R.id.frenchfriesButton)){
            choices = choices + "薯條 (30元) X1" + "\n";
            price = price + 30;
        }
        else if(v == findViewById(R.id.friedchickenButton)){
            choices = choices + "炸雞 (40元) X1" + "\n";
            price = price + 40;
        }
    }

    public void placeOrder(View v){
        Intent i = new Intent(Order.this,OrderDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("choices", choices);
        bundle.putInt("price", price);
        i.putExtras(bundle);
        startActivity(i);
    }
}