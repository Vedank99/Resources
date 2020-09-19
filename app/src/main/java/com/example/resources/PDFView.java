package com.example.resources;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.resources.Fragments.BookFragment;
import com.example.resources.Fragments.NoteFragment;
import com.example.resources.Fragments.PaperFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class PDFView extends AppCompatActivity {

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_view);

        Intent intent = getIntent();
        title = intent.getStringExtra("Parent");

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new BookFragment(title)).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){

                    case R.id.navigation_books :
                        fragment = new BookFragment(title);
                        break;
                    case R.id.navigation_notes :
                        fragment = new NoteFragment(title);
                        break;
                    case R.id.navigation_papers :
                        fragment = new PaperFragment(title);
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
                return true;
            }
        });

    }



}
