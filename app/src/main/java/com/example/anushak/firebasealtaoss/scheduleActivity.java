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
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class scheduleActivity extends AppCompatActivity {

    ListView listView;
    private DatabaseReference mDatabase;
    private ListAdapter1 mList;

    android.support.v7.widget.Toolbar toolbar;
    TextView text,userid,usertype;
    String Text,UserId,UserType;
    HorizontalScrollView hsv1,hsv2;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_schedule);

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
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        userid.setText(UserId);
        usertype.setText(UserType);

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Interview Dashboard - Scheduled candidates List");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Interview Dashboard - Scheduled candidates List");
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
                Intent intent = new Intent(scheduleActivity.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scheduleActivity.this,InterviewDashboard.class);
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
                Intent intent = new Intent(scheduleActivity.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scheduleActivity.this,InterviewDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference("Interviews");
        Query query=mDatabase.orderByChild("status").equalTo("Schedule");
        mList = new ListAdapter1(query, this, R.layout.listview2);
        listView = (ListView) this.findViewById(R.id.walklist);
        listView.setAdapter(mList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intervi ListViewClickData = (Intervi) parent.getItemAtPosition(position);
                Toast.makeText(scheduleActivity.this, ListViewClickData.getFull_name(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(scheduleActivity.this,show_schedule_list_Activity.class);
                intent.putExtra("Interviewid",ListViewClickData. getInterid());
                intent.putExtra("Name",ListViewClickData.getFull_name());
                intent.putExtra("Name",ListViewClickData.getFull_name());
                intent.putExtra("qualification",ListViewClickData.getQualification());
                intent.putExtra("fresher",ListViewClickData.getFreshers());
                intent.putExtra("emaid",ListViewClickData.getEmail());
                intent.putExtra("Mobile",ListViewClickData.getMobile());
                intent.putExtra("designation",ListViewClickData.getDesign());
                intent.putExtra("proje",ListViewClickData.getProject());
                intent.putExtra("update",ListViewClickData.getSelect());
                intent.putExtra("status",ListViewClickData.getStatus());
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
        inflater.inflate(R.menu.new_candidate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();
        if(id == R.id.newcandidate)
        {
            Intent intent = new Intent(scheduleActivity.this, addschedule_Activity.class);
            intent.putExtra("userId",UserId);
            intent.putExtra("usertype",UserType);
            startActivity(intent);
        }
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(scheduleActivity.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
