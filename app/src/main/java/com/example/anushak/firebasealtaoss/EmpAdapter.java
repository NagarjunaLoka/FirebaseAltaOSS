package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmpAdapter extends FirebaseEmpAdapter<User> {

    private TextView Name, Empid, Designation;
    ImageView profile;
    Context context;

    public EmpAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, User.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, final User model) {
        Name = (TextView) view.findViewById(R.id.name);
        Name.setText(String.valueOf(model.getName().toString()) + model.getMiddlename().toString() + "." + model.getLastname().toString());
        Empid = (TextView) view.findViewById(R.id.empid);
        Empid.setText(String.valueOf(model.getEmpid().toString()));
        Designation = (TextView) view.findViewById(R.id.designation);
        Designation.setText(String.valueOf(model.getDesignation()));
        profile = (ImageView) view.findViewById(R.id.profileImageView);
        Picasso.with(context).load(String.valueOf(model.getImageURL())).into(profile);
    }

}

