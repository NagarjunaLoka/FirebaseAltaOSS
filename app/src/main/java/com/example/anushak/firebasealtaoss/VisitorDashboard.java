package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VisitorDashboard extends AppCompatActivity {

    Button myvistors, addvisitor, editvistor, searchvisitor;
    TextView empid,text,userid,usertype;
    String EMPID,Text,UserId,UserType;
    ImageView iv;
    TextView dashboard;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_visitor_dashboard);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        empid = (TextView)findViewById(R.id.empid);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        myvistors = findViewById(R.id.btnmyvis);
        addvisitor = findViewById(R.id.btnaddvis);
        editvistor = findViewById(R.id.btndeletevis);
        searchvisitor = findViewById(R.id.btndatevis);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        EMPID = getIntent().getStringExtra("empid");
        empid.setText(EMPID);
        userid.setText(UserId);
        usertype.setText(UserType);
        text.setText("Admin Dashboard - Visitor Dashboard");

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDashboard.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        UserType = usertype.getText().toString();
        addvisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDashboard.this, Addvisitor.class);
                intent.putExtra("usertype",UserType);
                intent.putExtra("userId",UserId);
                startActivity(intent);
            }
        });

      myvistors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDashboard.this, VisDirectory.class);
                intent.putExtra("empid",EMPID);
                intent.putExtra("usertype",UserType);
                intent.putExtra("userId",UserId);
                startActivity(intent);
            }
        });

        searchvisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDashboard.this, AdminVisList.class);
                intent.putExtra("usertype",UserType);
                intent.putExtra("userId",UserId);
                startActivity(intent);
            }
        });

        editvistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDashboard.this, AdminEditVisitor.class);
                intent.putExtra("usertype",UserType);
                intent.putExtra("userId",UserId);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.query) {
            UserId = userid.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(VisitorDashboard.this, QueryForm.class);
            i.putExtra("userId", UserId);
            i.putExtra("message", Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
