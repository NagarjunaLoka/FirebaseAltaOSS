package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MyProfile extends AppCompatActivity {

    TextView name,empid,department,designation,gender,bloodgroup,dateofbirth,dateofjoining,officialemail,personalemail,address1,address2,contactnumber,referalcode;
    String Empid,Text,UserType,UserId;
    DatabaseReference databaseReference;
    android.support.v7.widget.Toolbar toolbar;
    TextView text,usertype,userid;
    HorizontalScrollView hsv1,hsv2,hsv3;
    Animation frombottom,fromtop,fromleft,fromright;

    ImageView profile_icon;

    //Paths
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my_profile);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
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
        profile_icon = (ImageView) findViewById(R.id.profile_icon);
        usertype = (TextView)findViewById(R.id.usertype);
        contactnumber = (TextView)findViewById(R.id.contactnumber);
        referalcode = (TextView)findViewById(R.id.referalcode);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        UserType = getIntent().getStringExtra("usertype");
        Empid = getIntent().getStringExtra("empid");

        empid.setText(Empid);
        usertype.setText(UserType);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromleft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        fromright = AnimationUtils.loadAnimation(this,R.anim.fromright);

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
                Intent intent = new Intent(MyProfile.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(MyProfile.this,HrDashboard.class);
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
                Intent intent = new Intent(MyProfile.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - My Profile");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - My Profile");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - My Profile");
        }

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Firstname = dataSnapshot.child("name").getValue().toString();
                String Middlename = dataSnapshot.child("middlename").getValue().toString();
                String Lastname = dataSnapshot.child("lastname").getValue().toString();
                String Empid = dataSnapshot.child("empid").getValue().toString();
                String Department = dataSnapshot.child("department").getValue().toString();
                String Designation = dataSnapshot.child("designation").getValue().toString();
                String Gender = dataSnapshot.child("gender").getValue().toString();
                String BloodGroup = dataSnapshot.child("bloodgroup").getValue().toString();
                String DateofBirth = dataSnapshot.child("dob").getValue().toString();
                String DateofJoining = dataSnapshot.child("doj").getValue().toString();
                String OfficialEmail = dataSnapshot.child("officialemail").getValue().toString();
                String PersonalEmail = dataSnapshot.child("personalemail").getValue().toString();
                String Address1 = dataSnapshot.child("permanentaddress").getValue().toString();
                String Address2 = dataSnapshot.child("correspondanceaddress").getValue().toString();
                String Contactnumber = dataSnapshot.child("phone").getValue().toString();
                String Referalcode = dataSnapshot.child("referalcode").getValue().toString();
                String Image=dataSnapshot.child("imageURL").getValue().toString();
                String UserId = dataSnapshot.child("userId").getValue().toString();

                Picasso.with(MyProfile.this).load(Image).into(profile_icon);
                empid.setText(Empid);
                name.setText(Firstname+" "+Middlename+"."+Lastname);
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
                contactnumber.setText(Contactnumber);
                referalcode.setText(Referalcode);
                userid.setText(UserId);

                name.setAnimation(fromleft);
                empid.setAnimation(fromright);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
            Intent i = new Intent(MyProfile.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
