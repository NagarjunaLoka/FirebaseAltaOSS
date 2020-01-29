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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.example.anushak.firebasealtaoss.EmpProjects.KEY_CLIENTNAME;
import static com.example.anushak.firebasealtaoss.EmpProjects.KEY_END;
import static com.example.anushak.firebasealtaoss.EmpProjects.KEY_PROJECTNAME;
import static com.example.anushak.firebasealtaoss.EmpProjects.KEY_START;
import static com.example.anushak.firebasealtaoss.EmpProjects.KEY_TEAMNAME;

public class ProjectDetails extends AppCompatActivity {

    TextView projectname,text1,text2,text3,text4;
    android.support.v7.widget.Toolbar toolbar;

    TextView text,usertype,userid;
    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;
    String Text,UserType,UserId;

    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;

    DatabaseReference mDatabase;
    FirebaseProjectAdapter<User> Sample2;
    ListView listviewfetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_project_details);

        listviewfetch = (ListView) findViewById(R.id.listviewfetch);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);

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

        usertype.setText(UserType);
        userid.setText(UserId);


        String ProjectName = "";
        String ClientName = "";
        String ManagerName = "";
        String StartDate = "";
        String EndDate = "";

        Intent intent = getIntent();
        if (null != intent) {
            ProjectName = intent.getStringExtra(KEY_PROJECTNAME);
            ClientName = intent.getStringExtra(KEY_CLIENTNAME);
            ManagerName = intent.getStringExtra(KEY_TEAMNAME);
            StartDate = intent.getStringExtra(KEY_START);
            EndDate = intent.getStringExtra(KEY_END);
        }

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        projectname = (TextView) findViewById(R.id.projectname);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);

        projectname.setText(ProjectName);
        text1.setText(ClientName);
        text2.setText(ManagerName);
        text3.setText(StartDate);
        text4.setText(EndDate);

        mDatabase= FirebaseDatabase.getInstance().getReference("ProjectPreferences");
        Query query = mDatabase.orderByChild("prefproject").equalTo(ProjectName);

        Sample2=new FirebaseProjectAdapter<User>(query,User.class, R.layout.empid_list,this) {
            private TextView Empid;

            @Override
            protected void populateView(View v, final User model) {
                Empid = (TextView) v.findViewById(R.id.empid);
                Empid.setText(String.valueOf(model.getEmpid().toString()));
            }
        };
        listviewfetch.setAdapter(Sample2);

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
                Intent intent = new Intent(ProjectDetails.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectDetails.this,EmpProjects.class);
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
                Intent intent = new Intent(ProjectDetails.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectDetails.this,EmpProjects.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Projects List - "+ProjectName);
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Projects List - "+ProjectName);
        }
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
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(ProjectDetails.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
