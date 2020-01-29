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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Preferences extends AppCompatActivity {

    ListView listView;
    private EmpAdapter mList;
    android.support.v7.widget.Toolbar toolbar;
    String Text,UserType,Empid,UserId;
    ArrayList<User> EmpList = new ArrayList<User>();
    User user;
    TextView text,usertype,empid,userId,dashboard;
    HorizontalScrollView hsv1;
    Animation frombottom,fromtop,fromleft,fromright;
    Button admin,hr,employee,projectpreferences;
    DatabaseReference reference;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_preferences);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) this.findViewById(R.id.listView);

        admin = (Button) findViewById(R.id.admin);
        hr = (Button)findViewById(R.id.hr);
        employee = (Button) findViewById(R.id.employee);
        projectpreferences = (Button) findViewById(R.id.projectpreferences);
        userId = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);
        empid = (TextView)findViewById(R.id.empid);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView) findViewById(R.id.dashboard);

        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");
        UserType = getIntent().getStringExtra("usertype");

        userId.setText(UserId);
        empid.setText(Empid);
        usertype.setText(UserType);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

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
                Intent intent = new Intent(Preferences.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Preferences");
        }

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = reference.orderByChild("department").equalTo("Admin");
                mList = new EmpAdapter(query, Preferences.this, R.layout.emp_list);
                listView.setAdapter(mList);
            }
        });
        hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = reference.orderByChild("department").equalTo("HR");
                mList = new EmpAdapter(query, Preferences.this, R.layout.emp_list);
                listView.setAdapter(mList);
            }
        });
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = reference.orderByChild("department").equalTo("Employee");
                mList = new EmpAdapter(query, Preferences.this, R.layout.emp_list);
                listView.setAdapter(mList);
            }
        });
        projectpreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Preferences.this,ProjectPreferences.class);
                intent.putExtra("empid", Empid);
                intent.putExtra("userId", UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });

        UserType = usertype.getText().toString();
        UserId = userId.getText().toString();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                User user = (User) mList.getItem(position);
                Intent intent = new Intent(getApplicationContext(), Permissions.class);
                intent.putExtra("empid", user.getEmpid());
                intent.putExtra("userId", user.getUserId());
                intent.putExtra("usertype",UserType);
                startActivity(intent);
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
            UserId = userId.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(Preferences.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
