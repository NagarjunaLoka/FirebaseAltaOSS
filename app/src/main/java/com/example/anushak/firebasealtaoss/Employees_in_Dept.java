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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Employees_in_Dept extends AppCompatActivity {

    ListView listView;
    Department_ListAdapter mList;
    FirebaseDatabase fdatabse;
    DatabaseReference dref;
    String department,hr;

    TextView text,userid,usertype,empid;
    String Text,UserId,UserType,Empid;

    ImageView iv;
    TextView dashboard,dept;

    HorizontalScrollView hsv1;
    Animation frombottom,fromtop,fromleft,fromright;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_employees_in__dept);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dept = (TextView) findViewById(R.id.dept);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);

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
                Intent intent = new Intent(Employees_in_Dept.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        dept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Employees_in_Dept.this,HrPayslips.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        text.setText("HR Dashboard - Dept.List(Payslips) - Employee List");


        fdatabse = FirebaseDatabase.getInstance();
        dref = fdatabse.getReference("fir-altaoss");

        listView = (ListView) findViewById(R.id.deptlist);

        department = getIntent().getStringExtra("department");

        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("department")
                .equalTo(department);

        mList = new Department_ListAdapter(query,this, R.layout.departmentlist_view);

        listView.setAdapter(mList);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Department ListViewClickData=(Department) parent.getItemAtPosition(position);
                Toast.makeText(Employees_in_Dept.this, ListViewClickData.getName(), Toast.LENGTH_SHORT).show();

                Intent i=new Intent(Employees_in_Dept.this,PaySlips_Form.class);
                i.putExtra("name",ListViewClickData.getName());
                i.putExtra("empid",Empid);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
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
            Intent i = new Intent(Employees_in_Dept.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
