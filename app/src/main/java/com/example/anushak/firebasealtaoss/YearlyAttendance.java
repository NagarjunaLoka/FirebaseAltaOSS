package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class YearlyAttendance extends AppCompatActivity {

    TextView attemail,attname;
    String Attemail,Attname;

    ImageView profile;
    String ImageURL;

    TextView text,userid,usertype,empid;
    String Text,UserId,UserType,Empid;

    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,attendance,attendance1,attendance2,att1,att2;

    HorizontalScrollView hsv1,hsv2,hsv3;
    Animation frombottom,fromtop,fromleft,fromright;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_yearly_attendance);

        attemail=(TextView) findViewById(R.id.attemail);
        attname=(TextView) findViewById(R.id.attname);
        profile=(ImageView) findViewById(R.id.profile_image);

        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        att1 = (TextView)findViewById(R.id.att1);
        att2 = (TextView)findViewById(R.id.att2);

        attendance = (TextView)findViewById(R.id.attendance);
        attendance1 = (TextView)findViewById(R.id.attendance1);
        attendance2 = (TextView)findViewById(R.id.attendance2);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");

        ImageURL = getIntent().getStringExtra("imageURL");
        Picasso.with(YearlyAttendance.this).load(ImageURL).into(profile);

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


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YearlyAttendance.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YearlyAttendance.this,Attendance_Activity.class);
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
                Intent intent = new Intent(YearlyAttendance.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        att1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YearlyAttendance.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        attendance1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YearlyAttendance.this,Attendance_Activity.class);
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
                Intent intent = new Intent(YearlyAttendance.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        att2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YearlyAttendance.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        attendance2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YearlyAttendance.this,Attendance_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Attendance - Yearly Attendance");
        }
        else if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Directory - Attendance - Yearly Attendance");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Directory - Attendance - Yearly Attendance");
        }

    }
}
