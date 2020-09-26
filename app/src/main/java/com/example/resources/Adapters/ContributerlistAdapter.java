package com.example.resources.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.resources.R;
import com.example.resources.contributer;

import java.util.List;

public class ContributerlistAdapter extends ArrayAdapter<contributer> {

    Context mContext;
    int mResource;

    public ContributerlistAdapter(@NonNull Context context, int resource,@NonNull List<contributer> objects) {
        super(context, resource,objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            v = layoutInflater.inflate(mResource, parent, false);
        }

        contributer cont = getItem(position);
        TextView name = v.findViewById(R.id.contributerName);
        TextView type = v.findViewById(R.id.contributerType);
        ImageView imageView = v.findViewById(R.id.contributerPic);

        name.setText(cont.getName());
        type.setText(cont.getType());
        imageView.setImageResource(cont.getImageID());

        return v;
    }
}
