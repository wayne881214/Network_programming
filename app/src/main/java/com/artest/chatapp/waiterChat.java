package com.artest.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import pl.droidsonroids.gif.GifDrawable;

import org.json.JSONObject;
import pl.droidsonroids.gif.GifImageView;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;

import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static com.artest.chatapp.Login.yourDatabaseURL;


public class waiterChat extends AppCompatActivity {

    LinearLayout layout;
    ImageView sendButton;
    ImageView sendPicture;
    ImageView takePicture;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    FirebaseStorage storage;
    String imagePath = "";
    String cloudImageName = "";
    BitmapFactory.Options bfoOptions = new BitmapFactory.Options();
    private Thread thread;
    final Base64.Decoder decoder = Base64.getDecoder();
    final Base64.Encoder encoder = Base64.getEncoder();


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_chat);

        layout = (LinearLayout) findViewById(R.id.layout1);
        sendButton = (ImageView) findViewById(R.id.sendButton);
        sendPicture = (ImageView) findViewById(R.id.sendPicture);
        takePicture = (ImageView)findViewById(R.id.takePicture);
        messageArea = (EditText) findViewById(R.id.messageArea);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        //??????firebase storage
        storage = FirebaseStorage.getInstance();

        Firebase.setAndroidContext(this);
        reference1 = new Firebase(yourDatabaseURL+"messages/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase(yourDatabaseURL+"messages/" + UserDetails.chatWith + "_" + UserDetails.username);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                messageArea.setText("");
                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    try {
                        final byte[] textByte = messageText.getBytes("UTF-8");
                        final String encodedText = encoder.encodeToString(textByte);
                        map.put("message", encodedText);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
//                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                }
            }
        });
        takePicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openCamera();
            }
        });
        //????????????
        sendPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fill in code by yourself
                Intent intent = new Intent();
                intent.setType("image/*");  // ??????Pictures??????Type?????????image
                intent.setAction(Intent.ACTION_GET_CONTENT);  // ??????Intent.ACTION_GET_CONTENT
                startActivityForResult(intent,1);  // ????????????????????????
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if (userName.equals(UserDetails.username)) {
                    addMessageBox("You:\n", message, 1);
                } else {
                    addMessageBox(UserDetails.chatWith + ":\n", message, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, String original, int type) {
        try {
            message = message + new String(decoder.decode(original), "UTF-8");
            original = new String(decoder.decode(original), "UTF-8");
        }catch (Exception e){

        }
        if (original.indexOf("https:") > -1 && message.indexOf("firebase") > -1) {
            GifImageView imageView = new GifImageView(waiterChat.this);
            if(original.indexOf(".gif")>-1){
                Glide.with(this).load(original)
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(imageView);
            }else {
                Glide.with(this).load(original)
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).into(imageView);
            }
            if (type == 1) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 10, 20, 20);
                imageView.setLayoutParams(lp);
            } else {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 10, 20, 20);
                imageView.setLayoutParams(lp);
            }
            if (type == 1) {
                imageView.setBackgroundResource(R.drawable.rounded_corner1);
            } else {
                imageView.setBackgroundResource(R.drawable.rounded_corner2);
            }
            layout.addView(imageView);
            scrollView.fullScroll(View.FOCUS_DOWN);
        } else {
            TextView textView = new TextView(waiterChat.this);
            textView.setText(message);
            if (type == 1) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(750, 10, 20, 20);
                textView.setLayoutParams(lp);
            } else {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 10, 20, 20);
                textView.setLayoutParams(lp);
            }
            if (type == 1) {
                textView.setBackgroundResource(R.drawable.rounded_corner1);
            } else {
                textView.setBackgroundResource(R.drawable.rounded_corner2);
            }
            layout.addView(textView);
            scrollView.fullScroll(View.FOCUS_DOWN);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String imageType = ".jpg";
        Bitmap bitmap = null;
        if (resultCode == RESULT_OK ) {
            //????????????
            if(requestCode == 1 ){
                Uri uri = data.getData();
                if (DocumentsContract.isDocumentUri(this, uri)) {
                    String docId = DocumentsContract.getDocumentId(uri);
                    if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                        //Fill in code by yourself
                        String id = docId.split(":")[1];
                        String selection = MediaStore.Images.Media._ID + "=" + id;
                        imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                    } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                        //Fill in code by yourself
                        Uri contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"),
                                Long.valueOf(docId));
                        imagePath = getImagePath(contentUri, null);
                    }
                } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                    //Fill in code by yourself
                    imagePath = getImagePath(uri, null);
                }
                Log.e("url: ", imagePath);
                //??????????????????
                if (imagePath.indexOf(".jpg") > -1) {
                    imageType = ".jpg";
                } else if (imagePath.indexOf(".png") > -1) {
                    imageType = ".png";
                } else if (imagePath.indexOf(".gif") > -1) {
                    imageType = ".gif";
                }
                //????????????????????????bitmap
                bitmap = BitmapFactory.decodeFile(imagePath, bfoOptions);

            }
            else if(requestCode == 2){
                //Fill in code by yourself
                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
            }
            //????????????
            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            //?????????????????????
            String picname = UserDetails.username + "-" + ts + imageType;
            //????????????????????????
            StorageReference imageRef = storage.getReference().child(picname);
            byte[] data2 = null ;
            if(imagePath.indexOf(".gif") > -1){
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(imagePath);
                    data2 = new byte[fis.available()];
                    fis.read(data2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                //Fill in code by yourself
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                data2 = baos.toByteArray();
            }
            UploadTask uploadTask = imageRef.putBytes(data2);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    // Continue with the task to get the download URL
                    return imageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Map<String, String> map = new HashMap<String, String>();
                        try {
                            final byte[] textByte;
                            textByte = String.valueOf(downloadUri).getBytes("UTF-8");
                            final String encodedText = encoder.encodeToString(textByte);
                            map.put("message", String.valueOf(encodedText));

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        //map.put("message", String.valueOf(downloadUri));
                        map.put("user", UserDetails.username);
                        reference1.push().setValue(map);
                        reference2.push().setValue(map);

                    } else {

                    }
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    public  void openCamera(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, 2);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
}