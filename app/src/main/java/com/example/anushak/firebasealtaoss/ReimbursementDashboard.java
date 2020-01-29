package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ReimbursementDashboard extends AppCompatActivity {

    Button mobile,general,conveyances;
    TextView empid,text,userid,usertype;
    String Category,Empid,Text,UserId,UserType;
    android.support.v7.widget.Toolbar toolbar;
    HorizontalScrollView hsv1,hsv2;

    ImageView iv,iv1;
    TextView dashboard,dashboard1;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_reimbursement_dashboard);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mobile = (Button)findViewById(R.id.mobile);
        general = (Button)findViewById(R.id.general);
        conveyances = (Button)findViewById(R.id.conveyances);

        empid = (TextView)findViewById(R.id.empid);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        Empid = getIntent().getStringExtra("empid");
        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        empid.setText(Empid);
        userid.setText(UserId);
        usertype.setText(UserType);

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
                Intent intent = new Intent(ReimbursementDashboard.this,AdminDashboard.class);
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
                Intent intent = new Intent(ReimbursementDashboard.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);


        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Reimbursement Dashboard");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Reimbursement Dashboard");
        }

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category=mobile.getText().toString();
                Intent intent = new Intent(ReimbursementDashboard.this,Mobile.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                intent.putExtra("category",Category);
                startActivity(intent);
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category=general.getText().toString();
                Intent intent = new Intent(ReimbursementDashboard.this,Mobile.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                intent.putExtra("category",Category);
                startActivity(intent);
            }
        });
        conveyances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category=conveyances.getText().toString();
                Intent intent = new Intent(ReimbursementDashboard.this,Mobile.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                intent.putExtra("category",Category);
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
            UserId = userid.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(ReimbursementDashboard.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
