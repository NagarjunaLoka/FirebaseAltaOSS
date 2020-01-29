package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    TextView name,empid,department,designation,gender,bloodgroup,dateofbirth,dateofjoining,officialemail,personalemail,address1,address2,contactnumber,referalcode;
    String Empid,Equery;
    DatabaseReference databaseReference;
    ImageView profile;
    TextView text,usertype,equery;
    HorizontalScrollView hsv1,hsv2;
    Animation frombottom,fromtop,fromleft,fromright;
    String Text,UserType;

    android.support.v7.widget.Toolbar toolbar;

    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_profile);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);
        equery = (TextView)findViewById(R.id.query);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);

        Equery = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        usertype.setText(UserType);
        equery.setText(Equery);

        String Name = "";
        String MiddleName = "";
        String LastName = "";
        String Empid = "";
        String Department = "";
        String Designation = "";
        String DateofBirth = "";
        String DateofJoining = "";
        String Gender = "";
        String BloodGroup = "";
        String OfficialEmail = "";
        String PersonalEmail = "";
        String Address1 = "";
        String Address2 = "";
        String UniqueID = "";
        String Phone = "";
        String ImageURL = "";

        Intent intent = getIntent();
        if (null != intent) {
            Name = intent.getStringExtra("name");
            MiddleName = getIntent().getStringExtra("middlename");
            LastName = getIntent().getStringExtra("lastname");
            Empid = getIntent().getStringExtra("empid");
            Department = getIntent().getStringExtra("department");
            Designation = getIntent().getStringExtra("designation");
            DateofBirth = getIntent().getStringExtra("dob");
            DateofJoining = getIntent().getStringExtra("doj");
            Gender = getIntent().getStringExtra("gender");
            BloodGroup = getIntent().getStringExtra("bloodgroup");
            OfficialEmail = getIntent().getStringExtra("officialemail");
            PersonalEmail = getIntent().getStringExtra("personalemail");
            Address1 = getIntent().getStringExtra("permanentaddress");
            Address2 = getIntent().getStringExtra("correspondanceaddress");
            UniqueID = getIntent().getStringExtra("referalcode");
            Phone = getIntent().getStringExtra("contactnumber");
            ImageURL = getIntent().getStringExtra("imageURL");
        }

        empid = (TextView)findViewById(R.id.empid);
        name = (TextView)findViewById(R.id.fullname);
        department = (TextView)findViewById(R.id.department);
        designation = (TextView)findViewById(R.id.designation);
        gender = (TextView)findViewById(R.id.gender);
        bloodgroup = (TextView)findViewById(R.id.bloodgroup);
        dateofbirth = (TextView)findViewById(R.id.dateofbirth);
        dateofjoining = (TextView)findViewById(R.id.dateofjoining);
        officialemail = (TextView)findViewById(R.id.officialemail);
        personalemail = (TextView)findViewById(R.id.personalemail);
        address1 = (TextView)findViewById(R.id.etPermanentAddress);
        address2 = (TextView)findViewById(R.id.etCorresspondenceAddress);
        contactnumber = (TextView)findViewById(R.id.contactnumber);
        referalcode = (TextView)findViewById(R.id.referalcode);
        profile = (ImageView)findViewById(R.id.profile_icon);

        Picasso.with(Profile.this).load(ImageURL).into(profile);
        empid.setText(Empid);
        name.setText(Name+" "+MiddleName+"."+LastName);
        department.setText(Department);
        designation.setText(Designation);
        gender.setText(Gender);
        bloodgroup.setText(BloodGroup);
        dateofbirth.setText(DateofBirth);
        dateofjoining.setText(DateofJoining);
        officialemail.setText(OfficialEmail);
        personalemail.setText(PersonalEmail);
        address1.setText(Address1);
        address2.setText(Address2);
        referalcode.setText(UniqueID);
        contactnumber.setText(Phone);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromleft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        fromright = AnimationUtils.loadAnimation(this,R.anim.fromright);

        hsv1.setAnimation(fromtop);
        hsv2.setAnimation(fromtop);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EmpDirectory.class);
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
                Intent intent = new Intent(Profile.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Directory - Profile");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Directory - Profile");
        }

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
            Equery = equery.getText().toString();
            Intent i = new Intent(Profile.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",Equery);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
