package com.example.anushak.firebasealtaoss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LeavesHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LeavesAdapter leavesAdapter;
    private List<Project> leavesList;
    DatabaseReference Drref;

    Spinner monthspinner,yearspinner,yearspinner1;
    TextView go,go1;

    TextView text,userid,usertype,empid;
    String Text,UserId,UserType,Empid;

    HorizontalScrollView hsv1;
    ImageView iv;
    TextView dashboard,attendance,form;
    Toolbar toolbar;

    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> months = new ArrayList<String>();

    static final String[] Months = new String[] { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaves_history);

        monthspinner=findViewById(R.id.monthspinner);
        yearspinner=findViewById(R.id.yearspinner);
        yearspinner1=findViewById(R.id.yearspinner1);
        go=findViewById(R.id.search);
        go1=findViewById(R.id.search1);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        attendance = (TextView)findViewById(R.id.attendance);
        form = findViewById(R.id.form);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Empid = getIntent().getStringExtra("empid");

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(Empid);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeavesHistory.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeavesHistory.this,AttendanceNew.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeavesHistory.this,Leave.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        text.setText("Employee Dashboard - Attendance - Leave Module(Apply Leave) - Leaves History");


        progressDialog = new ProgressDialog(LeavesHistory.this);

        final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2018; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        yearspinner.setAdapter(adapter);
        yearspinner1.setAdapter(adapter);

        Drref = FirebaseDatabase.getInstance().getReference("Leaves");
        Query query=Drref.orderByChild("empid").equalTo(Empid);
        query.addListenerForSingleValueEvent(valueEventListener);

        recyclerView = findViewById(R.id.leaveshistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        leavesList = new ArrayList<>();
        leavesAdapter = new LeavesAdapter(this, leavesList);
        recyclerView.setAdapter(leavesAdapter);

        yearspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    final int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
                    for (int i = 1; i <= Months.length; i++) {
                        //Spinner =(Spinner)findViewById(Months[i]);
                        months.add(Integer.toString(i));
                        //Spinner.setAdapter(Months[i]);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LeavesHistory.this, android.R.layout.simple_spinner_item, Months);
                        monthspinner.setAdapter(adapter1);
                    }

                } else {
                    final int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
                    for (int i = 1; i <= Months.length - thisMonth; i++) {
                        months.add(Integer.toString(i));
                        //Spinner.setAdapter(Months[i]);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LeavesHistory.this, android.R.layout.simple_spinner_item, Months);
                        monthspinner.setAdapter(adapter1);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                String month=monthspinner.getSelectedItem().toString();
                String year=yearspinner.getSelectedItem().toString();
                Query query1=Drref.orderByChild("empidmonthandyear").equalTo(Empid+month+year);
                query1.addListenerForSingleValueEvent(valueEventListener);

            }
        });

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Loading...");
                progressDialog.show();
                String year1=yearspinner1.getSelectedItem().toString();
                Query query2=Drref.orderByChild("empidyear").equalTo(Empid+year1);
                query2.addListenerForSingleValueEvent(valueEventListener);
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            leavesList.clear();
            if (dataSnapshot.exists()) {
                progressDialog.dismiss();
                recyclerView.setVisibility(View.VISIBLE);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Project project = snapshot.getValue(Project.class);
                    leavesList.add(project);
                }
                leavesAdapter.notifyDataSetChanged();
            }
            else{
                Toast.makeText(LeavesHistory.this, "Value not Found", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                recyclerView.setVisibility(View.GONE);
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
        int id = item.getItemId();
        if (id == R.id.query) {
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(LeavesHistory.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}