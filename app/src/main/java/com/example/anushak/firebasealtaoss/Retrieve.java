package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Retrieve extends AppCompatActivity {

    TextView type,eid;
    ListView listView;
    SearchView search;
    private DatabaseReference mDatabase;
    ListAdapter2 mList;

    String EMPID,TYPE,UserId,UserType,Text,Qtype;
    TextView userid,usertype,text;
    android.support.v7.widget.Toolbar toolbar;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1,qtype,qtype1,retype,retype1;
    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_retrieve);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        search=findViewById(R.id.searchupdates);

        userid = (TextView) findViewById(R.id.userid);
        usertype = (TextView) findViewById(R.id.usertype);
        text = (TextView) findViewById(R.id.text);
        type = (TextView) findViewById(R.id.ttype);
        eid = (TextView) findViewById(R.id.eid);
        iv = (ImageView) findViewById(R.id.iv);
        iv1 = (ImageView) findViewById(R.id.iv1);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        list = (TextView) findViewById(R.id.list);
        list1 = (TextView) findViewById(R.id.list1);
        hsv1 = (HorizontalScrollView) findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView) findViewById(R.id.hsv2);
        qtype = (TextView) findViewById(R.id.qtype);
        qtype1 = (TextView) findViewById(R.id.qtype1);
        retype = (TextView) findViewById(R.id.retype);
        retype1 = (TextView) findViewById(R.id.retype1);

        EMPID = getIntent().getStringExtra("EMPID");
        TYPE = getIntent().getStringExtra("TYPE");
        UserId = getIntent().getStringExtra("id");
        UserType = getIntent().getStringExtra("usertype");

        userid.setText(UserId);
        usertype.setText(UserType);
        type.setText(TYPE);
        eid.setText(EMPID);
        retype.setText("  >>  " + TYPE);
        retype1.setText("  >>  " + TYPE);

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
                Intent intent = new Intent(Retrieve.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Retrieve.this, EmpPayroll.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        qtype.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Retrieve.this, PayrollDocuments.class);
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
                Intent intent = new Intent(Retrieve.this, HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Retrieve.this, EmpPayroll.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        qtype1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Retrieve.this, PayrollDocuments.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin") ? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr") ? View.VISIBLE : View.GONE);

        if (UserType.equals("hr")) {
            qtype1.setText("  Re-Imbursement");
            Qtype = qtype1.getText().toString();
            text.setText("HR Dashboard - Employees List - " + Qtype + " - " + TYPE);
        } else if (UserType.equals("admin")) {
            qtype.setText("  Payroll");
            Qtype = qtype.getText().toString();
            text.setText("Admin Dashboard - Employees List - " + Qtype + " - " + TYPE);
        }

        mDatabase = FirebaseDatabase.getInstance().getReference
                ("Reimbursement").child(TYPE);
        Query query = mDatabase.orderByChild("emid").equalTo(EMPID);

        mList = new ListAdapter2(query, this, R.layout.update_list2);
        listView = (ListView) this.findViewById(R.id.listView);
        listView.setAdapter(mList);
        search = (SearchView) findViewById(R.id.searchupdates);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reimbursement ListViewClickData = (Reimbursement) parent.getItemAtPosition(position);
                Toast.makeText(Retrieve.this, ListViewClickData.getEmid(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Retrieve.this, PayDocuments1.class);
                intent.putExtra("Eid", ListViewClickData.getEmid());
                intent.putExtra("Type", ListViewClickData.getSpinner());
                intent.putExtra("Uploaded", ListViewClickData.getUploaded().toString());
                intent.putExtra("URL", ListViewClickData.getUrl().toString());
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
        public void firebaseUserSearch(final String text){
            Query query=mDatabase.orderByChild("emid").startAt(text.toString()).endAt(text+"utf8");
            mList=new ListAdapter2(query,this,R.layout.update_list2);
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
            Intent i = new Intent(Retrieve.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}