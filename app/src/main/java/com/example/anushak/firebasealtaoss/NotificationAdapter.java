package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class NotificationAdapter extends FirebaseNotificationAdapter<Notification> {

    private TextView title,message,date;

    public NotificationAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, Notification.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Notification model1) {

        title = (TextView) view.findViewById(R.id.title);
        title.setText(String.valueOf(model1.getNTitle().toString()));
        message=(TextView) view.findViewById(R.id.message);
        message.setText(String.valueOf(model1.getNMessage().toString()));
        date=(TextView) view.findViewById(R.id.date);
        date.setText(String.valueOf(model1.getNDate().toString()));
    }


}
