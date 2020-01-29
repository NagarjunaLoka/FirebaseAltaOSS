package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;


public class ListAdapter1 extends FirebaseListAdapter1<Intervi> {
    private TextView interid,intername,fresh,mobile,designation;

    public ListAdapter1(Query mRef, Activity activity, int mLayout) {
        super(mRef, Intervi.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Intervi model) {

        interid = (TextView) view.findViewById(R.id.inteid);
        interid.setText(model.getEmail().toString());

        intername = (TextView) view.findViewById(R.id.intername);
        intername.setText(model.getFull_name().toString());

         fresh= (TextView) view.findViewById(R.id.fresh);
        fresh.setText(model.getFreshers().toString());

        mobile = (TextView) view.findViewById(R.id.mob);
        mobile.setText(model.getMobile().toString());

        designation = (TextView) view.findViewById(R.id.desg);
        designation.setText(model.getDesign().toString());

    }
}