package com.example.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class Years extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageReference;
    String curDir = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        curDir = storageReference.getPath();

        TextView firstYear = findViewById(R.id.firstYear);
        TextView secondYear = findViewById(R.id.secondYear);
        TextView thirdYear = findViewById(R.id.thirdYear);
        TextView fourthYear = findViewById(R.id.fourthYear);

        //Toast.makeText(this,"Storage Path : "+curDir,Toast.LENGTH_LONG).show();

        firstYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Subjects.class);
                intent.putExtra("Year","First Year");
                intent.putExtra("Branch","");
                startActivity(intent);
            }
        });

        secondYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Branches.class);
                intent.putExtra("Year","Second Year");
                startActivity(intent);
            }
        });

        thirdYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Branches.class);
                intent.putExtra("Year","Third Year");
                startActivity(intent);
            }
        });

        fourthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Years.this,Branches.class);
                intent.putExtra("Year","Fourth Year");
                startActivity(intent);
            }
        });

    }
}
