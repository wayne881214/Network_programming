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
            choices = choices + "披薩 (1元) X1" + "\n";
            price = price + 1;
        }
        else if(v == findViewById(R.id.burgerButton)){
            choices = choices + "漢堡 (2元) X1" + "\n";
            price = price + 2;
        }
        else if(v == findViewById(R.id.colaButton)){
            choices = choices + "可樂 (3元) X1" + "\n";
            price = price + 3;
        }
        else if(v == findViewById(R.id.frenchfriesButton)){
            choices = choices + "薯條 (4元) X1" + "\n";
            price = price + 4;
        }
        else if(v == findViewById(R.id.friedchickenButton)){
            choices = choices + "炸雞 (5元) X1" + "\n";
            price = price + 5;
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