package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MobileStatus extends AppCompatActivity {

    TextView empid,category,date,status,mobile,amount;
    String Empid,Category,Date,Status,Mobile,Amount,PdfUrl,Text,Id,Type,UserType,Message,UserId;
    ImageView pdfurl;
    Button approve,reject;

    android.support.v7.widget.Toolbar toolbar;
    private DatabaseReference mDatabase;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1,type,type1,usertype,message,userid;
    HorizontalScrollView hsv1,hsv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mobile_status);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference("Reimbursement");

        empid=(TextView) findViewById(R.id.empid);
        category=(TextView) findViewById(R.id.category);
        date=(TextView) findViewById(R.id.date);
        status=(TextView) findViewById(R.id.status);
        mobile=(TextView) findViewById(R.id.mobilenumber);
        amount=(TextView) findViewById(R.id.amount);
        pdfurl = (ImageView)findViewById(R.id.url);
        approve = (Button) findViewById(R.id.approve);
        reject = (Button) findViewById(R.id.reject);
        type = (TextView)findViewById(R.id.type);
        type1 = (TextView)findViewById(R.id.type1);
        usertype = (TextView)findViewById(R.id.usertype);
        message = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Type = getIntent().getStringExtra("type");
        Empid = getIntent().getStringExtra("Eid");
        Category = getIntent().getStringExtra("Category");
        Date = getIntent().getStringExtra("Uploaded");
        Status = getIntent().getStringExtra("Update");
        Mobile = getIntent().getStringExtra("mobileno");
        Amount = getIntent().getStringExtra("Amount");
        PdfUrl = getIntent().getStringExtra("URL");
        Id=getIntent().getStringExtra("ID");

        userid.setText(UserId);
        usertype.setText("");
        empid.setText(Empid);
        category.setText(Category);
        date.setText(Date);
        mobile.setText(Mobile);
        amount.setText(Amount);
        type.setText(Type+"  >>");
        type1.setText(Type+"  >>");

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            message.setText("HR Dashboard - Reimbursement Dashboard - "+ Type+ " - Permission");
        }
        else if(UserType.equals("admin"))
        {
            message.setText("Admin Dashboard - Reimbursement Dashboard - "+ Type+ " - Permission");
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
                Intent intent = new Intent(MobileStatus.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MobileStatus.this,ReimbursementDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        type.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MobileStatus.this,Mobile.class);
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
                Intent intent = new Intent(MobileStatus.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MobileStatus.this,ReimbursementDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        type1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MobileStatus.this,Mobile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        pdfurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(PdfUrl));
                startActivity(intent);
            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("approve");
                Text=status.getText().toString();
                mDatabase.child(Category).child(Id).child("update").setValue(Text);

            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("reject");
                Text=status.getText().toString();
                mDatabase.child(Category).child(Id).child("update").setValue(Text);

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
            Message = message.getText().toString();
            Intent i = new Intent(MobileStatus.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Message);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
