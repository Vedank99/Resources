package com.example.resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Branches extends AppCompatActivity {
    DatabaseReference databaseReference;

    ListView listView;
    List<String> branchLists;
    BranchlistAdapter adapter;
    Context context;
    public String title;
    private AdView mAdView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);

        final Intent intent = getIntent();
        title = intent.getStringExtra("Parent");
        toolbar = findViewById(R.id.branchToolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        branchLists = new ArrayList<>();
        listView = findViewById(R.id.branchList);


        context = MainActivity.mContext;


        adapter = new BranchlistAdapter(Branches.this,R.layout.generic_list,branchLists,title,"Branch");
        listView.setAdapter(adapter);

        viewAllFiles(title);

    }

    private void viewAllFiles(String title) {
        databaseReference = FirebaseDatabase.getInstance().getReference(title);
        Log.d("Firebase Path :", "Path " + title);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String curBranch = snapshot.getKey();
                branchLists.add(curBranch);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        Log.d("ViewALlFiles", "Being ended");

    }

}
