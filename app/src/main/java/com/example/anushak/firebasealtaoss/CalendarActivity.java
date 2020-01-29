package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    Button btn_add_event;
    TextView text,userid,usertype;
    android.support.v7.widget.Toolbar toolbar;
    String UserId,UserType,Text;
    HorizontalScrollView hsv1,hsv2,hsv3;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_calendar);
        btn_add_event = (Button)findViewById(R.id.btn_add_event);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        usertype.setText(UserType);
        userid.setText(UserId);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(CalendarActivity.this,HrDashboard.class);
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
                Intent intent = new Intent(CalendarActivity.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Calendar");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Calendar");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Calendar");
        }
        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();
        btn_add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(CalendarActivity.this,EventActivity.class);
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
        inflater.inflate(R.menu.calendar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UserId = userid.getText().toString();
        Text = text.getText().toString();
        switch (item.getItemId()) {
            case R.id.query:
                Intent intent = new Intent(CalendarActivity.this,QueryForm.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("message",Text);
                startActivity(intent);
                return true;
            case R.id.publicholiday:
                Intent intent1 = new Intent(CalendarActivity.this,Holidays.class);
                intent1.putExtra("userId",UserId);
                intent1.putExtra("usertype",UserType);
                startActivity(intent1);
                return true;
            case R.id.Event:
                Intent intent2 = new Intent(CalendarActivity.this,ShowEventActivity.class);
                intent2.putExtra("userId",UserId);
                intent2.putExtra("usertype",UserType);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
