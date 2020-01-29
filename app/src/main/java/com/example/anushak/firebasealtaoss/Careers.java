package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Careers extends AppCompatActivity {

    ListView listView;
    private DatabaseReference mDatabase;
    private ListAdapter mList;
    android.support.v7.widget.Toolbar toolbar;

    TextView empid,userid,text,usertype;
    String Empid,UserId,Text,UserType;

    HorizontalScrollView hsv1,hsv2,hsv3;

    //Paths
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;

    //public static final String KEY_TITLE="jobtitle";
    public static final String KEY_VACANCIES="vacancies";
    public static final String KEY_EXPERIENCE="experience";
    public static final String KEY_BOND="bondperiod";
    public static final String KEY_SALARY="salary";
    public static final String KEY_JOBLOC="joblocation";
    public static final String KEY_INTERVIEWLOC="interviewlocation";
    public static final String KEY_SKILL="skillrequirments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_careers);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        empid = (TextView)findViewById(R.id.empid);
        mDatabase = FirebaseDatabase.getInstance().getReference("JobDetails");
        mList = new ListAdapter(mDatabase, this, R.layout.jobs_list);
        listView = (ListView) this.findViewById(R.id.listView);
        listView.setAdapter(mList);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");

        usertype.setText(UserType);
        userid.setText(UserId);
        empid.setText(Empid);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Careers.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Careers.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Careers.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Careers");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Careers");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Careers");
        }
        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                Job job = (Job) mList.getItem(position);

                Intent intent = new Intent(getApplicationContext(), Job_details.class);
                intent.putExtra("jobtitle", job.getJobtitle());
                intent.putExtra(KEY_VACANCIES, job.getVacancies());
                intent.putExtra(KEY_EXPERIENCE, job.getExperience());
                intent.putExtra(KEY_BOND, job.getBondperiod());
                intent.putExtra(KEY_SALARY, job.getSalary());
                intent.putExtra(KEY_JOBLOC, job.getJoblocation());
                intent.putExtra(KEY_INTERVIEWLOC, job.getInterviewlocation());
                intent.putExtra(KEY_SKILL, job.getSkillrequirments());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_job_alert,menu);
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();
        if(id == R.id.job)
        {
            if(UserType.equals("admin")||UserType.equals("hr")) {
                Intent intent = new Intent(Careers.this, CreateJob.class);
                intent.putExtra("empid",Empid);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
            else {
                Toast.makeText(Careers.this,"You Don't have permission to access.", Toast.LENGTH_LONG).show();
            }
        }
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(Careers.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
