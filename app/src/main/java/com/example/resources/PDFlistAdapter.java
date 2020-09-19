package com.example.resources;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class PDFlistAdapter extends ArrayAdapter<pdfs> {

    private Context mContext;
    int mResource;


    public PDFlistAdapter(@NonNull Context context, int resource, @NonNull List<pdfs> objects) {
        super(context,resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            v = layoutInflater.inflate(mResource, parent, false);
        }

        final pdfs p = getItem(position);
        final String fileName = p.getName();

        TextView nameText =  (TextView) v.findViewById(R.id.namePDF);
        nameText.setText(fileName);

        v.setClickable(true);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                        mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

                if (info == null) {
                    Toast.makeText(mContext, "Couldn't Download the file", Toast.LENGTH_LONG).show();
                    Toast.makeText(mContext, "Make sure that the device is connected to the internet.", Toast.LENGTH_LONG).show();
                }else{
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(p.getUrl()));
                    request.setDescription("Resources");
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

                    DownloadManager manager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
            }
        }
            });
        return v;
    }
}
