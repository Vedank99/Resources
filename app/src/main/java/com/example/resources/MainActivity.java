package com.example.resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_STORAGE_REQUEST_CODE = 0;
    DatabaseReference databaseReference;

    ListView listView;
    List<pdfs> pdfsList;
    PDFlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfsList = new ArrayList<>();
        listView = findViewById(R.id.rootList);


        adapter = new PDFlistAdapter(this,R.layout.pdf_layout,pdfsList);
        listView.setAdapter(adapter);

        getPermissions();

        viewAllFiles();


    }

    private void viewAllFiles() {
            databaseReference = FirebaseDatabase.getInstance().getReference("Data");
            Log.d("size before :", "pdf size " + pdfsList.size());

            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d("Size of pdfFile", "" + pdfsList.size());
                    pdfs currentPDF = snapshot.getValue(pdfs.class);
                    pdfsList.add(currentPDF);
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