package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class EventAdapter extends FirebaseEventAdapter<Event> {
    private TextView eid,ename,edes,egdate,etodate;

    public EventAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, Event.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Event model) {

        eid = (TextView) view.findViewById(R.id.fstline);
        eid.setText(model.getEventid().toString());
        ename = (TextView) view.findViewById(R.id.evnme);
        ename.setText(model.getEventname().toString());
        edes = (TextView) view.findViewById(R.id.evdes);
        edes.setText(String.valueOf(model.getEventdes()));
        egdate = (TextView) view.findViewById(R.id.godate);
        egdate.setText(String.valueOf(model.getGodate()));
        etodate = (TextView) view.findViewById(R.id.todate);
        etodate.setText(String.valueOf(model.getTodate()));

    }
}