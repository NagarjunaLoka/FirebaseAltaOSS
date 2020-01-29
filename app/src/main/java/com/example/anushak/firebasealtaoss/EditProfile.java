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
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EditProfile extends AppCompatActivity {

    TextView name,empid,department,designation,gender,bloodgroup,dateofbirth,dateofjoining,officialemail,personalemail,address1,address2,contactnumber,referalcode;
    String Empid,Text,UserType,UserId;
    DatabaseReference databaseReference;
    android.support.v7.widget.Toolbar toolbar;
    TextView text,usertype,userid;
    HorizontalScrollView hsv1,hsv2,hsv3;
    Animation frombottom,fromtop,fromleft,fromright;
    ImageView profile_icon;

    Button save;
    ImageView button;
    EditText edepartment,edesignation,egender,ebloodgroup,edateofbirth,edateofjoining,eofficialemail,epersonalemail,eaddress1,eaddress2,econtactnumber;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_edit_profile);

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
        contactnumber = (TextView)findViewById(R.id.contactnumber);
        referalcode = (TextView)findViewById(R.id.referalcode);
        profile_icon = (ImageView) findViewById(R.id.profile_icon);
        usertype = (TextView)findViewById(R.id.usertype);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        button = (ImageView) findViewById(R.id.button);
        save = (Button) findViewById(R.id.save);

        edepartment = (EditText)findViewById(R.id.edepartment);
        edesignation = (EditText)findViewById(R.id.edesignation);
        egender = (EditText)findViewById(R.id.egender);
        ebloodgroup = (EditText)findViewById(R.id.ebloodgroup);
        edateofbirth = (EditText)findViewById(R.id.edateofbirth);
        edateofjoining = (EditText)findViewById(R.id.edateofjoining);
        eofficialemail = (EditText)findViewById(R.id.eofficialemail);
        epersonalemail = (EditText)findViewById(R.id.epersonalemail);
        eaddress1 = (EditText)findViewById(R.id.ePermanentAddress);
        eaddress2 = (EditText)findViewById(R.id.eCorresspondenceAddress);
        econtactnumber = (EditText)findViewById(R.id.econtactnumber);

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

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        save.setVisibility(View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Edit Profile");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Edit Profile");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Edit Profile");
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
                Intent intent = new Intent(EditProfile.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(EditProfile.this,HrDashboard.class);
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
                Intent intent = new Intent(EditProfile.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

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

                Picasso.with(EditProfile.this).load(Image).into(profile_icon);
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

                edepartment.setText(Department);
                edesignation.setText(Designation);
                egender.setText(Gender);
                ebloodgroup.setText(BloodGroup);
                edateofbirth.setText(DateofBirth);
                edateofjoining.setText(DateofJoining);
                eofficialemail.setText(OfficialEmail);
                epersonalemail.setText(PersonalEmail);
                eaddress1.setText(Address1);
                eaddress2.setText(Address2);
                econtactnumber.setText(Contactnumber);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                department.setVisibility(View.GONE);
                designation.setVisibility(View.GONE);
                gender.setVisibility(View.GONE);
                bloodgroup.setVisibility(View.GONE);
                dateofbirth.setVisibility(View.GONE);
                dateofjoining.setVisibility(View.GONE);
                officialemail.setVisibility(View.GONE);
                personalemail.setVisibility(View.GONE);
                address1.setVisibility(View.GONE);
                address2.setVisibility(View.GONE);
                contactnumber.setVisibility(View.GONE);

                edepartment.setVisibility(View.VISIBLE);
                edesignation.setVisibility(View.VISIBLE);
                egender.setVisibility(View.VISIBLE);
                ebloodgroup.setVisibility(View.VISIBLE);
                edateofbirth.setVisibility(View.VISIBLE);
                edateofjoining.setVisibility(View.VISIBLE);
                eofficialemail.setVisibility(View.VISIBLE);
                epersonalemail.setVisibility(View.VISIBLE);
                eaddress1.setVisibility(View.VISIBLE);
                eaddress2.setVisibility(View.VISIBLE);
                econtactnumber.setVisibility(View.VISIBLE);

                save.setVisibility(View.VISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();

                department.setVisibility(View.VISIBLE);
                designation.setVisibility(View.VISIBLE);
                gender.setVisibility(View.VISIBLE);
                bloodgroup.setVisibility(View.VISIBLE);
                dateofbirth.setVisibility(View.VISIBLE);
                dateofjoining.setVisibility(View.VISIBLE);
                officialemail.setVisibility(View.VISIBLE);
                personalemail.setVisibility(View.VISIBLE);
                address1.setVisibility(View.VISIBLE);
                address2.setVisibility(View.VISIBLE);
                contactnumber.setVisibility(View.VISIBLE);

                edepartment.setVisibility(View.GONE);
                edesignation.setVisibility(View.GONE);
                egender.setVisibility(View.GONE);
                ebloodgroup.setVisibility(View.GONE);
                edateofbirth.setVisibility(View.GONE);
                edateofjoining.setVisibility(View.GONE);
                eofficialemail.setVisibility(View.GONE);
                epersonalemail.setVisibility(View.GONE);
                eaddress1.setVisibility(View.GONE);
                eaddress2.setVisibility(View.GONE);
                econtactnumber.setVisibility(View.GONE);
            }
        });

    }

    public void submit(){

        String Edepartment = edepartment.getText().toString();
        String Edesignation = edesignation.getText().toString();
        String Egender = egender.getText().toString();
        String Ebloodgroup = ebloodgroup.getText().toString();
        String Edateofbirth = edateofbirth.getText().toString();
        String Edateofjoining = edateofjoining.getText().toString();
        String Eofficialemail = eofficialemail.getText().toString();
        String Epersonalemail = epersonalemail.getText().toString();
        String Eaddress1 = eaddress1.getText().toString();
        String Eaddress2 = eaddress2.getText().toString();
        String Econtactnumber = econtactnumber.getText().toString();
        String Referalcode = referalcode.getText().toString();

        updateuser(Edepartment,Edesignation,Egender,Ebloodgroup,Edateofbirth,Edateofjoining,Eofficialemail,Epersonalemail,Eaddress1,Eaddress2,Econtactnumber,Referalcode);

    }

    private boolean updateuser(String department, String designation, String gender, String bloodgroup, String dateofbirth, String dateofjoining, String officialemail, String personalemail, String address1, String address2, String contactnumber, String referalcode) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("department");
        DatabaseReference dR1 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("designation");
        DatabaseReference dR2 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("gender");
        DatabaseReference dR3 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("bloodgroup");
        DatabaseReference dR4 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("dateofbirth");
        DatabaseReference dR5 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("dateofjoining");
        DatabaseReference dR6 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("officialemail");
        DatabaseReference dR7 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("personalemail");
        DatabaseReference dR8 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("permanentaddress");
        DatabaseReference dR9 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("correspondanceaddress");
        DatabaseReference dR10 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone");
        DatabaseReference dR11 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("referalcode");

        //updating users
        dR.setValue(department);
        dR1.setValue(designation);
        dR2.setValue(gender);
        dR3.setValue(bloodgroup);
        dR4.setValue(dateofbirth);
        dR5.setValue(dateofjoining);
        dR6.setValue(officialemail);
        dR7.setValue(personalemail);
        dR8.setValue(address1);
        dR9.setValue(address2);
        dR10.setValue(contactnumber);
        dR11.setValue(referalcode);

        Toast.makeText(getApplicationContext(), "Details Updated", Toast.LENGTH_LONG).show();
        return true;
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
            Intent i = new Intent(EditProfile.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
