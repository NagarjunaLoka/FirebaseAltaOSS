package com.example.anushak.firebasealtaoss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class VisDirectory extends AppCompatActivity {

    ListView listView;
    private DatabaseReference mDatabase;
    private VisAdapter mList;

    TextView empid,text,userid,usertype;
    String EMPID,Text,UserId,UserType;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list;
    android.support.v7.widget.Toolbar toolbar;
    Animation fromtop;
    HorizontalScrollView hsv1,hsv2,hsv3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_vis_directory);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        list = (TextView)findViewById(R.id.list);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        EMPID=getIntent().getStringExtra("empid");
        usertype.setText(UserType);
        userid.setText(UserId);
        empid.setText(EMPID);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        hsv1.setAnimation(fromtop);
        hsv2.setAnimation(fromtop);
        hsv3.setAnimation(fromtop);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisDirectory.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisDirectory.this, VisitorDashboard.class);
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
                Intent intent = new Intent(VisDirectory.this, HrDashboard.class);
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
                Intent intent = new Intent(VisDirectory.this, EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin") ? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr") ? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("employee") ? View.VISIBLE : View.GONE);

        if (UserType.equals("employee")) {
            text.setText("Employee Dashboard - My Visitors");
        } else if (UserType.equals("hr")) {
            text.setText("HR Dashboard - My Visitors");
        } else if (UserType.equals("admin")) {
            text.setText("Admin Dashboard - Visitor Dashboard - My Visitor");
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("VisitorDetails");
        Query query = mDatabase.orderByChild("empid").equalTo(EMPID);

        mList = new VisAdapter(query, this, R.layout.vis_list);
        listView = (ListView) this.findViewById(R.id.listView);
        listView.setAdapter(mList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                VisUser visUser = (VisUser) mList.getItem(position);
                Intent intent = new Intent(getApplicationContext(), VisitorDetails.class);
                intent.putExtra("fname", visUser.getFname());
                intent.putExtra("lname", visUser.getLname());
                intent.putExtra("email", visUser.getEmail());
                intent.putExtra("phone", visUser.getPhone());
                intent.putExtra("contactperson", visUser.getContactperson());
                intent.putExtra("empid", visUser.getEmpid());
                intent.putExtra("companyname", visUser.getCompanyname());
                intent.putExtra("companybranch", visUser.getCompanybranch());
                intent.putExtra("reasonforvisit", visUser.getReasonforvisit());
                intent.putExtra("dateofvisit", visUser.getDateofvisit());
                intent.putExtra("personalid", visUser.getPersonalid());
                intent.putExtra("typeofvisit", visUser.getTypeofvisit());
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
            Intent intent = new Intent(VisDirectory.this, Addvisitor.class);
            intent.putExtra("userId",UserId);
            intent.putExtra("usertype",UserType);
            startActivity(intent);
        }
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(VisDirectory.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
