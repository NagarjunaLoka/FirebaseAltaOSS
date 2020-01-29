package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.net.URL;

public class MyDownloads extends AppCompatActivity {

    TextView empid;
    String Empid;
    ListView DownloadsListView;
    URL url;
    DownloadAdapter downloadAdapter;
    private DatabaseReference mDatabase;

    TextView usertype,userid,text;
    String UserType,UserId,Text;
    HorizontalScrollView hsv1,hsv2,hsv3;
    android.support.v7.widget.Toolbar toolbar;

    //Paths
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my_downloads);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase= FirebaseDatabase.getInstance().getReference("MyDownloads");
        empid = (TextView) findViewById(R.id.empid);

        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
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

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Empid = getIntent().getStringExtra("empid");

        usertype.setText(UserType);
        userid.setText(UserId);
        empid.setText(Empid);

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - My Downloads");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - My Downloads");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - My Downloads");
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
                Intent intent = new Intent(MyDownloads.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(MyDownloads.this,HrDashboard.class);
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
                Intent intent = new Intent(MyDownloads.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        DownloadsListView = (ListView) findViewById(R.id.listview);
        Query query=mDatabase.orderByChild("empid1").equalTo(Empid);
        downloadAdapter=new DownloadAdapter(query,this,R.layout.downloads_layout);

        DownloadsListView.setAdapter(downloadAdapter);

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
            Intent i = new Intent(MyDownloads.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}



