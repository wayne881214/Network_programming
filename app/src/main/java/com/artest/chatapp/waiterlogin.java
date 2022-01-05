package com.artest.chatapp;

import static com.artest.chatapp.Register.SHA1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import static com.artest.chatapp.Login.yourDatabaseURL;

public class waiterlogin extends AppCompatActivity {
    EditText username, password;
    Button loginButton,testButton;
    String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiterlogin);

    request_permissions();

    username = (EditText)findViewById(R.id.username);
    password = (EditText)findViewById(R.id.password);
    loginButton = (Button)findViewById(R.id.loginButton);
    testButton=(Button)findViewById(R.id.testButton);

        testButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(waiterlogin.this, Login.class));
        }
    });
        loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            user = username.getText().toString();
            pass = password.getText().toString();
            try {
                pass = SHA1(pass);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(user.equals("")){
                username.setError("can't be blank");
            }
            else if(pass.equals("")){
                password.setError("can't be blank");
            }
            else{
                String url = yourDatabaseURL+"waiters.json";
                final ProgressDialog pd = new ProgressDialog(waiterlogin.this);
                pd.setMessage("Loading...");
                pd.show();

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("null")){
                            Toast.makeText(waiterlogin.this, "user not found", Toast.LENGTH_LONG).show();
                        }
                        else{
                            try {
                                JSONObject obj = new JSONObject(s);

                                if(!obj.has(user)){
                                    Toast.makeText(waiterlogin.this, "user not found", Toast.LENGTH_LONG).show();
                                }
                                else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                    UserDetails.username = user;
                                    UserDetails.password = pass;
                                    startActivity(new Intent(waiterlogin.this, waiterChoose.class));
                                }
                                else {
                                    Toast.makeText(waiterlogin.this, "incorrect password", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        pd.dismiss();
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("" + volleyError);
                        pd.dismiss();
                    }
                });

                RequestQueue rQueue = Volley.newRequestQueue(waiterlogin.this);
                rQueue.add(request);
            }

        }
    });
}
    private void request_permissions() {
        List<String> permissionList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]), 1002);
        }
    }
}