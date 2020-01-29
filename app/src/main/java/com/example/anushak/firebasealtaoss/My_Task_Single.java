package com.example.anushak.firebasealtaoss;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class My_Task_Single extends AppCompatActivity {

    Spinner status;

    TextView name, empid, email, title, date, subject, discription, priority, task_assign,officialemail,department,id;
    String Name, Empid, Email, Title, Subject, Discription, Priority, ID,Department;
    android.support.v7.widget.Toolbar toolbar;
    TextView text,userid,usertype;
    String Text,UserId,UserType;
    HorizontalScrollView hsv1,hsv2,hsv3;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2,ticket,ticket1,ticket2;
    Animation fromtop;

    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my__task__single);

        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        String value = sharedpreferences.getString("person_name", "defaultValue");
        String value1 = sharedpreferences.getString("person_name1", "defaultValue");


        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        status = (Spinner) findViewById(R.id.status);
        name = (TextView) findViewById(R.id.name);
        empid = (TextView) findViewById(R.id.empid);
        email = (TextView) findViewById(R.id.phonenum);
        title = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        subject = (TextView) findViewById(R.id.subject);
        discription = (TextView) findViewById(R.id.discription);
        priority = (TextView) findViewById(R.id.priority);
        officialemail=(TextView)findViewById(R.id.officialemail);
        task_assign = (TextView) findViewById(R.id.empidlist);
        id=findViewById(R.id.id);
        department=(TextView)findViewById(R.id.department);
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
        list1 = (TextView)findViewById(R.id.list1);
        list2 = (TextView)findViewById(R.id.list2);
        ticket = (TextView)findViewById(R.id.ticket);
        ticket1 = (TextView)findViewById(R.id.ticket1);
        ticket2 = (TextView)findViewById(R.id.ticket2);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

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
                Intent intent = new Intent(My_Task_Single.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Task_Single.this,Ticketing_Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ticket.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Task_Single.this,Assigned_Tickets.class);
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
                Intent intent = new Intent(My_Task_Single.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Task_Single.this,Ticketing_Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ticket1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Task_Single.this,Assigned_Tickets.class);
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
                Intent intent = new Intent(My_Task_Single.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Task_Single.this,Ticketing_Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        ticket2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Task_Single.this,Assigned_Tickets.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        if(value.equalsIgnoreCase("Admin")||value.equalsIgnoreCase("HR")||(value.equalsIgnoreCase("Employee")))
        {
            usertype.setText(value);
        }

        if(!value1.equalsIgnoreCase(""))
        {
            userid.setText(value1);
        }



        hsv1.setVisibility(usertype.getText().toString().equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(usertype.getText().toString().equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(usertype.getText().toString().equals("admin")? View.VISIBLE : View.GONE);

        if(usertype.getText().toString().equals("employee"))
        {
            text.setText("Employee Dashboard - Ticketing Dashboard - Assigned Tickets - Status Form");
        }
        else if(usertype.getText().toString().equals("hr"))
        {
            text.setText("HR Dashboard - Ticketing Dashboard - Assigned Tickets - Status Form");
        }
        else if(usertype.getText().toString().equals("admin"))
        {
            text.setText("Admin Dashboard - Ticketing Dashboard - Assigned Tickets - Status Form");
        }

        Department=getIntent().getStringExtra("dept");
        Name = getIntent().getStringExtra("name");
        Empid = getIntent().getStringExtra("empid");
        Email = getIntent().getStringExtra("email");
        Title = getIntent().getStringExtra("title");
        Subject = getIntent().getStringExtra("subject");
        Discription = getIntent().getStringExtra("discription");
        Priority = getIntent().getStringExtra("priority");
        ID = getIntent().getStringExtra("id");

        name.setText(Name);
        empid.setText(Empid);
        email.setText(Email);
        title.setText(Title);
        department.setText(Department);
        subject.setText(Subject);
        discription.setText(Discription);
        priority.setText(Priority);
        id.setText(ID);

    }




        public void submit(View view){

        String id1 = id.getText().toString();
        String empid1 = empid.getText().toString();
        String status1 = status.getSelectedItem().toString();

        updateuser(id1, empid1, status1);

    }

    private boolean updateuser(String id, String empid,String status) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Tickets").child(id).child("status");

        //updating users
        dR.setValue(status);
        Toast.makeText(getApplicationContext(), "Ticket Updated", Toast.LENGTH_LONG).show();
        return true;
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
            Intent i = new Intent(My_Task_Single.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
