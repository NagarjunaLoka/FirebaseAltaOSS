package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Permissions extends AppCompatActivity {

    String Text,UserType,Empid,UserId;
    TextView text,usertype,empid,userId;

    android.support.v7.widget.Toolbar toolbar;
    HorizontalScrollView hsv1;
    Animation frombottom,fromtop,fromleft,fromright;

    LinearLayout lemp,lhr,ladmin;

    Spinner profile, project, message, mypay, payslips, reimbursementupload, attendance, ourupdates, myprojects, careers;
    TextView profile1,project1, message1, mypay1, payslips1, reimbursementupload1, attendance1, ourupdates1, myprojects1, careers1;
    Button save;

    Spinner profileh, projecth, messageh, mypayh, payslipsh, reimbursementuploadh, attendanceh, ourupdatesh, myprojectsh;
    Spinner newempformh,empdirectoryh,projectdetailsh,empattendanceh,reimbursementfetchh,documentsuploadh,payslipsfetchh,careersh,rewardsh,interviewsh;
    TextView profileh1,projecth1, messageh1, mypayh1, payslipsh1, reimbursementuploadh1, attendanceh1, ourupdatesh1, myprojectsh1;
    TextView newempformh1,empdirectoryh1,projectdetailsh1,empattendanceh1,reimbursementfetchh1,documentsuploadh1,payslipsfetchh1,careersh1,rewardsh1,interviewsh1;
    Button savehr;

    Spinner profilea, projecta, messagea, mypaya, payslipsa, reimbursementuploada, attendancea, ourupdatesa, myprojectsa;
    Spinner newempforma,empdirectorya,projectdetailsa,empattendancea,reimbursementfetcha,documentsuploada,payslipsfetcha,careersa,rewardsa,interviewsa;
    Spinner documentsfetcha,payrolla,cccameraa,permissionsa;
    TextView profilea1,projecta1, messagea1, mypaya1, payslipsa1, reimbursementuploada1, attendancea1, ourupdatesa1, myprojectsa1;
    TextView newempforma1,empdirectorya1,projectdetailsa1,empattendancea1,reimbursementfetcha1,documentsuploada1,payslipsfetcha1,careersa1,rewardsa1,interviewsa1;
    TextView documentsfetcha1,payrolla1,cccameraa1,permissionsa1;
    Button saveadmin;

    User user;
    FirebaseDatabase fdatabse;
    DatabaseReference dref;
    ImageView iv;
    TextView dashboard,list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_permissions);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userId = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);
        empid = (TextView)findViewById(R.id.empid);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        list = (TextView)findViewById(R.id.list);

        fdatabse = FirebaseDatabase.getInstance();
        dref = fdatabse.getReference("Preferences");
        user = new User();

        save = (Button) findViewById(R.id.save);
        savehr = (Button) findViewById(R.id.savehr);
        saveadmin = (Button) findViewById(R.id.saveadmin);

        lemp = findViewById(R.id.lemp);
        lhr = findViewById(R.id.lhr);
        ladmin = findViewById(R.id.ladmin);

        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");
        UserType = getIntent().getStringExtra("usertype");

        userId.setText(UserId);
        empid.setText(Empid);
        usertype.setText(UserType);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromleft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        fromright = AnimationUtils.loadAnimation(this,R.anim.fromright);

        hsv1.setAnimation(fromtop);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Permissions.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Permissions.this,Preferences.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Preferences - Permissions");
        }

        profile = (Spinner) findViewById(R.id.profile);
        project = (Spinner) findViewById(R.id.project);
        message = (Spinner) findViewById(R.id.message);
        mypay = (Spinner) findViewById(R.id.mypay);
        payslips = (Spinner) findViewById(R.id.payslips);
        reimbursementupload = (Spinner) findViewById(R.id.reimbursementupload);
        attendance = (Spinner) findViewById(R.id.attendance);
        ourupdates = (Spinner) findViewById(R.id.ourupdates);
        myprojects = (Spinner) findViewById(R.id.myprojects);
        careers = (Spinner) findViewById(R.id.careers);

        profile1 = (TextView) findViewById(R.id.profile1);
        project1 = (TextView) findViewById(R.id.project1);
        message1 = (TextView) findViewById(R.id.message1);
        mypay1 = (TextView) findViewById(R.id.mypay1);
        payslips1 = (TextView) findViewById(R.id.payslips1);
        reimbursementupload1 = (TextView) findViewById(R.id.reimbursementupload1);
        attendance1 = (TextView) findViewById(R.id.attendance1);
        ourupdates1 = (TextView) findViewById(R.id.ourupdates1);
        myprojects1 = (TextView) findViewById(R.id.myprojects1);
        careers1 = (TextView) findViewById(R.id.careers1);

        profileh = (Spinner) findViewById(R.id.profileh);
        projecth = (Spinner) findViewById(R.id.projecth);
        messageh = (Spinner) findViewById(R.id.messageh);
        mypayh = (Spinner) findViewById(R.id.mypayh);
        payslipsh = (Spinner) findViewById(R.id.payslipsh);
        reimbursementuploadh = (Spinner) findViewById(R.id.reimbursementuploadh);
        attendanceh = (Spinner) findViewById(R.id.attendanceh);
        ourupdatesh = (Spinner) findViewById(R.id.ourupdatesh);
        myprojectsh = (Spinner) findViewById(R.id.myprojectsh);
        newempformh = (Spinner) findViewById(R.id.newempformh);
        empdirectoryh = (Spinner) findViewById(R.id.empdirectoryh);
        projectdetailsh = (Spinner) findViewById(R.id.projectdetailsh);
        empattendanceh = (Spinner) findViewById(R.id.empattendanceh);
        reimbursementfetchh = (Spinner) findViewById(R.id.reimbursementfetchh);
        documentsuploadh = (Spinner) findViewById(R.id.documentsuploadh);
        payslipsfetchh = (Spinner) findViewById(R.id.payslipsfetchh);
        careersh = (Spinner) findViewById(R.id.careersh);
        rewardsh = (Spinner) findViewById(R.id.rewardsh);
        interviewsh = (Spinner) findViewById(R.id.interviewsh);

        profileh1 = (TextView) findViewById(R.id.profileh1);
        projecth1 = (TextView) findViewById(R.id.projecth1);
        messageh1 = (TextView) findViewById(R.id.messageh1);
        mypayh1 = (TextView) findViewById(R.id.mypayh1);
        payslipsh1 = (TextView) findViewById(R.id.payslipsh1);
        reimbursementuploadh1 = (TextView) findViewById(R.id.reimbursementuploadh1);
        attendanceh1 = (TextView) findViewById(R.id.attendanceh1);
        ourupdatesh1 = (TextView) findViewById(R.id.ourupdatesh1);
        myprojectsh1 = (TextView) findViewById(R.id.myprojectsh1);
        newempformh1 = (TextView) findViewById(R.id.newempformh1);
        empdirectoryh1 = (TextView) findViewById(R.id.empdirectoryh1);
        projectdetailsh1 = (TextView) findViewById(R.id.projectdetailsh1);
        empattendanceh1 = (TextView) findViewById(R.id.empattendanceh1);
        reimbursementfetchh1 = (TextView) findViewById(R.id.reimbursementfetchh1);
        documentsuploadh1 = (TextView) findViewById(R.id.documentsuploadh1);
        payslipsfetchh1 = (TextView) findViewById(R.id.payslipsfetchh1);
        careersh1 = (TextView) findViewById(R.id.careersh1);
        rewardsh1 = (TextView) findViewById(R.id.rewardsh1);
        interviewsh1 = (TextView) findViewById(R.id.interviewsh1);

        profilea = (Spinner) findViewById(R.id.profilea);
        projecta = (Spinner) findViewById(R.id.projecta);
        messagea = (Spinner) findViewById(R.id.messagea);
        mypaya = (Spinner) findViewById(R.id.mypaya);
        payslipsa = (Spinner) findViewById(R.id.payslipsa);
        reimbursementuploada = (Spinner) findViewById(R.id.reimbursementuploada);
        attendancea = (Spinner) findViewById(R.id.attendancea);
        ourupdatesa = (Spinner) findViewById(R.id.ourupdatesa);
        myprojectsa = (Spinner) findViewById(R.id.myprojectsa);
        newempforma = (Spinner) findViewById(R.id.newempforma);
        empdirectorya = (Spinner) findViewById(R.id.empdirectorya);
        projectdetailsa = (Spinner) findViewById(R.id.projectdetailsa);
        empattendancea = (Spinner) findViewById(R.id.empattendancea);
        reimbursementfetcha = (Spinner) findViewById(R.id.reimbursementfetcha);
        documentsuploada = (Spinner) findViewById(R.id.documentsuploada);
        payslipsfetcha = (Spinner) findViewById(R.id.payslipsfetcha);
        careersa = (Spinner) findViewById(R.id.careersa);
        rewardsa = (Spinner) findViewById(R.id.rewardsa);
        interviewsa = (Spinner) findViewById(R.id.interviewsa);
        documentsfetcha = (Spinner) findViewById(R.id.documentsfetcha);
        payrolla = (Spinner) findViewById(R.id.payrolla);
        cccameraa = (Spinner) findViewById(R.id.cccameraa);
        permissionsa = (Spinner) findViewById(R.id.permissionsa);

        profilea1 = (TextView) findViewById(R.id.profilea1);
        projecta1 = (TextView) findViewById(R.id.projecta1);
        messagea1 = (TextView) findViewById(R.id.messagea1);
        mypaya1 = (TextView) findViewById(R.id.mypaya1);
        payslipsa1 = (TextView) findViewById(R.id.payslipsa1);
        reimbursementuploada1 = (TextView) findViewById(R.id.reimbursementuploada1);
        attendancea1 = (TextView) findViewById(R.id.attendancea1);
        ourupdatesa1 = (TextView) findViewById(R.id.ourupdatesa1);
        myprojectsa1 = (TextView) findViewById(R.id.myprojectsa1);
        newempforma1 = (TextView) findViewById(R.id.newempforma1);
        empdirectorya1 = (TextView) findViewById(R.id.empdirectorya1);
        projectdetailsa1 = (TextView) findViewById(R.id.projectdetailsa1);
        empattendancea1 = (TextView) findViewById(R.id.empattendancea1);
        reimbursementfetcha1 = (TextView) findViewById(R.id.reimbursementfetcha1);
        documentsuploada1 = (TextView) findViewById(R.id.documentsuploada1);
        payslipsfetcha1 = (TextView) findViewById(R.id.payslipsfetcha1);
        careersa1 = (TextView) findViewById(R.id.careersa1);
        rewardsa1 = (TextView) findViewById(R.id.rewardsa1);
        interviewsa1 = (TextView) findViewById(R.id.interviewsa1);
        documentsfetcha1 = (TextView) findViewById(R.id.documentsfetcha1);
        payrolla1 = (TextView) findViewById(R.id.payrolla1);
        cccameraa1 = (TextView) findViewById(R.id.cccameraa1);
        permissionsa1 = (TextView) findViewById(R.id.permissionsa1);

        ////////Employee
        if(Empid.startsWith("E"))
        {
            lemp.setVisibility(View.VISIBLE);
            lhr.setVisibility(View.GONE);
            ladmin.setVisibility(View.GONE);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (profile1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (project1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (message1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (mypay1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (payslips1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (reimbursementupload1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (attendance1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (ourupdates1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (myprojects1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else if (careers1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }
                    else {
                        addUpdate();
                    }
                } });

            profile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    profile.setVisibility(View.GONE);
                    profile1.setVisibility(View.VISIBLE);
                    profile1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            profile1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profile1.setVisibility(View.GONE);
                    profile.setVisibility(View.VISIBLE);
                }
            });

            project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    project.setVisibility(View.GONE);
                    project1.setVisibility(View.VISIBLE);
                    project1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            project1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    project1.setVisibility(View.GONE);
                    project.setVisibility(View.VISIBLE);
                }
            });

            message.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    message.setVisibility(View.GONE);
                    message1.setVisibility(View.VISIBLE);
                    message1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            message1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message1.setVisibility(View.GONE);
                    message.setVisibility(View.VISIBLE);
                }
            });

            mypay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    mypay.setVisibility(View.GONE);
                    mypay1.setVisibility(View.VISIBLE);
                    mypay1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            mypay1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mypay1.setVisibility(View.GONE);
                    mypay.setVisibility(View.VISIBLE);
                }
            });

            payslips.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    payslips.setVisibility(View.GONE);
                    payslips1.setVisibility(View.VISIBLE);
                    payslips1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            payslips1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payslips1.setVisibility(View.GONE);
                    payslips.setVisibility(View.VISIBLE);
                }
            });

            reimbursementupload.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    reimbursementupload.setVisibility(View.GONE);
                    reimbursementupload1.setVisibility(View.VISIBLE);
                    reimbursementupload1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            reimbursementupload1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reimbursementupload1.setVisibility(View.GONE);
                    reimbursementupload.setVisibility(View.VISIBLE);
                }
            });

            attendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    attendance.setVisibility(View.GONE);
                    attendance1.setVisibility(View.VISIBLE);
                    attendance1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            attendance1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attendance1.setVisibility(View.GONE);
                    attendance.setVisibility(View.VISIBLE);
                }
            });

            ourupdates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    ourupdates.setVisibility(View.GONE);
                    ourupdates1.setVisibility(View.VISIBLE);
                    ourupdates1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            ourupdates1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ourupdates1.setVisibility(View.GONE);
                    ourupdates.setVisibility(View.VISIBLE);
                }
            });

            myprojects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    myprojects.setVisibility(View.GONE);
                    myprojects1.setVisibility(View.VISIBLE);
                    myprojects1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            myprojects1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myprojects1.setVisibility(View.GONE);
                    myprojects.setVisibility(View.VISIBLE);
                }
            });

            careers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    // Get the spinner selected item text
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    // Display the selected item into the
                    careers.setVisibility(View.GONE);
                    careers1.setVisibility(View.VISIBLE);
                    careers1.setText(selectedItemText);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            careers1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    careers1.setVisibility(View.GONE);
                    careers.setVisibility(View.VISIBLE);
                }
            });

            dref = FirebaseDatabase.getInstance().getReference("Preferences");
            Query lastQuery = dref.child(Empid);
            lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null) {
                        profile1.setText(profile1.getText().toString().equals("") ? "Choose" : "Choose");
                        project1.setText(project1.getText().toString().equals("") ? "Choose" : "Choose");
                        message1.setText(message1.getText().toString().equals("") ? "Choose" : "Choose");
                        mypay1.setText(mypay1.getText().toString().equals("") ? "Choose" : "Choose");
                        payslips1.setText(payslips1.getText().toString().equals("") ? "Choose" : "Choose");
                        reimbursementupload1.setText(reimbursementupload1.getText().toString().equals("") ? "Choose" : "Choose");
                        attendance1.setText(attendance1.getText().toString().equals("") ? "Choose" : "Choose");
                        ourupdates1.setText(ourupdates1.getText().toString().equals("") ? "Choose" : "Choose");
                        myprojects1.setText(myprojects1.getText().toString().equals("") ? "Choose" : "Choose");
                        careers1.setText(careers1.getText().toString().equals("") ? "Choose" : "Choose");
                    } else {
                        String Profile1 = dataSnapshot.child("profile").getValue().toString();
                        String Project1 = dataSnapshot.child("project").getValue().toString();
                        String Message1 = dataSnapshot.child("message").getValue().toString();
                        String Mypay1 = dataSnapshot.child("mypay").getValue().toString();
                        String Payslips1 = dataSnapshot.child("payslips").getValue().toString();
                        String Reimbursementupload1 = dataSnapshot.child("reimbursementupload").getValue().toString();
                        String Attendance1 = dataSnapshot.child("attendance").getValue().toString();
                        String Ourupdates1 = dataSnapshot.child("ourupdates").getValue().toString();
                        String Myprojects1 = dataSnapshot.child("myprojects").getValue().toString();
                        String Careers1 = dataSnapshot.child("careers").getValue().toString();

                        profile1.setText(Profile1);
                        project1.setText(Project1);
                        message1.setText(Message1);
                        mypay1.setText(Mypay1);
                        payslips1.setText(Payslips1);
                        reimbursementupload1.setText(Reimbursementupload1);
                        attendance1.setText(Attendance1);
                        ourupdates1.setText(Ourupdates1);
                        myprojects1.setText(Myprojects1);
                        careers1.setText(Careers1);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

///HR
        if(Empid.startsWith("H"))
        {
            lemp.setVisibility(View.GONE);
            lhr.setVisibility(View.VISIBLE);
            ladmin.setVisibility(View.GONE);

            savehr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (profileh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (projecth1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (messageh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (mypayh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (payslipsh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (reimbursementuploadh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (attendanceh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (ourupdatesh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (myprojectsh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (newempformh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (empdirectoryh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (projectdetailsh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (empattendanceh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (reimbursementfetchh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (documentsuploadh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (payslipsfetchh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (rewardsh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (careersh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (interviewsh1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }else {
                        addHRUpdate();
                    }} });

            profileh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    profileh.setVisibility(View.GONE);
                    profileh1.setVisibility(View.VISIBLE);
                    profileh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            profileh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profileh1.setVisibility(View.GONE);
                    profileh.setVisibility(View.VISIBLE); }});

            projecth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    projecth.setVisibility(View.GONE);
                    projecth1.setVisibility(View.VISIBLE);
                    projecth1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            projecth1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    projecth1.setVisibility(View.GONE);
                    projecth.setVisibility(View.VISIBLE); }});

            messageh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    messageh.setVisibility(View.GONE);
                    messageh1.setVisibility(View.VISIBLE);
                    messageh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            messageh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messageh1.setVisibility(View.GONE);
                    messageh.setVisibility(View.VISIBLE); }});

            mypayh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    mypayh.setVisibility(View.GONE);
                    mypayh1.setVisibility(View.VISIBLE);
                    mypayh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            mypayh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mypayh1.setVisibility(View.GONE);
                    mypayh.setVisibility(View.VISIBLE); }});

            payslipsh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    payslipsh.setVisibility(View.GONE);
                    payslipsh1.setVisibility(View.VISIBLE);
                    payslipsh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            payslipsh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payslipsh1.setVisibility(View.GONE);
                    payslipsh.setVisibility(View.VISIBLE); }});

            reimbursementuploadh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    reimbursementuploadh.setVisibility(View.GONE);
                    reimbursementuploadh1.setVisibility(View.VISIBLE);
                    reimbursementuploadh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            reimbursementuploadh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reimbursementuploadh1.setVisibility(View.GONE);
                    reimbursementuploadh.setVisibility(View.VISIBLE); }});

            attendanceh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    attendanceh.setVisibility(View.GONE);
                    attendanceh1.setVisibility(View.VISIBLE);
                    attendanceh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            attendanceh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attendanceh1.setVisibility(View.GONE);
                    attendanceh.setVisibility(View.VISIBLE); }});

            ourupdatesh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    ourupdatesh.setVisibility(View.GONE);
                    ourupdatesh1.setVisibility(View.VISIBLE);
                    ourupdatesh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            ourupdatesh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ourupdatesh1.setVisibility(View.GONE);
                    ourupdatesh.setVisibility(View.VISIBLE); }});

            myprojectsh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    myprojectsh.setVisibility(View.GONE);
                    myprojectsh1.setVisibility(View.VISIBLE);
                    myprojectsh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            myprojectsh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myprojectsh1.setVisibility(View.GONE);
                    myprojectsh.setVisibility(View.VISIBLE); }});

            newempformh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    newempformh.setVisibility(View.GONE);
                    newempformh1.setVisibility(View.VISIBLE);
                    newempformh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            newempformh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newempformh1.setVisibility(View.GONE);
                    newempformh.setVisibility(View.VISIBLE); }});

            empdirectoryh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    empdirectoryh.setVisibility(View.GONE);
                    empdirectoryh1.setVisibility(View.VISIBLE);
                    empdirectoryh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            empdirectoryh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empdirectoryh1.setVisibility(View.GONE);
                    empdirectoryh.setVisibility(View.VISIBLE); }});

            projectdetailsh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    projectdetailsh.setVisibility(View.GONE);
                    projectdetailsh1.setVisibility(View.VISIBLE);
                    projectdetailsh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            projectdetailsh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    projectdetailsh1.setVisibility(View.GONE);
                    projectdetailsh.setVisibility(View.VISIBLE); }});

            empattendanceh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    empattendanceh.setVisibility(View.GONE);
                    empattendanceh1.setVisibility(View.VISIBLE);
                    empattendanceh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            empattendanceh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empattendanceh1.setVisibility(View.GONE);
                    empattendanceh.setVisibility(View.VISIBLE); }});

            reimbursementfetchh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    reimbursementfetchh.setVisibility(View.GONE);
                    reimbursementfetchh1.setVisibility(View.VISIBLE);
                    reimbursementfetchh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            reimbursementfetchh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reimbursementfetchh1.setVisibility(View.GONE);
                    reimbursementfetchh.setVisibility(View.VISIBLE); }});

            documentsuploadh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    documentsuploadh.setVisibility(View.GONE);
                    documentsuploadh1.setVisibility(View.VISIBLE);
                    documentsuploadh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            documentsuploadh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    documentsuploadh1.setVisibility(View.GONE);
                    documentsuploadh.setVisibility(View.VISIBLE); }});

            payslipsfetchh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    payslipsfetchh.setVisibility(View.GONE);
                    payslipsfetchh1.setVisibility(View.VISIBLE);
                    payslipsfetchh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            payslipsfetchh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payslipsfetchh1.setVisibility(View.GONE);
                    payslipsfetchh.setVisibility(View.VISIBLE); }});

            careersh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    careersh.setVisibility(View.GONE);
                    careersh1.setVisibility(View.VISIBLE);
                    careersh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            careersh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    careersh1.setVisibility(View.GONE);
                    careersh.setVisibility(View.VISIBLE); }});

            rewardsh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    rewardsh.setVisibility(View.GONE);
                    rewardsh1.setVisibility(View.VISIBLE);
                    rewardsh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            rewardsh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rewardsh1.setVisibility(View.GONE);
                    rewardsh.setVisibility(View.VISIBLE); }});

            interviewsh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    interviewsh.setVisibility(View.GONE);
                    interviewsh1.setVisibility(View.VISIBLE);
                    interviewsh1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            interviewsh1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interviewsh1.setVisibility(View.GONE);
                    interviewsh.setVisibility(View.VISIBLE); }});

            dref = FirebaseDatabase.getInstance().getReference("Preferences");
            Query lastQuery = dref.child(Empid);
            lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null) {
                        profileh1.setText(profileh1.getText().toString().equals("") ? "Choose" : "Choose");
                        projecth1.setText(projecth1.getText().toString().equals("") ? "Choose" : "Choose");
                        messageh1.setText(messageh1.getText().toString().equals("") ? "Choose" : "Choose");
                        mypayh1.setText(mypayh1.getText().toString().equals("") ? "Choose" : "Choose");
                        payslipsh1.setText(payslipsh1.getText().toString().equals("") ? "Choose" : "Choose");
                        reimbursementuploadh1.setText(reimbursementuploadh1.getText().toString().equals("") ? "Choose" : "Choose");
                        attendanceh1.setText(attendanceh1.getText().toString().equals("") ? "Choose" : "Choose");
                        ourupdatesh1.setText(ourupdatesh1.getText().toString().equals("") ? "Choose" : "Choose");
                        myprojectsh1.setText(myprojectsh1.getText().toString().equals("") ? "Choose" : "Choose");
                        newempformh1.setText(newempformh1.getText().toString().equals("") ? "Choose" : "Choose");
                        empdirectoryh1.setText(empdirectoryh1.getText().toString().equals("") ? "Choose" : "Choose");
                        projectdetailsh1.setText(projectdetailsh1.getText().toString().equals("") ? "Choose" : "Choose");
                        empattendanceh1.setText(empattendanceh1.getText().toString().equals("") ? "Choose" : "Choose");
                        reimbursementfetchh1.setText(reimbursementfetchh1.getText().toString().equals("") ? "Choose" : "Choose");
                        documentsuploadh1.setText(documentsuploadh1.getText().toString().equals("") ? "Choose" : "Choose");
                        payslipsfetchh1.setText(payslipsfetchh1.getText().toString().equals("") ? "Choose" : "Choose");
                        careersh1.setText(careersh1.getText().toString().equals("") ? "Choose" : "Choose");
                        rewardsh1.setText(rewardsh1.getText().toString().equals("") ? "Choose" : "Choose");
                        interviewsh1.setText(interviewsh1.getText().toString().equals("") ? "Choose" : "Choose");
                    } else{
                        String Profileh1 = dataSnapshot.child("profile").getValue().toString();
                        String Projecth1 = dataSnapshot.child("project").getValue().toString();
                        String Messageh1 = dataSnapshot.child("message").getValue().toString();
                        String Mypayh1 = dataSnapshot.child("mypay").getValue().toString();
                        String Payslipsh1 = dataSnapshot.child("payslips").getValue().toString();
                        String Reimbursementuploadh1 = dataSnapshot.child("reimbursementupload").getValue().toString();
                        String Attendanceh1 = dataSnapshot.child("attendance").getValue().toString();
                        String Ourupdatesh1 = dataSnapshot.child("ourupdates").getValue().toString();
                        String Myprojectsh1 = dataSnapshot.child("myprojects").getValue().toString();
                        String Newempformh1 = dataSnapshot.child("newempform").getValue().toString();
                        String Empdirectoryh1 = dataSnapshot.child("empdirectory").getValue().toString();
                        String Projectdetailsh1 = dataSnapshot.child("projectdetails").getValue().toString();
                        String Empattendanceh1 = dataSnapshot.child("empattendance").getValue().toString();
                        String Reimbursementhfetchh1 = dataSnapshot.child("reimbursementfetch").getValue().toString();
                        String Documentsuploadh1 = dataSnapshot.child("documentsupload").getValue().toString();
                        String Payslipsfetchh1 = dataSnapshot.child("payslipsfetch").getValue().toString();
                        String Careersh1 = dataSnapshot.child("careers").getValue().toString();
                        String Rewardsh1 = dataSnapshot.child("rewards").getValue().toString();
                        String Interviewsh1 = dataSnapshot.child("interviews").getValue().toString();

                        profileh1.setText(Profileh1);
                        projecth1.setText(Projecth1);
                        messageh1.setText(Messageh1);
                        mypayh1.setText(Mypayh1);
                        payslipsh1.setText(Payslipsh1);
                        reimbursementuploadh1.setText(Reimbursementuploadh1);
                        attendanceh1.setText(Attendanceh1);
                        ourupdatesh1.setText(Ourupdatesh1);
                        myprojectsh1.setText(Myprojectsh1);
                        newempformh1.setText(Newempformh1);
                        empdirectoryh1.setText(Empdirectoryh1);
                        projectdetailsh1.setText(Projectdetailsh1);
                        empattendanceh1.setText(Empattendanceh1);
                        reimbursementfetchh1.setText(Reimbursementhfetchh1);
                        documentsuploadh1.setText(Documentsuploadh1);
                        payslipsfetchh1.setText(Payslipsfetchh1);
                        careersh1.setText(Careersh1);
                        rewardsh1.setText(Rewardsh1);
                        interviewsh1.setText(Interviewsh1);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }


/////////Admin
        if(Empid.startsWith("A")) {
            lemp.setVisibility(View.GONE);
            lhr.setVisibility(View.GONE);
            ladmin.setVisibility(View.VISIBLE);

            saveadmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (profilea1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (projecta1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (messagea1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (mypaya1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (payslipsa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (reimbursementuploada1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (attendancea1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (ourupdatesa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (empdirectorya1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (projectdetailsa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (empattendancea1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (payrolla1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (documentsfetcha1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (permissionsa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (cccameraa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (payslipsfetcha1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (newempforma1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (reimbursementfetcha1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (documentsuploada1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (myprojectsa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (rewardsa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (careersa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    } else if (interviewsa1.getText().toString().trim().equals("CHANGE")) {
                        Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
                    }else {
                        addadminUpdate();
                    }
                }
            });

            profilea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    profilea.setVisibility(View.GONE);
                    profilea1.setVisibility(View.VISIBLE);
                    profilea1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            profilea1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profilea1.setVisibility(View.GONE);
                    profilea.setVisibility(View.VISIBLE); }});

            projecta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    projecta.setVisibility(View.GONE);
                    projecta1.setVisibility(View.VISIBLE);
                    projecta1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            projecta1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    projecta1.setVisibility(View.GONE);
                    projecta.setVisibility(View.VISIBLE); }});

            messagea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    messagea.setVisibility(View.GONE);
                    messagea1.setVisibility(View.VISIBLE);
                    messagea1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            messagea1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messagea1.setVisibility(View.GONE);
                    messagea.setVisibility(View.VISIBLE); }});

            mypaya.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    mypaya.setVisibility(View.GONE);
                    mypaya1.setVisibility(View.VISIBLE);
                    mypaya1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            mypaya1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mypaya1.setVisibility(View.GONE);
                    mypaya.setVisibility(View.VISIBLE); }});

            payslipsa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    payslipsa.setVisibility(View.GONE);
                    payslipsa1.setVisibility(View.VISIBLE);
                    payslipsa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            payslipsa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payslipsa1.setVisibility(View.GONE);
                    payslipsa.setVisibility(View.VISIBLE); }});

            reimbursementuploada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    reimbursementuploada.setVisibility(View.GONE);
                    reimbursementuploada1.setVisibility(View.VISIBLE);
                    reimbursementuploada1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            reimbursementuploada1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reimbursementuploada1.setVisibility(View.GONE);
                    reimbursementuploada.setVisibility(View.VISIBLE); }});

            attendancea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    attendancea.setVisibility(View.GONE);
                    attendancea1.setVisibility(View.VISIBLE);
                    attendancea1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            attendancea1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attendancea1.setVisibility(View.GONE);
                    attendancea.setVisibility(View.VISIBLE); }});

            ourupdatesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    ourupdatesa.setVisibility(View.GONE);
                    ourupdatesa1.setVisibility(View.VISIBLE);
                    ourupdatesa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            ourupdatesa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ourupdatesa1.setVisibility(View.GONE);
                    ourupdatesa.setVisibility(View.VISIBLE); }});

            myprojectsa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    myprojectsa.setVisibility(View.GONE);
                    myprojectsa1.setVisibility(View.VISIBLE);
                    myprojectsa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            myprojectsa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myprojectsa1.setVisibility(View.GONE);
                    myprojectsa.setVisibility(View.VISIBLE); }});

            newempforma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    newempforma.setVisibility(View.GONE);
                    newempforma1.setVisibility(View.VISIBLE);
                    newempforma1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            newempforma1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newempforma1.setVisibility(View.GONE);
                    newempforma.setVisibility(View.VISIBLE); }});

            empdirectorya.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    empdirectorya.setVisibility(View.GONE);
                    empdirectorya1.setVisibility(View.VISIBLE);
                    empdirectorya1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            empdirectorya1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empdirectorya1.setVisibility(View.GONE);
                    empdirectorya.setVisibility(View.VISIBLE); }});

            projectdetailsa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    projectdetailsa.setVisibility(View.GONE);
                    projectdetailsa1.setVisibility(View.VISIBLE);
                    projectdetailsa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            projectdetailsa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    projectdetailsa1.setVisibility(View.GONE);
                    projectdetailsa.setVisibility(View.VISIBLE); }});

            empattendancea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    empattendancea.setVisibility(View.GONE);
                    empattendancea1.setVisibility(View.VISIBLE);
                    empattendancea1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            empattendancea1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empattendancea1.setVisibility(View.GONE);
                    empattendancea.setVisibility(View.VISIBLE); }});

            reimbursementfetcha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    reimbursementfetcha.setVisibility(View.GONE);
                    reimbursementfetcha1.setVisibility(View.VISIBLE);
                    reimbursementfetcha1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            reimbursementfetcha1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reimbursementfetcha1.setVisibility(View.GONE);
                    reimbursementfetcha.setVisibility(View.VISIBLE); }});

            documentsuploada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    documentsuploada.setVisibility(View.GONE);
                    documentsuploada1.setVisibility(View.VISIBLE);
                    documentsuploada1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            documentsuploada1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    documentsuploada1.setVisibility(View.GONE);
                    documentsuploada.setVisibility(View.VISIBLE); }});

            payslipsfetcha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    payslipsfetcha.setVisibility(View.GONE);
                    payslipsfetcha1.setVisibility(View.VISIBLE);
                    payslipsfetcha1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            payslipsfetcha1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payslipsfetcha1.setVisibility(View.GONE);
                    payslipsfetcha.setVisibility(View.VISIBLE); }});

            careersa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    careersa.setVisibility(View.GONE);
                    careersa1.setVisibility(View.VISIBLE);
                    careersa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            careersa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    careersa1.setVisibility(View.GONE);
                    careersa.setVisibility(View.VISIBLE); }});

            rewardsa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    rewardsa.setVisibility(View.GONE);
                    rewardsa1.setVisibility(View.VISIBLE);
                    rewardsa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            rewardsa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rewardsa1.setVisibility(View.GONE);
                    rewardsa.setVisibility(View.VISIBLE); }});

            interviewsa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    interviewsa.setVisibility(View.GONE);
                    interviewsa1.setVisibility(View.VISIBLE);
                    interviewsa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            interviewsa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interviewsa1.setVisibility(View.GONE);
                    interviewsa.setVisibility(View.VISIBLE); }});

            documentsfetcha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    documentsfetcha.setVisibility(View.GONE);
                    documentsfetcha1.setVisibility(View.VISIBLE);
                    documentsfetcha1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            documentsfetcha1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    documentsfetcha1.setVisibility(View.GONE);
                    documentsfetcha.setVisibility(View.VISIBLE); }});

            payrolla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    payrolla.setVisibility(View.GONE);
                    payrolla1.setVisibility(View.VISIBLE);
                    payrolla1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            payrolla1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    payrolla1.setVisibility(View.GONE);
                    payrolla.setVisibility(View.VISIBLE); }});

            cccameraa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    cccameraa.setVisibility(View.GONE);
                    cccameraa1.setVisibility(View.VISIBLE);
                    cccameraa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            cccameraa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cccameraa1.setVisibility(View.GONE);
                    cccameraa.setVisibility(View.VISIBLE); }});

            permissionsa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItemText = (String) adapterView.getItemAtPosition(i);
                    permissionsa.setVisibility(View.GONE);
                    permissionsa1.setVisibility(View.VISIBLE);
                    permissionsa1.setText(selectedItemText); }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {}});
            permissionsa1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    permissionsa1.setVisibility(View.GONE);
                    permissionsa.setVisibility(View.VISIBLE); }});


            dref = FirebaseDatabase.getInstance().getReference("Preferences");
            Query lastQuery = dref.child(Empid);
            lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null) {

                        profilea1.setText(profilea1.getText().toString().equals("") ? "Choose" : "Choose");
                        projecta1.setText(projecta1.getText().toString().equals("") ? "Choose" : "Choose");
                        messagea1.setText(messagea1.getText().toString().equals("") ? "Choose" : "Choose");
                        mypaya1.setText(mypaya1.getText().toString().equals("") ? "Choose" : "Choose");
                        payslipsa1.setText(payslipsa1.getText().toString().equals("") ? "Choose" : "Choose");
                        reimbursementuploada1.setText(reimbursementuploada1.getText().toString().equals("") ? "Choose" : "Choose");
                        attendancea1.setText(attendancea1.getText().toString().equals("") ? "Choose" : "Choose");
                        ourupdatesa1.setText(ourupdatesa1.getText().toString().equals("") ? "Choose" : "Choose");
                        myprojectsa1.setText(myprojectsa1.getText().toString().equals("") ? "Choose" : "Choose");
                        newempforma1.setText(newempforma1.getText().toString().equals("") ? "Choose" : "Choose");
                        empdirectorya1.setText(empdirectorya1.getText().toString().equals("") ? "Choose" : "Choose");
                        projectdetailsa1.setText(projectdetailsa1.getText().toString().equals("") ? "Choose" : "Choose");
                        empattendancea1.setText(empattendancea1.getText().toString().equals("") ? "Choose" : "Choose");
                        reimbursementfetcha1.setText(reimbursementfetcha1.getText().toString().equals("") ? "Choose" : "Choose");
                        documentsuploada1.setText(documentsuploada1.getText().toString().equals("") ? "Choose" : "Choose");
                        payslipsfetcha1.setText(payslipsfetcha1.getText().toString().equals("") ? "Choose" : "Choose");
                        careersa1.setText(careersa1.getText().toString().equals("") ? "Choose" : "Choose");
                        rewardsa1.setText(rewardsa1.getText().toString().equals("") ? "Choose" : "Choose");
                        interviewsa1.setText(interviewsa1.getText().toString().equals("") ? "Choose" : "Choose");
                        documentsfetcha1.setText(documentsfetcha1.getText().toString().equals("") ? "Choose" : "Choose");
                        payrolla1.setText(payrolla1.getText().toString().equals("") ? "Choose" : "Choose");
                        cccameraa1.setText(cccameraa1.getText().toString().equals("") ? "Choose" : "Choose");
                        permissionsa1.setText(permissionsa1.getText().toString().equals("") ? "Choose" : "Choose");
                    }else{
                        String Profilea1 = dataSnapshot.child("profile").getValue().toString();
                        String Projecta1 = dataSnapshot.child("project").getValue().toString();
                        String Messagea1 = dataSnapshot.child("message").getValue().toString();
                        String Mypaya1 = dataSnapshot.child("mypay").getValue().toString();
                        String Payslipsa1 = dataSnapshot.child("payslips").getValue().toString();
                        String Reimbursementuploada1 = dataSnapshot.child("reimbursementupload").getValue().toString();
                        String Attendancea1 = dataSnapshot.child("attendance").getValue().toString();
                        String Ourupdatesa1 = dataSnapshot.child("ourupdates").getValue().toString();
                        String Myprojectsa1 = dataSnapshot.child("myprojects").getValue().toString();
                        String Newempforma1 = dataSnapshot.child("newempform").getValue().toString();
                        String Empdirectorya1 = dataSnapshot.child("empdirectory").getValue().toString();
                        String Projectdetailsa1 = dataSnapshot.child("projectdetails").getValue().toString();
                        String Empattendancea1 = dataSnapshot.child("empattendance").getValue().toString();
                        String Reimbursementhfetcha1 = dataSnapshot.child("reimbursementfetch").getValue().toString();
                        String Documentsuploada1 = dataSnapshot.child("documentsupload").getValue().toString();
                        String Payslipsfetcha1 = dataSnapshot.child("payslipsfetch").getValue().toString();
                        String Careersa1 = dataSnapshot.child("careers").getValue().toString();
                        String Rewardsa1 = dataSnapshot.child("rewards").getValue().toString();
                        String Interviewsa1 = dataSnapshot.child("interviews").getValue().toString();
                        String Documentsfetcha1 = dataSnapshot.child("documentsfetch").getValue().toString();
                        String Payrolla1 = dataSnapshot.child("payroll").getValue().toString();
                        String Cccameraa1 = dataSnapshot.child("cccamera").getValue().toString();
                        String Permissionsa1 = dataSnapshot.child("permissions").getValue().toString();

                        profilea1.setText(Profilea1);
                        projecta1.setText(Projecta1);
                        messagea1.setText(Messagea1);
                        mypaya1.setText(Mypaya1);
                        payslipsa1.setText(Payslipsa1);
                        reimbursementuploada1.setText(Reimbursementuploada1);
                        attendancea1.setText(Attendancea1);
                        ourupdatesa1.setText(Ourupdatesa1);
                        myprojectsa1.setText(Myprojectsa1);
                        newempforma1.setText(Newempforma1);
                        empdirectorya1.setText(Empdirectorya1);
                        projectdetailsa1.setText(Projectdetailsa1);
                        empattendancea1.setText(Empattendancea1);
                        reimbursementfetcha1.setText(Reimbursementhfetcha1);
                        documentsuploada1.setText(Documentsuploada1);
                        payslipsfetcha1.setText(Payslipsfetcha1);
                        careersa1.setText(Careersa1);
                        rewardsa1.setText(Rewardsa1);
                        interviewsa1.setText(Interviewsa1);
                        documentsfetcha1.setText(Documentsfetcha1);
                        payrolla1.setText(Payrolla1);
                        cccameraa1.setText(Cccameraa1);
                        permissionsa1.setText(Permissionsa1);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }

    //Employee
    private void addUpdate(){
        String Empid = empid.getText().toString();
        String Profile = profile1.getText().toString();
        String Project = project1.getText().toString();
        String Message = message1.getText().toString();
        String Mypay = mypay1.getText().toString();
        String Payslips = payslips1.getText().toString();
        String Reimbursementupload = reimbursementupload1.getText().toString();
        String Attendance = attendance1.getText().toString();
        String Ourupdates = ourupdates1.getText().toString();
        String Myprojects = myprojects1.getText().toString();
        String Careers =careers1.getText().toString();

        if(!TextUtils.isEmpty(Empid)){
            String id = Empid;
            User user = new User(Empid,Profile,Project,Message,Mypay,Payslips,Reimbursementupload,Attendance,Ourupdates,Myprojects,Careers);
            dref.child(id).setValue(user);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
        }
    }


    //HR
    private void addHRUpdate(){
        String Empid = empid.getText().toString();
        String Profile = profileh1.getText().toString();
        String Project = projecth1.getText().toString();
        String Message = messageh1.getText().toString();
        String Mypay = mypayh1.getText().toString();
        String Payslips = payslipsh1.getText().toString();
        String Reimbursementupload = reimbursementuploadh1.getText().toString();
        String Attendance = attendanceh1.getText().toString();
        String Ourupdates = ourupdatesh1.getText().toString();
        String Myprojects = myprojectsh1.getText().toString();
        String Newempform = newempformh1.getText().toString();
        String Empdirectory = empdirectoryh1.getText().toString();
        String Projectdetails = projectdetailsh1.getText().toString();
        String Empattendance = empattendanceh1.getText().toString();
        String Reimbursementfetch = reimbursementfetchh1.getText().toString();
        String Documentsupload = documentsuploadh1.getText().toString();
        String Payslipsfetch = payslipsfetchh1.getText().toString();
        String Careers = careersh1.getText().toString();
        String Rewards = rewardsh1.getText().toString();
        String Interviews =interviewsh1.getText().toString();

        if(!TextUtils.isEmpty(Empid)){
            String id = Empid;
            User user = new User(Empid,Profile,Project,Message,Mypay,Payslips,Reimbursementupload,Attendance,Ourupdates,Myprojects,
                    Newempform,Empdirectory,Projectdetails,Empattendance,Reimbursementfetch,Documentsupload,Payslipsfetch,Careers,Rewards,Interviews);
            dref.child(id).setValue(user);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    //Admin
    private void addadminUpdate(){
        String Empid = empid.getText().toString();
        String Profile = profilea1.getText().toString();
        String Project = projecta1.getText().toString();
        String Message = messagea1.getText().toString();
        String Mypay = mypaya1.getText().toString();
        String Payslips = payslipsa1.getText().toString();
        String Reimbursementupload = reimbursementuploada1.getText().toString();
        String Attendance = attendancea1.getText().toString();
        String Ourupdates = ourupdatesa1.getText().toString();
        String Myprojects = myprojectsa1.getText().toString();
        String Newempform = newempforma1.getText().toString();
        String Empdirectory = empdirectorya1.getText().toString();
        String Projectdetails = projectdetailsa1.getText().toString();
        String Empattendance = empattendancea1.getText().toString();
        String Reimbursementfetch = reimbursementfetcha1.getText().toString();
        String Documentsupload = documentsuploada1.getText().toString();
        String Payslipsfetch = payslipsfetcha1.getText().toString();
        String Careers = careersa1.getText().toString();
        String Rewards = rewardsa1.getText().toString();
        String Interviews =interviewsa1.getText().toString();
        String Documentsfetch = documentsfetcha1.getText().toString();
        String Payroll = payrolla1.getText().toString();
        String Cccamera = cccameraa1.getText().toString();
        String Permissions =permissionsa1.getText().toString();

        if(!TextUtils.isEmpty(Empid)){
            String id = Empid;
            User user = new User(Empid,Profile,Project,Message,Mypay,Payslips,Reimbursementupload,Attendance,Ourupdates,Myprojects,
                    Newempform,Empdirectory,Projectdetails,Empattendance,Reimbursementfetch,Documentsupload,Payslipsfetch,Careers,Rewards,Interviews,
                    Documentsfetch,Payroll,Cccamera,Permissions);
            dref.child(id).setValue(user);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
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
            UserId = userId.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(Permissions.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
