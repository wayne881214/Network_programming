package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class OrderDetails extends AppCompatActivity {

    TextView listView, priceView;
    String list_choice;
    Integer price_bd, price_usd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        listView= (TextView) findViewById(R.id.listView);
        priceView= (TextView) findViewById(R.id.priceView);

        Bundle bundle = getIntent().getExtras();
        list_choice = bundle.getString("choices");
        price_bd = bundle.getInt("price");

        listView.setText(list_choice);
        priceView.setText(price_bd.toString()+" 元");
    }
}