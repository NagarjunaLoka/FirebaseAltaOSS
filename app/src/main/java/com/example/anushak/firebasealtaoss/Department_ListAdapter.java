package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public class Department_ListAdapter extends Department_FirebaseListAdapter<Department> {

    private TextView name;
    private  TextView empid;

    /**
     * @param mRef        The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                    combination of <code>limit()</code>, <code>startAt()</code>, and <code>endAt()</code>,
     * @param mModelClass Firebase will marshall the data at a location into an instance of a class that you provide
     * @param mLayout     This is the mLayout used to represent a single list item. You will be responsible for populating an
     *                    instance of the corresponding view with the data from an instance of mModelClass.
     * @param activity    The activity containing the ListView
     */

    public Department_ListAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, Department.class, mLayout, activity);

    }


    @Override
    protected void populateView(View view, Department model) {

        name = view.findViewById(R.id.name);
        name.setText(String.valueOf(model.getName()));
        empid =view.findViewById(R.id.empid);
        empid.setText(String.valueOf(model.getEmpid()));



    }
}

