package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
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

public class EmpRewards extends AppCompatActivity {

    ListView listView;
    private DatabaseReference mDatabase;
    private EmpAdapter mList;
    android.support.v7.widget.Toolbar toolbar;
    String Text, UserType,  UserId;
    SearchView searchView;

    TextView text, usertype, userId;
    HorizontalScrollView hsv1, hsv2;
    Animation frombottom, fromtop, fromleft, fromright;

    ImageView iv, iv1;
    TextView dashboard, dashboard1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_rewards);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mList = new EmpAdapter(mDatabase, this, R.layout.emp_list);
        listView = (ListView) this.findViewById(R.id.listView);
        listView.setAdapter(mList);

        userId = (TextView) findViewById(R.id.userid);
        text = (TextView) findViewById(R.id.text);
        usertype = (TextView) findViewById(R.id.usertype);
        searchView = (SearchView) findViewById(R.id.search);

        hsv1 = (HorizontalScrollView) findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView) findViewById(R.id.hsv2);

        iv = (ImageView) findViewById(R.id.iv);
        iv1 = (ImageView) findViewById(R.id.iv1);

        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);


        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        userId.setText(UserId);
        usertype.setText(UserType);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        fromleft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        fromright = AnimationUtils.loadAnimation(this, R.anim.fromright);

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
                Intent intent = new Intent(EmpRewards.this, AdminDashboard.class);
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
                Intent intent = new Intent(EmpRewards.this, HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin") ? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr") ? View.VISIBLE : View.GONE);


        if (UserType.equals("hr")) {
            text.setText("HR Dashboard - Employee List(Rewards)");
        } else if (UserType.equals("admin")) {
            text.setText("Admin Dashboard - Employee List(Rewards)");
        }

        UserType = usertype.getText().toString();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                User user = (User) mList.getItem(position);
                Intent intent = new Intent(getApplicationContext(), Rewards.class);
                intent.putExtra("empid", user.getEmpid());
                intent.putExtra("name", user.getName());
                intent.putExtra("middlename", user.getMiddlename());
                intent.putExtra("lastname", user.getLastname());
                intent.putExtra("department", user.getDepartment());
                intent.putExtra("usertype", UserType);
                intent.putExtra("userId", user.getUserId());
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public  boolean onQueryTextChange(String text) {

                text=searchView.getQuery().toString();

                firebaseUserSearch(text);
                //mList.getFilter().filter(text.toString());
                return true;
            }
        });

    }
    public void firebaseUserSearch(final String text){
        Query query=mDatabase.orderByChild("empid").startAt(text.toString()).endAt(text+"utf8");
        mList=new EmpAdapter(query,this,R.layout.emp_list);
        listView.setAdapter(mList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.query) {
            UserId = userId.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(EmpRewards.this, QueryForm.class);
            i.putExtra("userId", UserId);
            i.putExtra("message", Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}