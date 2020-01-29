package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class ReimbursementAdapter extends FirebaseReimbursementAdapter<Reimbursement> {
    private TextView empid, category,update, dateandtime;


    public ReimbursementAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, Reimbursement.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Reimbursement model) {

        empid = (TextView) view.findViewById(R.id.empid);
        empid.setText(model.getEmid().toString());

        category=(TextView)view.findViewById(R.id.category);
        category.setText(model.getType().toString());

        dateandtime=(TextView) view.findViewById(R.id.dateandtime);
        dateandtime.setText(model.getUploaded().toString());

        update=(TextView) view.findViewById(R.id.update);
        update.setText(model.getUpdate().toString());

    }
}
