package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Order extends AppCompatActivity {
    Button pizzaButton, burgerButton, colaButton, frenchfriesButton, friedchickenButton;
    String choices = "";
    String pizza="",burger="",cola="",frenchfries="",friedchicken="";
    Integer a=0,b=0,c=0,d=0,e=0;
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
            a++;
            pizza = "披薩 (50元) X" + a+ "\n";
            price = price + 50;
        }
        else if(v == findViewById(R.id.burgerButton)){
            b++;
            burger=  "漢堡 (45元) X" +b+ "\n";
            price = price + 45;
        }
        else if(v == findViewById(R.id.colaButton)){
            c++;
            cola= "可樂 (25元) X" +c+ "\n";
            price = price + 25;
        }
        else if(v == findViewById(R.id.frenchfriesButton)){
            d++;
            frenchfries="薯條 (30元) X" +d+ "\n";
            price = price + 30;
        }
        else if(v == findViewById(R.id.friedchickenButton)){
            e++;
            friedchicken= "炸雞 (40元) X" + e+"\n";
            price = price + 40;
        }
    }

    public void placeOrder(View v){
        choices=pizza+burger+cola+friedchicken;
        a=0;
        b=0;
        c=0;
        d=0;
        e=0;
        pizza="";
        burger="";
        cola="";
        frenchfries="";
        friedchicken="";
        Intent i = new Intent(Order.this,OrderDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("choices", choices);
        bundle.putInt("price", price);
        i.putExtras(bundle);
        startActivity(i);
    }
}