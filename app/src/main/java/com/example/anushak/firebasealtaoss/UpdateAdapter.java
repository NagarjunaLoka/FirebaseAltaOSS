package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class UpdateAdapter extends FirebaseEmpAdapter<User> {

    private TextView projectname, notes, empid,dateandtime;


    public UpdateAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, User.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, User model) {

        projectname = (TextView) view.findViewById(R.id.projectname);
        projectname.setText(String.valueOf(model.getProjectname()));
        notes = (TextView) view.findViewById(R.id.notes);
        notes.setText(String.valueOf(model.getTaskstatus()));
        empid = (TextView) view.findViewById(R.id.empid);
        empid.setText(String.valueOf(model.getEmpid()));
        dateandtime = (TextView) view.findViewById(R.id.dateandtime);
        dateandtime.setText(String.valueOf(model.getDate()));

    }
}
