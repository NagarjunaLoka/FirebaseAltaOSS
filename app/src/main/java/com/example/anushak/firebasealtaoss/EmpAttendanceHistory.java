package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class EmpAttendanceHistory extends AppCompatActivity {

    TextView empid,userid,usertype,text;
    String Empid,UserId,UserType,Text;

    private RecyclerView recyclerView;
    private Attendanceadapter attendanceadapter;
    private List<Attendance> attendanceList;
    DatabaseReference Drref;

    HorizontalScrollView hsv1,hsv2;
    //Paths
    ImageView iv,iv1;
    TextView dashboard,dashboard1,attendance,attendance1;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_attendance_history);

        empid = findViewById(R.id.empid);
        userid = findViewById(R.id.userid);
        usertype = findViewById(R.id.usertype);
        text = findViewById(R.id.text);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceList = new ArrayList<>();
        attendanceadapter = new Attendanceadapter(this, attendanceList);
        recyclerView.setAdapter(attendanceadapter);

        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        attendance = (TextView)findViewById(R.id.attendance);
        attendance1 = (TextView)findViewById(R.id.attendance1);

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
                Intent intent = new Intent(EmpAttendanceHistory.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpAttendanceHistory.this,AttendanceNew.class);
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
                Intent intent = new Intent(EmpAttendanceHistory.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        attendance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpAttendanceHistory.this,AttendanceNew.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Attendance - History");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Attendance - History");
        }

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String date1 = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        String month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        String year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));

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
            Intent i = new Intent(EmpAttendanceHistory.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
