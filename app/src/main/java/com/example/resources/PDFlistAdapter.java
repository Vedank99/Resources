package com.example.resources;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

        TextView nameText =  (TextView) v.findViewById(R.id.namePDF);
        ImageView imageView = v.findViewById(R.id.pdfImage);
        nameText.setText(p.getName());

        v.setClickable(true);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReference();
                StorageReference islandRef = storageReference.child(p.getName() + ".pdf");

                Log.d("Root Directory : ", Environment.getExternalStorageDirectory().getAbsolutePath());

                final File outputFile = new File(Environment.getExternalStorageDirectory(), "" + p.getName() + ".pdf");

                final ProgressDialog pd = new ProgressDialog(mContext);
                pd.setMessage("Downloading " + p.getName() + ".pdf");
                pd.show();

                NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                        mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

                if (info == null) {
                    pd.dismiss();
                    Toast.makeText(mContext, "Couldn't Download the file", Toast.LENGTH_LONG).show();
                    Toast.makeText(mContext, "Make sure that the device is connected to the internet.", Toast.LENGTH_LONG).show();
                }else{
                final long TEN_MEGABYTE = 10 * 1024 * 1024;
                islandRef.getBytes(TEN_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {

                        pd.dismiss();

                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                            fileOutputStream.write(bytes);
                            fileOutputStream.close();
                            Toast.makeText(mContext, "File downloaded to Internal Storage/" + p.getName() + ".pdf", Toast.LENGTH_LONG).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri pdfUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", outputFile);
                        intent.setDataAndType(pdfUri, "application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        mContext.startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(mContext, "Couldn't Download the file", Toast.LENGTH_LONG).show();
                        Toast.makeText(mContext, "Make sure that the device is connected to the internet.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
            });
        return v;
    }
}
