package com.example.resources.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.resources.Adapters.ContributerlistAdapter;
import com.example.resources.ContributerDetails;
import com.example.resources.R;
import com.example.resources.contributer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class About extends AppCompatActivity {

    Toolbar toolbar;
    List<contributer> contributersList;
    ContributerlistAdapter contributerlistAdapter;
    ListView contributersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.aboutToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        contributersList = new ArrayList<contributer>();

        contributer c1 = new contributer("Vedank Goyal","Development","vedankgoyal99@gmail.com","https://www.facebook.com/vedank.goyal.7",R.drawable.vedank_pic);
        contributer c2 = new contributer("Sameer Bargota","Design","","",R.drawable.nodata_icon);

        contributersList.add(c1);
        contributersList.add(c2);

        contributersListView = findViewById(R.id.contributersList);

        contributerlistAdapter = new ContributerlistAdapter(this,R.layout.contributer_list,contributersList);
        contributersListView.setAdapter(contributerlistAdapter);

        contributerlistAdapter.notifyDataSetChanged();

        contributersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityOptions options = null;
                Intent intent = new Intent(About.this, ContributerDetails.class);
                intent.putExtra("Cont",contributersList.get(position));

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(About.this,view,"img_transition");
                    startActivity(intent, options.toBundle());
                }
                else
                    startActivity(intent);
            }
        });

    }
}