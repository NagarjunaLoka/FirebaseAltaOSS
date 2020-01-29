package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.util.ArrayList;

public class QueryDashboard extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ListView listView,listView1,listView2,listView3,listView4;
    private DatabaseReference mDatabase;
    private QueryAdapter mList,mList1,mList2,mList3,mList4;
    TextView title,text,userid,usertype;
    String Text,UserId,UserType;

    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;

    ImageView iv,iv1;
    TextView dashboard,dashboard1;

   SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_query_dashboard);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView)findViewById(R.id.title);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        searchView=findViewById(R.id.search);

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

        mDatabase = FirebaseDatabase.getInstance().getReference("Queries");
        Query query = mDatabase.orderByChild("status").equalTo("Query Updated");
        Query query1 = mDatabase.orderByChild("status").equalTo("Pending");
        Query query2 = mDatabase.orderByChild("status").equalTo("Resolved");
        Query query3 = mDatabase.orderByChild("status").equalTo("UnResolved");
        Query query4 = mDatabase.orderByChild("status").equalTo("Unable to Resolve");

        mList = new QueryAdapter(query, this, R.layout.query_layout);
        mList1 = new QueryAdapter(query1, this, R.layout.query_layout);
        mList2 = new QueryAdapter(query2, this, R.layout.query_layout);
        mList3 = new QueryAdapter(query3, this, R.layout.query_layout);
        mList4 = new QueryAdapter(query4, this, R.layout.query_layout);

        listView = (ListView) this.findViewById(R.id.listView);
        listView1 = (ListView) this.findViewById(R.id.listView1);
        listView2= (ListView) this.findViewById(R.id.listView2);
        listView3= (ListView) this.findViewById(R.id.listView3);
        listView4= (ListView) this.findViewById(R.id.listView4);

        listView.setAdapter(mList);
        listView1.setAdapter(mList1);
        listView2.setAdapter(mList2);
        listView3.setAdapter(mList3);
        listView4.setAdapter(mList4);

        UserId = userid.getText().toString();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Equery ListViewClickData=(Equery) parent.getItemAtPosition(position);
                Toast.makeText(QueryDashboard.this, ListViewClickData.getEmpid(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QueryDashboard.this,QueryUpdate.class);
                intent.putExtra("empid",ListViewClickData.getEmpid());
                intent.putExtra("email",ListViewClickData.getEmail());
                intent.putExtra("subject",ListViewClickData.getSubject());
                intent.putExtra("qmessage",ListViewClickData.getQmessage().toString());
                intent.putExtra("status",ListViewClickData.getStatus().toString());
                intent.putExtra("queryid",ListViewClickData.getQueryid().toString());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Equery ListViewClickData=(Equery) parent.getItemAtPosition(position);
                Toast.makeText(QueryDashboard.this, ListViewClickData.getEmpid(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QueryDashboard.this,QueryUpdate.class);
                intent.putExtra("empid",ListViewClickData.getEmpid());
                intent.putExtra("email",ListViewClickData.getEmail());
                intent.putExtra("subject",ListViewClickData.getSubject());
                intent.putExtra("qmessage",ListViewClickData.getQmessage().toString());
                intent.putExtra("status",ListViewClickData.getStatus().toString());
                intent.putExtra("queryid",ListViewClickData.getQueryid().toString());
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
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
                Intent intent = new Intent(QueryDashboard.this,AdminDashboard.class);
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
                Intent intent = new Intent(QueryDashboard.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);


        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Query Dashboard");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Query Dashboard");
        }



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                text = searchView.getQuery().toString();

                firebaseUserSearch(text);


                //mList.getFilter().filter(text.toString());
                return true;
            }
        });

    }

    public void firebaseUserSearch(final String text) {
        Query query = mDatabase.orderByChild("empid").startAt(text).endAt(text + "utf8");
        mList = new QueryAdapter(query, this, R.layout.query_layout);

        listView.setAdapter(mList);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        getMenuInflater().inflate(R.menu.totalqueries,menu);
        getMenuInflater().inflate(R.menu.filtermenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        UserId = userid.getText().toString();
        Text = text.getText().toString();
        if(id == R.id.query)
        {
            Intent i = new Intent(QueryDashboard.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }
        if(id == R.id.pending)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.VISIBLE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.GONE);
            title.setText("Pending Queries- "+listView1.getAdapter().getCount());


        }
        if(id == R.id.resolved)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.VISIBLE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.GONE);
            title.setText("Resolved Queries- "+listView2.getAdapter().getCount());
        }
        if(id == R.id.unresolved)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.VISIBLE);
            listView4.setVisibility(View.GONE);
            title.setText("UnResolved Queries- "+listView3.getAdapter().getCount());
        }
        if(id == R.id.unable)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.VISIBLE);
            title.setText("Unable to Resolve Queries- "+listView4.getAdapter().getCount());
        }
        if(id == R.id.totalquery)
        {
            listView.setVisibility(View.VISIBLE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.GONE);
            title.setText("Total Queries- "+listView.getAdapter().getCount());
        }

        return super.onOptionsItemSelected(item);
    }
}
