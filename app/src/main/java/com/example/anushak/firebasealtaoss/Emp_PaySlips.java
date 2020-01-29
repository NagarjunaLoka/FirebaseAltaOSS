package com.example.anushak.firebasealtaoss;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
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

public class Emp_PaySlips extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PaySlips_Adapter adapter;
    private List<Salary> artistList;
    DatabaseReference dbArtists;

    android.support.v7.widget.Toolbar toolbar;
    SearchView searchView;

    TextView empid,text,usertype,userid;
    String Empid,Text,UserType,UserId;

    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,mypay,mypay1,mypay2;

    HorizontalScrollView hsv1,hsv2,hsv3;
    Animation frombottom,fromtop,fromleft,fromright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp__pay_slips);


        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  llScroll = findViewById(R.id.llscroll);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList = new ArrayList<>();
        adapter = new PaySlips_Adapter(this, artistList);
        recyclerView.setAdapter(adapter);

        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        mypay = (TextView)findViewById(R.id.mypay);
        mypay1 = (TextView)findViewById(R.id.mypay1);
        mypay2 = (TextView)findViewById(R.id.mypay2);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);


        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        Empid=getIntent().getStringExtra("empid");

        empid.setText(Empid);
        userid.setText(UserId);
        usertype.setText(UserType);

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
                Intent intent = new Intent(Emp_PaySlips.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emp_PaySlips.this,MyPay.class);
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
                Intent intent = new Intent(Emp_PaySlips.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emp_PaySlips.this,MyPay.class);
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
                Intent intent = new Intent(Emp_PaySlips.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emp_PaySlips.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);


        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - My Pay - All Payslips");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - My Pay - All Payslips");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - My Pay - All Payslips");
        }








        //1. SELECT * FROM Artists
        // dbArtists = FirebaseDatabase.getInstance().getReference("Tickets");

        //2. SELECT * FROM Artists WHERE id = "-LAJ7xKNj4UdBjaYr8Ju"
        Query query = FirebaseDatabase.getInstance().getReference("Salary Details")
                .orderByChild("empid")
                .equalTo("A1001");


        query.addListenerForSingleValueEvent(valueEventListener);






        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {

                   // Toast.makeText(Emp_PaySlips.this, "connected", Toast.LENGTH_LONG).show();
                } else {

                   // Toast.makeText(Emp_PaySlips.this,getDrawable(R.dr), Toast.LENGTH_LONG).show();

                    Toast aa = Toast.makeText(getBaseContext(), "NO INTERNET CONNECTION",Toast.LENGTH_SHORT);
                    ImageView cc = new ImageView(getBaseContext());
                    cc.setImageResource(R.drawable.no_internet);
                    aa.setView(cc);
                    aa.show();


                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

               // System.err.println("Listener was cancelled");
              Toast.makeText(getBaseContext(), "SOMETHING WENT WRONG ",Toast.LENGTH_SHORT);
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Salary salary = snapshot.getValue(Salary.class);
                    artistList.add(salary);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);
        getMenuInflater().inflate(R.menu.queryform,menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                final  List<Salary> filtermodelist=filter(artistList,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });
        return true;
    }
    private List<Salary> filter(List<Salary> pl,String query)
    {
        query=query.toLowerCase();
        final List<Salary> filteredModeList=new ArrayList<>();
        for (Salary model:pl)
        {
            final String text=model.getMonth_and_year().toLowerCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }
    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.query) {
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(Emp_PaySlips.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



}
