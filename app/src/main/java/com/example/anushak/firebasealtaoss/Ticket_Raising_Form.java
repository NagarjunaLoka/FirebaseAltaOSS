package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class Ticket_Raising_Form extends AppCompatActivity {


    ListView listView;
    TicketAdapter mList;
    SearchView searchView;
    DatabaseReference dbArtists;

    EditText name,empid,email,title,subject,discription,emaildept;
    Spinner sp1,sp2;
    TextView empidlist,status,userid,usertype,text;
    String Empid,id,UserId,UserType,Text;
    Button submit;
    android.support.v7.widget.Toolbar toolbar;
    FirebaseDatabase fdatabse;
    DatabaseReference dref,dref1,databaseReference;
    HorizontalScrollView hsv1,hsv2,hsv3;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_ticket__raising__form);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.name);
        empid = findViewById(R.id.empid);
        email = findViewById(R.id.email);
        title = findViewById(R.id.title);
        subject = findViewById(R.id.subject);
        discription = findViewById(R.id.discription);
        sp1 = findViewById(R.id.priority);
        sp2 = findViewById(R.id.assign);
        empidlist = findViewById(R.id.empidlist);
        status = findViewById(R.id.status);
        submit = findViewById(R.id.submit);
        emaildept = findViewById(R.id.emailid);
        userid = findViewById(R.id.userid);
        usertype = findViewById(R.id.usertype);
        text = findViewById(R.id.text);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);
        list2 = (TextView)findViewById(R.id.list2);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);


        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        userid.setText(UserId);
        usertype.setText(UserType);

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        Query query = databaseReference.child(UserId);

        fdatabse = FirebaseDatabase.getInstance();
        dref = fdatabse.getReference("Tickets");

        dref1 = fdatabse.getReference("Users");

        listView = (ListView) findViewById(R.id.listview);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        empidlist.setText("");
                        emaildept.setText("");
                        m1();
                        break;
                    case 2:
                        empidlist.setText("");
                        emaildept.setText("");
                        m2();
                        break;
                    case 3:
                        empidlist.setText("");
                        emaildept.setText("");
                        m3();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // set editbox invivible

            }
        });

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

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
                Intent intent = new Intent(Ticket_Raising_Form.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_Raising_Form.this,Ticketing_Dashboard.class);
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
                Intent intent = new Intent(Ticket_Raising_Form.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_Raising_Form.this,Ticketing_Dashboard.class);
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
                Intent intent = new Intent(Ticket_Raising_Form.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_Raising_Form.this,Ticketing_Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Ticketing Dashboard - Ticket Raising Form");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Ticketing Dashboard - Ticket Raising Form");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Ticketing Dashboard - Ticket Raising Form");
        }

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Fname = dataSnapshot.child("name").getValue().toString();
                String Mname = dataSnapshot.child("middlename").getValue().toString();
                String Lname = dataSnapshot.child("lastname").getValue().toString();
                String Empid = dataSnapshot.child("empid").getValue().toString();
                String PersonalEmail = dataSnapshot.child("personalemail").getValue().toString();

                name.setText(Fname+Mname+"."+Lname);
                empid.setText(Empid);
                email.setText(PersonalEmail);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User ListViewClickData=(User)parent.getItemAtPosition(position);
                Toast.makeText(Ticket_Raising_Form.this, ListViewClickData.getName(), Toast.LENGTH_SHORT).show();
                empidlist.setText(ListViewClickData.getEmpid());
                emaildept.setText(ListViewClickData.getEmail());
            }
        });

        //1. SELECT * FROM Artists
        dbArtists = FirebaseDatabase.getInstance().getReference("Users");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUpdate();
            }
        });

    }

    private void addUpdate(){
        String Name = name.getText().toString();
        String Empid = empid.getText().toString();
        String Email = email.getText().toString();
        String Title = title.getText().toString();
        String Subject = subject.getText().toString();
        String Discription = discription.getText().toString();
        String Sp1 = sp1.getSelectedItem().toString();
        String Sp2 = sp2.getSelectedItem().toString();
        String Empidlist = empidlist.getText().toString();
        String Status = status.getText().toString();


        if(!TextUtils.isEmpty(Name)){

            String id = dref.push().getKey();
            Artist artist = new Artist(id,Name,Empid,Email,Title,Subject,Discription,Sp1,Sp2,Status,Empidlist);

            dref.child(id).setValue(artist);

            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public  void m1(){

        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("departmenttype")
                .equalTo("hardware");

        mList = new TicketAdapter(query, this, R.layout.department_view);
        listView.setAdapter(mList);
    }

    public  void m2(){

        Query query =dref1
                .orderByChild("departmenttype")
                .equalTo("software");

        mList = new TicketAdapter(query, this, R.layout.department_view);
        listView.setAdapter(mList);

    }

    public  void m3(){

        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("departmenttype")
                .equalTo("networking");

        mList = new TicketAdapter(query, this, R.layout.department_view);
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
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(Ticket_Raising_Form.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
