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

public class MyPay extends AppCompatActivity {

    TextView empid,empname,text,usertype,userid;
    Button paySlip,empReimbursement;
    String Empid,EmpName,Text,UserType,UserId,MiddleName,LastName;

    android.support.v7.widget.Toolbar toolbar;

    Button paySlip1,paySlip2;

    //Paths
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;

    HorizontalScrollView hsv1,hsv2,hsv3;
    Animation frombottom,fromtop,fromleft,fromright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my_pay);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        paySlip = (Button) findViewById(R.id.EmpPayslip);

        paySlip1 = (Button) findViewById(R.id.EmpPayslip1);
        paySlip2 = (Button) findViewById(R.id.EmpPayslip2);


        empReimbursement = (Button) findViewById(R.id.EmpReimbursement);
        empname = (TextView) findViewById(R.id.EmpName);
        empid = (TextView) findViewById(R.id.EmpID);
        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        EmpName = getIntent().getStringExtra("name");
        MiddleName = getIntent().getStringExtra("middlename");
        LastName = getIntent().getStringExtra("lastname");
        Empid=getIntent().getStringExtra("empid");

        empid.setText(Empid);
        empname.setText(EmpName+MiddleName+"."+LastName);
        userid.setText(UserId);
        usertype.setText(UserType);

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
                Intent intent = new Intent(MyPay.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(MyPay.this,HrDashboard.class);
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
                Intent intent = new Intent(MyPay.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - My Pay");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - My Pay");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - My Pay");
        }


        empReimbursement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyPay.this,ReimbursementUpload.class);
                i.putExtra("empid",Empid);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                startActivity(i);
            }
        });

        EmpName = empname.getText().toString();
        paySlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyPay.this, PaySlips.class);
                i.putExtra("empid",Empid);
                i.putExtra("name",EmpName);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                startActivity(i);
            }
        });

        paySlip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MyPay.this, Emp_PaySlips.class);
                i.putExtra("empid",Empid);
                i.putExtra("name",EmpName);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                startActivity(i);
            }
        });
        paySlip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MyPay.this, Select_date_in_Emp_PaySlips.class);
                i.putExtra("empid",Empid);
                i.putExtra("name",EmpName);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                startActivity(i);

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
            Intent i = new Intent(MyPay.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}



