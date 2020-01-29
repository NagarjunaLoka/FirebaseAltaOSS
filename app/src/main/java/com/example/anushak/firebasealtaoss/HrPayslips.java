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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class HrPayslips extends AppCompatActivity {

    ListView listView;

    TextView text,userid,usertype,empid;
    String Text,UserId,UserType,Empid;

    ImageView iv;
    TextView dashboard;

    HorizontalScrollView hsv1;
    Animation frombottom,fromtop,fromleft,fromright;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_hr_payslips);

        listView=findViewById(R.id.listView);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView) findViewById(R.id.dashboard);
        hsv1 = (HorizontalScrollView) findViewById(R.id.hsv1);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(Empid);

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
                Intent intent = new Intent(HrPayslips.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

            text.setText("HR Dashboard - Departments List(Payslips)");


        String[] Departments = new String[] { "Admin","Employee", "HR",
                "Networking", "Testing", "Hardware", "Software",
                "Website"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                Departments);

        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Capture the click position and set it into a string
                String dept = (String) listView.getItemAtPosition(position);

                // Launch SingleItemView.java using intent
                Intent i = new Intent(HrPayslips.this, Employees_in_Dept.class);

                // Send captured string to SingleItemView.java
                i.putExtra("department", dept);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);

                // Start SingleItemView.java
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
            Intent i = new Intent(HrPayslips.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
