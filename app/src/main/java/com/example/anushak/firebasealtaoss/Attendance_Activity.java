package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Attendance_Activity extends AppCompatActivity{

    TextView attemail,attname;
    String Attemail,Attname;

    ImageView profile;
    String ImageURL;

    TextView text,userid,usertype,empid,applyleave;
    String Text,UserId,UserType,Empid;

    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,att,att1,att2;

    HorizontalScrollView hsv1,hsv2,hsv3;
    Animation frombottom,fromtop,fromleft,fromright;
    android.support.v7.widget.Toolbar toolbar;
    Button yearlyattendance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_attendance_);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        attemail=(TextView) findViewById(R.id.attemail);
        attname=(TextView) findViewById(R.id.attname);
        profile=(ImageView) findViewById(R.id.profile_image);

        applyleave = (TextView) findViewById(R.id.applyleave);
        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        att1 = (TextView)findViewById(R.id.att1);
        att2 = (TextView)findViewById(R.id.att2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        yearlyattendance=findViewById(R.id.yearlyattendance);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");

        ImageURL = getIntent().getStringExtra("imageURL");
        Picasso.with(Attendance_Activity.this).load(ImageURL).into(profile);

        Attemail = getIntent().getStringExtra("officialemail");
        attemail.setText(Attemail);

        Attname = getIntent().getStringExtra("name");
        attname.setText(Attname);

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(Empid);

        hsv1.setAnimation(fromtop);
        hsv2.setAnimation(fromtop);
        hsv3.setAnimation(fromtop);

        applyleave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance_Activity.this,Leave.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("empid",Empid);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance_Activity.this,EmployeeDashboard.class);
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

        att1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance_Activity.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance_Activity.this,HrDashboard.class);
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

        att2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance_Activity.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance_Activity.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Attendance");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Emp Directory - Attendance");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Emp Directory - Attendance");
        }


        yearlyattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance_Activity.this,YearlyAttendance.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("empid",Empid);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                intent.putExtra("officialemail",Attemail);
                intent.putExtra("name",Attname);
                intent.putExtra("imageURL",ImageURL);
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

        int id = item.getItemId();
        if (id == R.id.query) {
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(Attendance_Activity.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}

