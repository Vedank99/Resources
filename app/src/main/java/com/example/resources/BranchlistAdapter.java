package com.example.resources;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BranchlistAdapter extends ArrayAdapter<String> {

    private Context mContext;
    int mResource;
    private String title;
    private String path;


    public BranchlistAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, String path,String title) {
        super(context,resource,objects);
        mContext = context;
        mResource = resource;
        this.title = title;
        this.path = path;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            v = layoutInflater.inflate(mResource, parent, false);
        }

        final String name = getItem(position);

        TextView nameText =  (TextView) v.findViewById(R.id.nameList);
        nameText.setText(name);

        v.setClickable(true);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(title.equals("Subject"))
                    intent = new Intent(getContext(),PDFView.class);
                else
                    intent = new Intent(getContext(),Subjects.class);
                intent.putExtra("Parent",path+"/"+name);
                getContext().startActivity(intent);
            }
        });

        return v;
    }
}
