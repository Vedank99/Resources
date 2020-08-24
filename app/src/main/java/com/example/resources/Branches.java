package com.example.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Branches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);

        final Intent intent = getIntent();
        final String title = intent.getStringExtra("Year");

        setTitle(title);

        TextView cse = findViewById(R.id.CSE);
        TextView ece = findViewById(R.id.ECE);

        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Branches.this,Subjects.class);
                intent1.putExtra("Year",title);
                intent1.putExtra("Branch","CSE");
                startActivity(intent1);
            }
        });

        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Branches.this,Subjects.class);
                intent1.putExtra("Year",title);
                intent1.putExtra("Branch","ECE");
                startActivity(intent1);
            }
        });

    }
}
