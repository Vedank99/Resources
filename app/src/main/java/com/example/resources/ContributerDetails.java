package com.example.resources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContributerDetails extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView type;

    ImageView mail;
    ImageView social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributer_details);

        Intent intent = getIntent();
        final contributer cont = (contributer) intent.getSerializableExtra("Cont");

        image = findViewById(R.id.contImage);
        name = findViewById(R.id.contName);
        type = findViewById(R.id.contType);
        mail = findViewById(R.id.contMail);
        social = findViewById(R.id.contFacebook);

        image.setImageResource(cont.getImageID());
        name.setText(cont.getName());
        type.setText(cont.getType());

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { cont.getEmail() });
                intent.putExtra(Intent.EXTRA_SUBJECT, "DTU Resources");
                intent.putExtra(Intent.EXTRA_TEXT, "Query/Feedback");
                startActivity(intent);
            }
        });

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String link = cont.getSocial();

                if(link.isEmpty())
                    Toast.makeText(ContributerDetails.this,"Developer Not On Facebok", Toast.LENGTH_SHORT).show();
                else {
                    Uri webpage = Uri.parse(link);
                    Intent browse = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(browse);
                }

            }
        });

    }
}