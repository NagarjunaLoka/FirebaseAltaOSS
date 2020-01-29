package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.anushak.firebasealtaoss.Careers.KEY_BOND;
import static com.example.anushak.firebasealtaoss.Careers.KEY_EXPERIENCE;
import static com.example.anushak.firebasealtaoss.Careers.KEY_INTERVIEWLOC;
import static com.example.anushak.firebasealtaoss.Careers.KEY_JOBLOC;
import static com.example.anushak.firebasealtaoss.Careers.KEY_SALARY;
import static com.example.anushak.firebasealtaoss.Careers.KEY_SKILL;
import static com.example.anushak.firebasealtaoss.Careers.KEY_VACANCIES;

public class Job_details extends AppCompatActivity {

    TextView title,vacancies,bondperiod,experience,salary,joblocation,interviewlocation,skills,userid,text,usertype;

    String Title = "",Text,UserType;
    String Vacancies = "";
    String Experience = "";
    String BondPeriod = "";
    String Salary = "";
    String JobLocation = "";
    String Interview = "";
    String Skills = "";
    String UserId = "";

    android.support.v7.widget.Toolbar toolbar;

    HorizontalScrollView hsv1,hsv2,hsv3;

    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_job_details);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);

        UserType = getIntent().getStringExtra("usertype");

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);
        list2 = (TextView)findViewById(R.id.list2);

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);


        Intent intent = getIntent();
        if (null != intent) {
            Title = intent.getStringExtra("jobtitle");
            Vacancies = intent.getStringExtra(KEY_VACANCIES);
            Experience = intent.getStringExtra(KEY_EXPERIENCE);
            BondPeriod = intent.getStringExtra(KEY_BOND);
            Salary = intent.getStringExtra(KEY_SALARY);
            JobLocation = intent.getStringExtra(KEY_JOBLOC);
            Interview = intent.getStringExtra(KEY_INTERVIEWLOC);
            Skills = intent.getStringExtra(KEY_SKILL);
            UserId = intent.getStringExtra("userId");
        }

        title = (TextView)findViewById(R.id.title);
        vacancies = (TextView)findViewById(R.id.vacancies);
        bondperiod = (TextView)findViewById(R.id.bondperiod);
        experience = (TextView)findViewById(R.id.experience);
        salary = (TextView)findViewById(R.id.salary);
        joblocation = (TextView)findViewById(R.id.joblocation);
        interviewlocation = (TextView)findViewById(R.id.interviewlocation);
        skills = (TextView)findViewById(R.id.skills);
        userid = (TextView)findViewById(R.id.userid);

        title.setText(Title);
        vacancies.setText(Vacancies);
        experience.setText(Experience);
        bondperiod.setText(BondPeriod);
        salary.setText(Salary);
        joblocation.setText(JobLocation);
        interviewlocation.setText(Interview);
        skills.setText(Skills);
        userid.setText(UserId);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Careers - Job Details");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Careers - Job Details");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Careers - Job Details");
        }

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Job_details.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Job_details.this,Careers.class);
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
                Intent intent = new Intent(Job_details.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Job_details.this,Careers.class);
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
                Intent intent = new Intent(Job_details.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Job_details.this,Careers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(Job_details.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
