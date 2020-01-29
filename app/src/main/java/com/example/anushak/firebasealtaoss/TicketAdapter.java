package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

import java.util.List;

public class TicketAdapter extends FirebaseTicketAdapter<User> {

    private TextView name, empid, department, email;

    private List<Artist> userList;

    public TicketAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, User.class, mLayout, activity);

    }

    @Override
    protected void populateView(View view, User model) {

        name = (TextView) view.findViewById(R.id.name);
        name.setText(String.valueOf(model.getName()));
        empid = (TextView) view.findViewById(R.id.empid);
        empid.setText(String.valueOf(model.getEmpid()));
        department = (TextView) view.findViewById(R.id.dept);
        department.setText(String.valueOf(model.getDepartment()));
        email = view.findViewById(R.id.email);
        email.setText(String.valueOf(model.getEmail()));


    }




}
