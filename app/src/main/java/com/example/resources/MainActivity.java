package com.example.resources;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    public static Context mContext;
    private final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getPermissions();
        mContext = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Years.class);
                intent.putExtra("Parent","Resources");

                MainActivity.this.startActivity(intent);

            }
        },3500);



    }
    private void getPermissions(){

        String externalReadPermission = Manifest.permission.READ_EXTERNAL_STORAGE.toString();
        String externalWritePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE.toString();

        if(ContextCompat.checkSelfPermission(this,externalWritePermission) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,externalReadPermission) != PackageManager.PERMISSION_GRANTED ){
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{externalReadPermission,externalWritePermission},100);
            }
        }

    }

}