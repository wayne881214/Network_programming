package com.artest.chatapp;

import static com.artest.chatapp.Login.yourDatabaseURL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class OrderDetails extends AppCompatActivity {

    TextView listView, priceView;
    String list_choice;
    Integer price_bd, price_usd;
    Firebase reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        listView= (TextView) findViewById(R.id.listView);
        priceView= (TextView) findViewById(R.id.priceView);

        Bundle bundle = getIntent().getExtras();
        list_choice = bundle.getString("choices");
        price_bd = bundle.getInt("price");
        Firebase.setAndroidContext(this);
        reference = new Firebase(yourDatabaseURL+"order/" + UserDetails.Room);
        Map<String, String> map = new HashMap<String, String>();
        map.put("money", price_bd.toString());
        map.put("total", list_choice);
        reference.push().setValue(map);

        listView.setText(list_choice);
        priceView.setText("總計: "+price_bd.toString()+" 元");

    }
}