package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class QueryAdapter extends FirebaseQueryAdapter<Equery> {

    private TextView qid,qempid, qemail, qsubject, qmessage, qstatus;

    public QueryAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, Equery.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Equery model) {

        qid = (TextView) view.findViewById(R.id.qid);
        qid.setText(String.valueOf(model.getQueryid().toString()));
        qempid = (TextView) view.findViewById(R.id.qempid);
        qempid.setText(String.valueOf(model.getEmpid().toString()));
        qemail = (TextView) view.findViewById(R.id.qemail);
        qemail.setText(String.valueOf(model.getEmail().toString()));
        qsubject = (TextView) view.findViewById(R.id.qsubject);
        qsubject.setText(String.valueOf(model.getSubject().toString()));
        qmessage = (TextView) view.findViewById(R.id.qmessage);
        qmessage.setText(String.valueOf(model.getQmessage().toString()));
        qstatus = (TextView) view.findViewById(R.id.qstatus);
        qstatus.setText(String.valueOf(model.getStatus().toString()));

    }
}
