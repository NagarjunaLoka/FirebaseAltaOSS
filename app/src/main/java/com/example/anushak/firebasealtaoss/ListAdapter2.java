package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class ListAdapter2 extends FirebaseReimbursementAdapter<Reimbursement> {
    private TextView empid, billdate,month,year, dateandtime;

    public ListAdapter2(Query mRef, Activity activity, int mLayout) {
        super(mRef, Reimbursement.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Reimbursement model) {

        empid = (TextView) view.findViewById(R.id.empid);
        empid.setText(model.getType().toString());

        billdate=(TextView)view.findViewById(R.id.billdate);
        billdate.setText(model.getBilldate().toString());

        dateandtime=(TextView) view.findViewById(R.id.dateandtime);
        dateandtime.setText(model.getUpdate().toString());

        month=(TextView) view.findViewById(R.id.month);
        month.setText(model.getMonth().toString());

        year=(TextView) view.findViewById(R.id.year);
        year.setText(model.getYear().toString());

    }
}
