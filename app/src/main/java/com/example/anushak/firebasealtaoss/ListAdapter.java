package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.Query;


public class ListAdapter extends FirebaseListAdapter<Job> {
    private TextView jobtitle, vacancies, joblocation, experience, salary;

    public ListAdapter(Query mRef, Activity activity, int mLayout) {
        super(mRef, Job.class, mLayout, activity);
    }

    @Override
    protected void populateView(View view, Job model) {

        jobtitle = (TextView) view.findViewById(R.id.jobtitle);
        jobtitle.setText(String.valueOf(model.getJobtitle().toString()));
        vacancies = (TextView) view.findViewById(R.id.noofvacancies);
        vacancies.setText(String.valueOf(model.getVacancies().toString()));
        joblocation = (TextView) view.findViewById(R.id.joblocation);
        joblocation.setText(String.valueOf(model.getJoblocation()));
        experience = (TextView) view.findViewById(R.id.experience);
        experience.setText(String.valueOf(model.getExperience()));
        salary = (TextView) view.findViewById(R.id.salary);
        salary.setText(String.valueOf(model.getSalary()));
    }
}
