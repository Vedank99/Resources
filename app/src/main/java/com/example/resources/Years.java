package com.example.resources;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Years extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Intent intent = getIntent();
        final String path = intent.getStringExtra("Parent");

        TextView firstYear = findViewById(R.id.firstYear);
        TextView secondYear = findViewById(R.id.secondYear);
        TextView thirdYear = findViewById(R.id.thirdYear);
        TextView fourthYear = findViewById(R.id.fourthYear);


        firstYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Subjects.class);
                intent.putExtra("Parent",path+"/First Year");
                startActivity(intent);
            }
        });

        secondYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Branches.class);
                intent.putExtra("Parent",path+"/Second Year");
                startActivity(intent);
            }
        });

        thirdYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Branches.class);
                intent.putExtra("Parent",path+"/Third Year");
                startActivity(intent);
            }
        });

        fourthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Branches.class);
                intent.putExtra("Parent",path+"/Fourth Year");
                startActivity(intent);
            }
        });

    }
}
