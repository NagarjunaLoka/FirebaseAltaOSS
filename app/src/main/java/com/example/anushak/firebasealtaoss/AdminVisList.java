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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdminVisList extends AppCompatActivity {

    ListView listView;
    private DatabaseReference mDatabase;
    private VisAdapter mList;
    TextView dashboard,list;
    ImageView iv;
    TextView text,userid,usertype;
    String Text,UserId,UserType;
    android.support.v7.widget.Toolbar toolbar;


    SearchView search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_admin_vis_list);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        list = (TextView)findViewById(R.id.list);
        search = (SearchView) findViewById(R.id.searchupdates);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisList.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisList.this, VisitorDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        userid.setText(UserId);
        usertype.setText(UserType);
        text.setText("Admin Dashboard - Visitor Dashboard - Visitor List(Search/View) ");

        mDatabase = FirebaseDatabase.getInstance().getReference("VisitorDetails");

        mList = new VisAdapter(mDatabase, this, R.layout.vis_list);
        listView = (ListView) this.findViewById(R.id.listView);
        listView.setAdapter(mList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {
                VisUser visUser = (VisUser) mList.getItem(position);
                Intent intent = new Intent(getApplicationContext(), VisitorDetails.class);
                intent.putExtra("fname", visUser.getFname());
                intent.putExtra("mname", visUser.getMname());
                intent.putExtra("lname", visUser.getLname());
                intent.putExtra("profile", visUser.getProfile());
                intent.putExtra("email", visUser.getEmail());
                intent.putExtra("phone", visUser.getPhone());
                intent.putExtra("contactperson", visUser.getContactperson());
                intent.putExtra("empid", visUser.getEmpid());
                intent.putExtra("companyname", visUser.getCompanyname());
                intent.putExtra("companybranch", visUser.getCompanybranch());
                intent.putExtra("reasonforvisit", visUser.getReasonforvisit());
                intent.putExtra("dateofvisit", visUser.getDateofvisit());
                intent.putExtra("personalid", visUser.getPersonalid());
                intent.putExtra("personal", visUser.getSppersonalid());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
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
            public  boolean onQueryTextChange(String text) {

                text=search.getQuery().toString();

                firebaseUserSearch(text);
                //mList.getFilter().filter(text.toString());
                return true;
            }
        });

    }
    public void firebaseUserSearch(final String text) {
        Query query = mDatabase.orderByChild("fname").startAt(text.toString()).endAt(text + "utf8");
        mList = new VisAdapter(query, this, R.layout.vis_list);
        listView.setAdapter(mList);
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.queryform,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(AdminVisList.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
