package com.example.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Subjects extends AppCompatActivity {

    String year;
    String branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_subjects);

        final Intent intent = getIntent();
        year = intent.getStringExtra("Year");
        branch = intent.getStringExtra("Branch");

        TextView sub1 = findViewById(R.id.subject1);
        TextView sub2 = findViewById(R.id.subject2);

        setTitle(year + " " + branch);

        final String sub1Text;
        final String sub2Text;

        if(year.equals("First Year")){

            sub1.setText("Maths");
            sub2.setText("Physics");
            sub1Text = "Maths";
            sub2Text = "Physics";

        }else{
            if(branch.equals("CSE")){

                sub1.setText("DBMS");
                sub2.setText("Data Structures");

                sub1Text = "DBMS";
                sub2Text = "Data Structures";

            }else{

                sub1.setText("Analog Electronics");
                sub2.setText("Digital Electronics");

                sub1Text = "Analog";
                sub2Text = "Digital";

            }
        }

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Subjects.this,PDFView.class);
                intent1.putExtra("Title",year + " " + branch + " " + sub1Text);
                startActivity(intent1);
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Subjects.this,PDFView.class);
                intent1.putExtra("Title",year + " " + branch + " " + sub2Text);
                startActivity(intent1);
            }
        });
    }
}
