package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class VisitorDetails extends AppCompatActivity {

    TextView fname, lname, email, phone, contactperson, empid, companyname, companybranch, dateofvisit, reasonforvisit, personalid, typeofvisit;
    DatabaseReference databaseReference;

    TextView text,userid,usertype;
    String Text,UserId,UserType;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2,visitor;
    android.support.v7.widget.Toolbar toolbar;
    Animation fromtop;
    HorizontalScrollView hsv1,hsv2,hsv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_visitor_details);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);
        list2 = (TextView)findViewById(R.id.list2);
        visitor = (TextView)findViewById(R.id.visitor);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        String Fname ="";
        String Lname = "";
        String Email = "";
        String Phone = "";
        String Contactperson = "";
        String Empid = "";
        String Companyname = "";
        String CompanyBranch = "";
        String Dateofvisit = "";
        String Reasonforvisit = "";
        String Personalid = "";
        String Typeofvisit = "";

        Intent intent = getIntent();
        if(null != intent){
            Fname = getIntent().getStringExtra("fname");
            Lname = getIntent().getStringExtra("lname");
            Email = getIntent().getStringExtra("email");
            Phone = getIntent().getStringExtra("phone");
            Contactperson = getIntent().getStringExtra("contactperson");
            Empid = getIntent().getStringExtra("empid");
            Companyname = getIntent().getStringExtra("companyname");
            CompanyBranch = getIntent().getStringExtra("companybranch");
            Dateofvisit = getIntent().getStringExtra("dateofvisit");
            Reasonforvisit = getIntent().getStringExtra("reasonforvisit");
            Personalid = getIntent().getStringExtra("personalid");
            Typeofvisit = getIntent().getStringExtra("typeofvisit");

        }

        fname =findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        contactperson = findViewById(R.id.contactperson);
        empid = findViewById(R.id.empid);
        companyname = findViewById(R.id.companyname);
        companybranch = findViewById(R.id.companybranch);
        dateofvisit = findViewById(R.id.dateofvisit);
        reasonforvisit = findViewById(R.id.reasonforvisit);
        personalid = findViewById(R.id.personalid);
        typeofvisit = findViewById(R.id.typeofvisit);

        fname.setText(Fname);
        lname.setText(Lname);
        email.setText(Email);
        phone.setText(Phone);
        contactperson.setText(Contactperson);
        empid.setText(Empid);
        companyname.setText(Companyname);
        companybranch.setText(CompanyBranch);
        dateofvisit.setText(Dateofvisit);
        reasonforvisit.setText(Reasonforvisit);
        personalid.setText(Personalid);
        typeofvisit.setText(Typeofvisit);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        usertype.setText(UserType);
        userid.setText(UserId);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

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
                Intent intent = new Intent(VisitorDetails.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDetails.this, VisitorDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        visitor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDetails.this, VisDirectory.class);
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
                Intent intent = new Intent(VisitorDetails.this, HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDetails.this, VisDirectory.class);
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
                Intent intent = new Intent(VisitorDetails.this, EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDetails.this, VisDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin") ? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr") ? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("employee") ? View.VISIBLE : View.GONE);

        if (UserType.equals("employee")) {
            text.setText("Employee Dashboard - My Visitors - visitor Details (" + Fname+Lname+" )");
        } else if (UserType.equals("hr")) {
            text.setText("HR Dashboard - My Visitors - visitor Details (" + Fname+Lname+" )");
        } else if (UserType.equals("admin")) {
            text.setText("Admin Dashboard - Visitor Dashboard - My Visitor - visitor Details (" + Fname+Lname+" )");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.queryform,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(VisitorDetails.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
