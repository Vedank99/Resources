package com.example.resources;


import androidx.appcompat.app.AppCompatActivity;;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    public static Context mContext;
    private final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Years.class);
                intent.putExtra("Parent","Resources");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        },2000);

    }

}