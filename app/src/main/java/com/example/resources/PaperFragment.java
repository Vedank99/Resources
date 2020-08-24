package com.example.resources;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PaperFragment extends Fragment {

    DatabaseReference databaseReference;

    ListView listView;
    List<pdfs> pdfsList;
    PDFlistAdapter adapter;
    Context context;

    public PaperFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_papers, container, false);


        pdfsList = new ArrayList<>();
        listView = v.findViewById(R.id.papersList);

        context = MainActivity.mContext;


        adapter = new PDFlistAdapter(getActivity(),R.layout.pdf_layout,pdfsList);
        listView.setAdapter(adapter);

        viewAllFiles();

        return v;
    }

    private void viewAllFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Papers");
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

}
