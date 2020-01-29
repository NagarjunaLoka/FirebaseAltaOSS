package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AdminAttendance extends AppCompatActivity {

    TextView empid,monthandyear,date;

    private RecyclerView recyclerView;
    private Attendanceadapter attendanceadapter;
    private List<Attendance> attendanceList;
    DatabaseReference Drref;

    android.support.v7.widget.Toolbar toolbar;

    TextView userid,text,usertype;
    String UserId,Text,UserType;

    HorizontalScrollView hsv1,hsv2;

    //Paths
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_attendance);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        empid = findViewById(R.id.empid);
        monthandyear = findViewById(R.id.monthandyear);
        date = findViewById(R.id.date);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceList = new ArrayList<>();
        attendanceadapter = new Attendanceadapter(this, attendanceList);
        recyclerView.setAdapter(attendanceadapter);

       String Empid = getIntent().getStringExtra("empid");
       empid.setText(Empid);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");

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
                Intent intent = new Intent(AdminAttendance.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAttendance.this,AttEmpDirectory.class);
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
                Intent intent = new Intent(AdminAttendance.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAttendance.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Attendance - Loggedin/Loggedout Details");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Attendance - Loggedin/Loggedout Details");
        }

        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String date1 = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        String month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        String year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));

        monthandyear.setText(month);
        String Monthandyear = monthandyear.getText().toString();
        int re2 = Integer.valueOf(Monthandyear) + 1;
        monthandyear.setText(year + "-" + re2);

        date.setText(month);
        String Date = date.getText().toString();
        int re1 = Integer.valueOf(Date) + 1;
        date.setText(year + "-" + re1 + "-" + date1);

        //1. SELECT * FROM Artists
        Drref = FirebaseDatabase.getInstance().getReference("Attendance Details");

        //2. SELECT * FROM Artists WHERE id = "-LAJ7xKNj4UdBjaYr8Ju"
        Query query = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString())
                .orderByChild("empid")
                .equalTo(empid.getText().toString());
        query.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            attendanceList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Attendance attendance = snapshot.getValue(Attendance.class);
                    attendanceList.add(attendance);
                }
                attendanceadapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();

        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(AdminAttendance.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
