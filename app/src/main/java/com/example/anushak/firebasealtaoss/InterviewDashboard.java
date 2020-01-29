package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InterviewDashboard extends AppCompatActivity {

    TextView wak,refer,schedule, history;
    LinearLayout walkinpng, referencepng, schedulepng, offcampuspng, historypng;
    android.support.v7.widget.Toolbar toolbar;
    TextView text,userid,usertype;
    String Text,UserId,UserType;
    HorizontalScrollView hsv1,hsv2;
    ImageView iv,iv1;
    TextView dashboard,dashboard1;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_interview_dashboard);
        wak=(TextView) findViewById(R.id.walk);
        refer=(TextView) findViewById(R.id.refer);
        schedule=(TextView) findViewById(R.id.schedule);
        history=(TextView)findViewById(R.id.history);
        walkinpng=(LinearLayout)findViewById(R.id.walkpng);
        referencepng=(LinearLayout)findViewById(R.id.referencepng);
        schedulepng=(LinearLayout)findViewById(R.id.schedulepng);
        offcampuspng=(LinearLayout)findViewById(R.id.offcampuspng);
        historypng=(LinearLayout)findViewById(R.id.historypng);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        userid.setText(UserId);
        usertype.setText(UserType);

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Interview Dashboard");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Interview Dashboard");
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
                Intent intent = new Intent(InterviewDashboard.this,AdminDashboard.class);
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
                Intent intent = new Intent(InterviewDashboard.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        walkinpng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterviewDashboard.this,WalkinActivity.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });

       referencepng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterviewDashboard.this,ReferenceActivity.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });

         schedulepng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterviewDashboard.this,scheduleActivity.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });

        offcampuspng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterviewDashboard.this,offcampus_activity.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
        historypng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterviewDashboard.this,InterviewHistory.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.queryform,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.query) {
            UserId = userid.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(InterviewDashboard.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}

