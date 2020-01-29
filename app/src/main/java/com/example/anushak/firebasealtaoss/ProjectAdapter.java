package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class ProjectAdapter extends FirebaseProjectListAdapter<Project> {

    private TextView projectname;

    public ProjectAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, Project.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Project model1) {

        projectname = (TextView) view.findViewById(R.id.projectname);
        projectname.setText(String.valueOf(model1.getProjectname().toString()));

    }
}
