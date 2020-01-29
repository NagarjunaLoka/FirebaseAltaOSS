package com.example.anushak.firebasealtaoss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class EmployeeLeaveHistory extends AppCompatActivity {

    TextView text,usertype,userId;
    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;
    android.support.v7.widget.Toolbar toolbar;

    String UserId,UserType,Text;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1,leave,leave1;

    private RecyclerView recyclerView;
    private LeavesAdapter leavesAdapter;
    private List<Project> leavesList;
    DatabaseReference Drref;

    Spinner monthspinner,yearspinner,yearspinner1;
    TextView go,go1;

    ProgressDialog progressDialog;

    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> months = new ArrayList<String>();

    static final String[] Months = new String[] { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_leave_history);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        monthspinner=findViewById(R.id.monthspinner);
        yearspinner=findViewById(R.id.yearspinner);
        yearspinner1=findViewById(R.id.yearspinner1);
        go=findViewById(R.id.search);
        go1=findViewById(R.id.search1);

        userId = findViewById(R.id.userid);
        usertype = findViewById(R.id.usertype);
        text = findViewById(R.id.text);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        list = findViewById(R.id.list);
        list1 = findViewById(R.id.list1);
        leave = findViewById(R.id.leave);
        leave1 = findViewById(R.id.leave1);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        userId.setText(UserId);
        usertype.setText(UserType);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        hsv1.setAnimation(fromtop);
        hsv2.setAnimation(fromtop);

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeLeaveHistory.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeLeaveHistory.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeLeaveHistory.this,AdminLeaveHistory.class);
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
                Intent intent = new Intent(EmployeeLeaveHistory.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeLeaveHistory.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        leave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeLeaveHistory.this,AdminLeaveHistory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Employee Directory(Attendance) - Leave Module - History");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Employee Directory(Attendance) - Leave Module - History");
        }


        progressDialog = new ProgressDialog(EmployeeLeaveHistory.this);

        final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2018; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        yearspinner.setAdapter(adapter);
        yearspinner1.setAdapter(adapter);

        Drref = FirebaseDatabase.getInstance().getReference("Leaves");
        // Query query=Drref.orderByChild("empid").equalTo(Empid);
        Drref.addListenerForSingleValueEvent(valueEventListener);

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
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(EmployeeLeaveHistory.this, android.R.layout.simple_spinner_item, Months);
                        monthspinner.setAdapter(adapter1);
                    }

                } else {
                    final int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
                    for (int i = 1; i <= Months.length - thisMonth; i++) {
                        months.add(Integer.toString(i));
                        //Spinner.setAdapter(Months[i]);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(EmployeeLeaveHistory.this, android.R.layout.simple_spinner_item, Months);
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
                Query query1=Drref.orderByChild("monthandyear").equalTo(month+year);
                query1.addListenerForSingleValueEvent(valueEventListener);

            }
        });

        go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Loading...");
                progressDialog.show();
                String year=yearspinner1.getSelectedItem().toString();
                Query query=Drref.orderByChild("year").equalTo(year);
                query.addListenerForSingleValueEvent(valueEventListener);
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
                Toast.makeText(EmployeeLeaveHistory.this, "Value not Found", Toast.LENGTH_SHORT).show();
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
            UserId = userId.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(EmployeeLeaveHistory.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}