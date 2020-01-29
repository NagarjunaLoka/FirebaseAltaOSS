package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QueryUpdate extends AppCompatActivity {

    TextView empid,email,subject,querymessage,status,queryid;
    Button submit;
    RadioGroup radiogroup;
    RadioButton radioButton;
    String QueryId,Empid,Email,Subject,Status,QueryMessage;

    private DatabaseReference mDatabase;

    android.support.v7.widget.Toolbar toolbar;

    TextView text,usertype,userid;
    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;
    String Text,UserType,UserId;

    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_query_update);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);
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

        usertype.setText(UserType);
        userid.setText(UserId);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        hsv1.setAnimation(fromtop);
        hsv2.setAnimation(fromtop);

        mDatabase = FirebaseDatabase.getInstance().getReference("Queries");

        empid = (TextView)findViewById(R.id.empid);
        queryid = (TextView)findViewById(R.id.queryid);
        subject = (TextView)findViewById(R.id.subject);
        status = (TextView)findViewById(R.id.status);
        email = (TextView)findViewById(R.id.email);
        querymessage = (TextView)findViewById(R.id.querymessage);
        submit = (Button)findViewById(R.id.submit);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);

        Empid = getIntent().getStringExtra("empid");
        Email = getIntent().getStringExtra("email");
        Subject = getIntent().getStringExtra("subject");
        Status = getIntent().getStringExtra("status");
        QueryMessage = getIntent().getStringExtra("qmessage");
        QueryId = getIntent().getStringExtra("queryid");

        empid.setText(Empid);
        email.setText(Email);
        status.setText(Status);
        subject.setText(Subject);
        querymessage.setText(QueryMessage);
        queryid.setText(QueryId);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryUpdate.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryUpdate.this,QueryDashboard.class);
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
                Intent intent = new Intent(QueryUpdate.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryUpdate.this,QueryDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Query Dashboard - Query Status Update");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Query Dashboard - Query Status Update");
        }

    }
    public void onRadioButtonClicked(View v)
    {
        //require to import the RadioButton class
        RadioButton pending = (RadioButton) findViewById(R.id.pending);
        RadioButton resolve = (RadioButton) findViewById(R.id.resolved);
        RadioButton unresolve = (RadioButton) findViewById(R.id.unresolve);
        RadioButton unabletoresolve = (RadioButton) findViewById(R.id.unabletoresolve);

        //is the current radio button now checked?
        boolean  checked = ((RadioButton) v).isChecked();

        //now check which radio button is selected
        //android switch statement
        switch(v.getId()){

            case R.id.pending:
                if(checked)
                pending.setTypeface(null, Typeface.BOLD_ITALIC);
                resolve.setTypeface(null, Typeface.NORMAL);
                unresolve.setTypeface(null, Typeface.NORMAL);
                unabletoresolve.setTypeface(null, Typeface.NORMAL);
                status.setText("Pending");
                Status=status.getText().toString();
                mDatabase.child(QueryId).child("status").setValue(Status);
                Toast.makeText(QueryUpdate.this,status.getText(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.resolved:
                if(checked)
                pending.setTypeface(null, Typeface.NORMAL);
                resolve.setTypeface(null, Typeface.BOLD_ITALIC);
                unresolve.setTypeface(null, Typeface.NORMAL);
                unabletoresolve.setTypeface(null, Typeface.NORMAL);
                status.setText("Resolved");
                Status=status.getText().toString();
                mDatabase.child(QueryId).child("status").setValue(Status);
                Toast.makeText(QueryUpdate.this,status.getText(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.unresolve:
                if(checked)
                pending.setTypeface(null, Typeface.NORMAL);
                resolve.setTypeface(null, Typeface.NORMAL);
                unresolve.setTypeface(null, Typeface.BOLD_ITALIC);
                unabletoresolve.setTypeface(null, Typeface.NORMAL);
                status.setText("UnResolved");
                Status=status.getText().toString();
                mDatabase.child(QueryId).child("status").setValue(Status);
                Toast.makeText(QueryUpdate.this,status.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.unabletoresolve:
                if(checked)
                pending.setTypeface(null, Typeface.NORMAL);
                resolve.setTypeface(null, Typeface.NORMAL);
                unresolve.setTypeface(null, Typeface.NORMAL);
                unabletoresolve.setTypeface(null, Typeface.BOLD_ITALIC);
                status.setText("Unable to Resolve");
                Status=status.getText().toString();
                mDatabase.child(QueryId).child("status").setValue(Status);
                Toast.makeText(QueryUpdate.this,status.getText(), Toast.LENGTH_SHORT).show();
                break;
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
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(QueryUpdate.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
