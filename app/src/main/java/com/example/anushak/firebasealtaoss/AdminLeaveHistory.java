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
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminLeaveHistory extends AppCompatActivity {

    TextView text,usertype,empid,userId;
    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;
    android.support.v7.widget.Toolbar toolbar;

    String Empid,UserId,UserType,Text;
    RecyclerView recyclerView;
    Button appliedleaves, leavehistory;

    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;

    private AppliedAdapter appliedAdapter;
    private List<Project> leavesList;
    DatabaseReference Drref;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_leave_history);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        empid = findViewById(R.id.empid);
        recyclerView = findViewById(R.id.recyclerView);
        appliedleaves = findViewById(R.id.appliedleaves);
        leavehistory = findViewById(R.id.leavehistory);
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

        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");
        UserType = getIntent().getStringExtra("usertype");

        userId.setText(UserId);
        empid.setText(Empid);
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
                Intent intent = new Intent(AdminLeaveHistory.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLeaveHistory.this,AttEmpDirectory.class);
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
                Intent intent = new Intent(AdminLeaveHistory.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLeaveHistory.this,AttEmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Employee Directory(Attendance) - Leave Module");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Employee Directory(Attendance) - Leave Module");
        }

        progressDialog = new ProgressDialog(AdminLeaveHistory.this);

        leavehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AdminLeaveHistory.this,EmployeeLeaveHistory.class);
                intent.putExtra("empid", Empid);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });

        appliedleaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMethod();
            }
        });
    }


    public void callMethod(){
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Drref = FirebaseDatabase.getInstance().getReference("Leaves");
        Query query=Drref.orderByChild("status").equalTo("Leave Request");
        query.addListenerForSingleValueEvent(valueEventListener);

        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminLeaveHistory.this));
        leavesList = new ArrayList<>();
        appliedAdapter = new AppliedAdapter(AdminLeaveHistory.this, leavesList);
        recyclerView.setAdapter(appliedAdapter);
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
                appliedAdapter.notifyDataSetChanged();
            }
            else{
                Toast.makeText(AdminLeaveHistory.this, "Value Not found", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(AdminLeaveHistory.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}
