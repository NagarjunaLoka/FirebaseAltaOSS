package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class VisAdapter extends FirebaseVisAdapter<VisUser> {
    private TextView firstname, lastname, companyname, companybranch, contactperson, phone, reasonforvisit, dateofvisit;
    CircleImageView profileImageView;
    String Image;
    Context context;

    public VisAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, VisUser.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, VisUser model) {

        profileImageView=(CircleImageView) view.findViewById(R.id.profileImageView);
        firstname = (TextView) view.findViewById(R.id.fname);
        firstname.setText(String.valueOf(model.getFname().toString()));
        lastname = (TextView) view.findViewById(R.id.lname);
        lastname.setText(String.valueOf(model.getLname().toString()));
        companyname = (TextView) view.findViewById(R.id.comname);
        companyname.setText(String.valueOf(model.getCompanyname().toString()));
        companybranch = (TextView) view.findViewById(R.id.combranch);
        companybranch.setText(String.valueOf(model.getCompanybranch().toString()));
        contactperson = (TextView) view.findViewById(R.id.contactperson);
        contactperson.setText(String.valueOf(model.getContactperson().toString()));
        phone = (TextView) view.findViewById(R.id.phone);
        phone.setText(String.valueOf(model.getPhone().toString()));
        reasonforvisit = (TextView) view.findViewById(R.id.reasonforvisit);
        reasonforvisit.setText(String.valueOf(model.getReasonforvisit().toString()));
        dateofvisit = (TextView) view.findViewById(R.id.dateofvisit);
        dateofvisit.setText(String.valueOf(model.getDateofvisit().toString()));
        Image=model.getProfile().toString();

        Picasso.with(view.getContext()).load(Image).into(profileImageView);


    }

}