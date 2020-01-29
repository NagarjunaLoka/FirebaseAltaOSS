package com.example.anushak.firebasealtaoss;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PayDocuments extends AppCompatActivity {

    TextView Empid,Month,Year,Type;
    TextView mTextview;

    ImageButton Url;
    String url, empid,month,year,type,UserId,Text;
    android.support.v7.widget.Toolbar toolbar;
    ImageView iv;
    TextView dashboard,list,qtype,userid,text;
    Button download;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_pay_documents);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference=FirebaseDatabase.getInstance().getReference("MyDownloads");

        Url = (ImageButton) findViewById(R.id.imag);
        Empid = (TextView) findViewById(R.id.PayID);
        Month = (TextView) findViewById(R.id.month);
        Year = (TextView) findViewById(R.id.year);
        Type=(TextView)findViewById(R.id.type);
        mTextview=(TextView)findViewById(R.id.textview);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        list = (TextView)findViewById(R.id.list);
        qtype = (TextView)findViewById(R.id.qtype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        download = (Button)findViewById(R.id.download);

        UserId = getIntent().getStringExtra("id");
        empid = getIntent().getStringExtra("EmpId");
        month = getIntent().getStringExtra("Month");
        year = getIntent().getStringExtra("Year");
        type = getIntent().getStringExtra("Type");
        url=getIntent().getStringExtra("text");

        userid.setText(UserId);
        Empid.setText(empid);
        Month.setText(month);
        Year.setText(year);
        Type.setText(type);
        final Uri uri=Uri.parse(url);
        text.setText("Admin Dashboard - Employees List - Payroll - Payslip("+month+"-"+year+")");

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,EmpPayroll.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        qtype.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,PayrollDocuments.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        Url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri,"application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                String  EMPID=empid;
                String MONTH=month;
                String YEAR=year;
                String URL=url;
                String  TYPE=type+"("+MONTH+"-"+YEAR+")";
                String id = databaseReference.push().getKey();

                User1 user = new User1(id,TYPE, EMPID, URL);
                databaseReference.child(id).setValue(user);

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
            Intent i = new Intent(PayDocuments.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}

