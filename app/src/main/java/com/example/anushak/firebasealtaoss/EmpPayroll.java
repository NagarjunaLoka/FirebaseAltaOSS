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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Locale;

public class EmpPayroll extends AppCompatActivity {

    ListView listView;
    SearchView search;
    private DatabaseReference mDatabase;
    EmpAdapter mList;
    TextView userid, usertype, text;
    String UserId, UserType, Text;
    HorizontalScrollView hsv1, hsv2;
    ImageView iv, iv1;
    TextView dashboard, dashboard1;
    Animation fromtop;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_payroll);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userid = (TextView) findViewById(R.id.userid);
        usertype = (TextView) findViewById(R.id.usertype);
        text = (TextView) findViewById(R.id.text);
        hsv1 = (HorizontalScrollView) findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView) findViewById(R.id.hsv2);
        iv = (ImageView) findViewById(R.id.iv);
        iv1 = (ImageView) findViewById(R.id.iv1);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        usertype.setText(UserType);
        userid.setText(UserId);

        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

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
                Intent intent = new Intent(EmpPayroll.this, AdminDashboard.class);
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
                Intent intent = new Intent(EmpPayroll.this, HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin") ? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr") ? View.VISIBLE : View.GONE);


        if (UserType.equals("hr")) {
            text.setText("HR Dashboard - Employees List(Payroll)");
        } else if (UserType.equals("admin")) {
            text.setText("Admin Dashboard - Employees List(Payroll)");
        }


        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        Query query = mDatabase.orderByChild("empid");
        mList = new EmpAdapter(query, this, R.layout.emp_list);
        listView = (ListView) this.findViewById(R.id.list);
        listView.setAdapter(mList);
        search = (SearchView) findViewById(R.id.searchupdates);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User ListViewClickData = (User) parent.getItemAtPosition(position);
                Toast.makeText(EmpPayroll.this, ListViewClickData.getEmpid(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EmpPayroll.this, PayrollDocuments.class);
                intent.putExtra("EmpId", ListViewClickData.getEmpid());
                intent.putExtra("name", ListViewClickData.getName());
                intent.putExtra("middlename", ListViewClickData.getMiddlename());
                intent.putExtra("lastname", ListViewClickData.getLastname());
                intent.putExtra("id", ListViewClickData.getUserId());
                intent.putExtra("userId", UserId);
                intent.putExtra("usertype", UserType);
                startActivity(intent);
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                text = search.getQuery().toString();

                firebaseUserSearch(text);
                //mList.getFilter().filter(text.toString());
                return true;
            }
        });

    }

    public void firebaseUserSearch(final String text) {
        Query query = mDatabase.orderByChild("empid").startAt(text.toUpperCase()).endAt(text.toLowerCase()+"utf8");
        mList = new EmpAdapter(query, this, R.layout.emp_list);

        listView.setAdapter(mList);

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
            UserId = userid.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(EmpPayroll.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}

