package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anushak.firebasealtaoss.util1.Helper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PayrollDocuments extends AppCompatActivity {

    TextView payslips,reimbursement;
    android.widget.Spinner Spinner,Spinner1,type;
    TextView empId,name,userid,usertype,text;
    String empid,month,year,category,Name,Mname,Lname,UserId,UserType,Text;
    TextView mTextview;
    String eid,Qtype;
    String ty="Payslips";
    private StorageReference pdfref;

    android.support.v7.widget.Toolbar toolbar;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1,qtype,qtype1;
    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;
    LinearLayout pay3,pay4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_payroll_documents);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        payslips = (TextView) findViewById(R.id.paySlips);
        reimbursement = (TextView) findViewById(R.id.reimbursement);
        empId = (TextView) findViewById(R.id.tvEmpID);
        mTextview=(TextView)findViewById(R.id.textview);
        name = (TextView)findViewById(R.id.tvLastName);
        Spinner =(android.widget.Spinner)findViewById(R.id.monthspinner);
        Spinner1 =(android.widget.Spinner)findViewById(R.id.yearspinner);
        type =(android.widget.Spinner)findViewById(R.id.type);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        list = (TextView) findViewById(R.id.list);
        list1 = (TextView) findViewById(R.id.list1);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        pay3 = (LinearLayout)findViewById(R.id.pay3);
        pay4 = (LinearLayout)findViewById(R.id.pay4);
        qtype = (TextView)findViewById(R.id.qtype);
        qtype1 = (TextView)findViewById(R.id.qtype1);

        UserId = getIntent().getStringExtra("id");
        UserType = getIntent().getStringExtra("usertype");
        Name = getIntent().getStringExtra("name");
        Mname = getIntent().getStringExtra("middlename");
        Lname = getIntent().getStringExtra("lastname");
        eid=getIntent().getStringExtra("EmpId");

        userid.setText(UserId);
        usertype.setText(UserType);
        name.setText(Name+Mname+"."+Lname);
        empId.setText(eid);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

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
                Intent intent = new Intent(PayrollDocuments.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayrollDocuments.this,EmpPayroll.class);
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
                Intent intent = new Intent(PayrollDocuments.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayrollDocuments.this,EmpPayroll.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            //qtype.setText("  Re-Imbursement");
            qtype1.setText("  Re-Imbursement");
            Qtype = qtype1.getText().toString();
            text.setText("HR Dashboard - Employees List - "+Qtype);
            pay3.setVisibility(View.GONE);
            pay4.setVisibility(View.GONE);
        }
        else if(UserType.equals("admin"))
        {
            //qtype1.setText("  Payroll");
            qtype.setText("  Payroll");
            Qtype = qtype.getText().toString();
            text.setText("Admin Dashboard - Employees List - "+Qtype);
        }

        payslips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                month = Spinner.getSelectedItem().toString();
                year = Spinner1.getSelectedItem().toString();
                FirebaseStorage storage= FirebaseStorage.getInstance();
                StorageReference storageRef=storage.getReference();
                pdfref=storageRef.child("uploads/").child(ty).child(ty+empid+year+month+".pdf");
                downloadDataViaUrl();


            }
        });

        reimbursement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = type.getSelectedItem().toString();
                if (text.equals("Mobile")){
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();

                    Intent intent=new Intent(PayrollDocuments.this,Retrieve.class);
                    intent.putExtra("EMPID",empid);
                    intent.putExtra("TYPE",category);
                    intent.putExtra("usertype",UserType);
                    intent.putExtra("id",UserId);

                    startActivity(intent);

                }
                if (text.equals("General Expenses")){
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();

                    Intent intent=new Intent(PayrollDocuments.this,Retrieve.class);
                    intent.putExtra("EMPID",empid);
                    intent.putExtra("TYPE",category);
                    intent.putExtra("usertype",UserType);
                    intent.putExtra("id",UserId);
                    startActivity(intent);

                }
                if (text.equals("Conveyances")){
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();

                    Intent intent=new Intent(PayrollDocuments.this,Retrieve.class);
                    intent.putExtra("EMPID",empid);
                    intent.putExtra("TYPE",category);
                    intent.putExtra("usertype",UserType);
                    intent.putExtra("id",UserId);
                    startActivity(intent);
                }
            }
        });
    }
    private void downloadDataViaUrl() {
        Helper.showDialog(this);
        pdfref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Helper.dismissDialog();
                mTextview.setText(uri.toString());
                Intent i=new Intent(PayrollDocuments.this,PayDocuments.class);
                i.putExtra("EmpId",empid);
                i.putExtra("Month",month);
                i.putExtra("Year",year);
                i.putExtra("Type",ty);
                i.putExtra("text",mTextview.getText().toString());
                i.putExtra("usertype",UserType);
                i.putExtra("id",UserId);
                startActivity(i);
                Toast.makeText(PayrollDocuments.this, "Click on PDF to download", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Helper.dismissDialog();
                Toast.makeText(PayrollDocuments.this, "Cannot found Related File", Toast.LENGTH_SHORT).show();
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
            UserId = userid.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(PayrollDocuments.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
