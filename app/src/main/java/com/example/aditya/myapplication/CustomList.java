package com.example.aditya.myapplication;

/**
 * Created by aditya on 2/15/2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by aditya on 2/15/2016.
 */
class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private String _name_;
    private String _year_;
    private String _developer_;
    private String _icon_;

    public CustomList(Activity context, String[] name) {
        super(context, R.layout.list_single, name);
        this.context = context;
        this.name = name;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        final int pos = position + 1;
        final TextView name = (TextView) rowView.findViewById(R.id.txt1);
        final TextView developer = (TextView) rowView.findViewById(R.id.txt2);
        final TextView year = (TextView) rowView.findViewById(R.id.txt3);
        final ImageView icon = (ImageView) rowView.findViewById(R.id.img);


        Firebase.setAndroidContext(getContext());
        Firebase myref = new Firebase("https://aditya7.firebaseio.com/Games");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                _name_ = String.valueOf(snapshot.child(String.valueOf(pos)).child("Name").getValue());
                _year_ = String.valueOf(snapshot.child(String.valueOf(pos)).child("Year").getValue());
                _developer_ = String.valueOf(snapshot.child(String.valueOf(pos)).child("Developer").getValue());
                _icon_ = String.valueOf(snapshot.child(String.valueOf(pos)).child("Icon").getValue());
                name.setText(_name_);
                developer.setText(_developer_);
                year.setText(_year_);
                Picasso.with(getContext()).load(_icon_).into(icon);

            }

            @Override
            public void onCancelled(FirebaseError error) {
                System.out.println("lol error noob");
            }
        });

        return rowView;
    }
};

