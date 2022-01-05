package com.artest.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.artest.chatapp.MusicDetails;
import com.artest.chatapp.R;
import com.artest.chatapp.Room;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


import static com.artest.chatapp.Login.yourDatabaseURL;


public class waiterorder extends AppCompatActivity {

    ListView usersList,singersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    ArrayList<String> bl = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;
    Firebase reference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiterorder);
        singersList= (ListView)findViewById(R.id.singersList);
        usersList = (ListView)findViewById(R.id.usersList);
        noUsersText = (TextView)findViewById(R.id.noUsersText);

        pd = new ProgressDialog(com.artest.chatapp.waiterorder.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = yourDatabaseURL+"order.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(com.artest.chatapp.waiterorder.this);
        rQueue.add(request);
        Firebase.setAndroidContext(this);
        singersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.Room=al.get(position);
                reference1 = new Firebase(yourDatabaseURL+"order/"+UserDetails.Room);
                reference1.removeValue();
                startActivity(new Intent(com.artest.chatapp.waiterorder.this, waiterorder.class));
            }
        });
    }

    public void doOnSuccess(String s){

        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";
            while(i.hasNext()){
                key = i.next().toString();
                String singer=obj.getJSONObject(key).getString("total");
                al.add(key);
                bl.add(singer);
                totalUsers++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <1){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bl));
            singersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
        }
        pd.dismiss();
    }
}