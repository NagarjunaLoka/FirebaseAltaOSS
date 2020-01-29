package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class EmpProjects extends AppCompatActivity {

    ListView listView,listview2,listview3,listview4;
    private DatabaseReference mDatabase;
    private ProjectAdapter mList,mList1,mList2,mList3;
    android.support.v7.widget.Toolbar toolbar;
    ArrayList<Project> ProjetsList = new ArrayList<Project>();
    LinearLayout ongoing,futureProjects,completedProjects,totalProjects;
    SearchView search;
    TextView name,usertype,userid,text;
    String UserType,UserId,Text;

    HorizontalScrollView hsv1,hsv2;
    Animation frombottom,fromtop,fromleft,fromright;

    ImageView iv,iv1;
    TextView dashboard,dashboard1;

    public static final String KEY_PROJECTNAME="projectname";
    public static final String KEY_TEAMNAME="teamleadername";
    public static final String KEY_CLIENTNAME="clientname";
    public static final String KEY_START="startdate";
    public static final String KEY_END="enddate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_projects);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView)findViewById(R.id.name);
        ongoing = (LinearLayout) findViewById(R.id.ongoingProjects);
        futureProjects = (LinearLayout)findViewById(R.id.futureProjects);
        completedProjects = (LinearLayout)findViewById(R.id.completedProjects);
        totalProjects = (LinearLayout)findViewById(R.id.totalProjects);

        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        hsv1.setAnimation(fromtop);
        hsv2.setAnimation(fromtop);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        usertype.setText(UserType);
        userid.setText(UserId);

        mDatabase = FirebaseDatabase.getInstance().getReference("Projects");

        Query query = mDatabase.orderByChild("projectstatus").equalTo("Running");
        Query query1 = mDatabase.orderByChild("projectstatus").equalTo("Completed");
        Query query2 = mDatabase.orderByChild("projectstatus").equalTo("Discussing");

        mList = new ProjectAdapter(mDatabase, this, R.layout.project_list);
        mList1 = new ProjectAdapter(query, this, R.layout.project_list);
        mList2 = new ProjectAdapter(query1, this, R.layout.project_list);
        mList3 = new ProjectAdapter(query2, this, R.layout.project_list);

        listView = (ListView) this.findViewById(R.id.listView);
        listview2 = (ListView) this.findViewById(R.id.listview2);
        listview3 = (ListView) this.findViewById(R.id.listview3);
        listview4 = (ListView) this.findViewById(R.id.listview4);

        search = (SearchView) findViewById(R.id.search);
        listView.setAdapter(mList);
        listview2.setAdapter(mList1);
        listview3.setAdapter(mList2);
        listview4.setAdapter(mList3);

        UserId = userid.getText().toString();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                Project project = (Project) mList.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ProjectDetails.class);
                intent.putExtra(KEY_PROJECTNAME, project.getProjectname());
                intent.putExtra(KEY_CLIENTNAME, project.getClientname());
                intent.putExtra(KEY_TEAMNAME, project.getTeamleadername());
                intent.putExtra(KEY_START, project.getStartdate());
                intent.putExtra(KEY_END, project.getEnddate());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                Project project = (Project) mList1.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ProjectDetails.class);
                intent.putExtra(KEY_PROJECTNAME, project.getProjectname());
                intent.putExtra(KEY_CLIENTNAME, project.getClientname());
                intent.putExtra(KEY_TEAMNAME, project.getTeamleadername());
                intent.putExtra(KEY_START, project.getStartdate());
                intent.putExtra(KEY_END, project.getEnddate());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                Project project = (Project) mList2.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ProjectDetails.class);
                intent.putExtra(KEY_PROJECTNAME, project.getProjectname());
                intent.putExtra(KEY_CLIENTNAME, project.getClientname());
                intent.putExtra(KEY_TEAMNAME, project.getTeamleadername());
                intent.putExtra(KEY_START, project.getStartdate());
                intent.putExtra(KEY_END, project.getEnddate());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
        listview4.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                Project project = (Project) mList3.getItem(position);

                Intent intent = new Intent(getApplicationContext(), ProjectDetails.class);
                intent.putExtra(KEY_PROJECTNAME, project.getProjectname());
                intent.putExtra(KEY_CLIENTNAME, project.getClientname());
                intent.putExtra(KEY_TEAMNAME, project.getTeamleadername());
                intent.putExtra(KEY_START, project.getStartdate());
                intent.putExtra(KEY_END, project.getEnddate());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });


        ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setVisibility(View.GONE);
                listview2.setVisibility(View.VISIBLE);
                listview3.setVisibility(View.GONE);
                listview4.setVisibility(View.GONE);
                name.setText("Ongoing Projects- "+listview2.getAdapter().getCount());
            }
        });
        completedProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.GONE);
                listview2.setVisibility(View.GONE);
                listview3.setVisibility(View.VISIBLE);
                listview4.setVisibility(View.GONE);
                name.setText("Completed Projects- "+listview3.getAdapter().getCount());
            }
        });
        futureProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.GONE);
                listview2.setVisibility(View.GONE);
                listview3.setVisibility(View.GONE);
                listview4.setVisibility(View.VISIBLE);
                name.setText("Future Projects- "+listview4.getAdapter().getCount());
            }
        });
        totalProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
                listview2.setVisibility(View.GONE);
                listview3.setVisibility(View.GONE);
                listview4.setVisibility(View.GONE);
                name.setText("Total Projects- "+listView.getAdapter().getCount());
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpProjects.this,AdminDashboard.class);
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
                Intent intent = new Intent(EmpProjects.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);


        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Employee Projects");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Employee Projects");
        }

    }

    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            // Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

            // A new comment has been added, add it to the displayed list
            Comment comment = dataSnapshot.getValue(Comment.class);

            // ...
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            //Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
            Comment newComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            //Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            //Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
            Comment movedComment = dataSnapshot.getValue(Comment.class);
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            //Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            Toast.makeText(EmpProjects.this, "Failed to load comments.",
                    Toast.LENGTH_SHORT).show();
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
        if(id == R.id.query)
        {
            UserId = userid.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(EmpProjects.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
