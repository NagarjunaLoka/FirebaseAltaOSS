package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Ticketing_Dashboard extends AppCompatActivity {

    CardView newticket,mytickets,tickets_history,my_task,open_tickets,closed_tickets,solved_tickets,pending_tickets;
    DatabaseReference dbArtists;
    private int opencount,closedcount,pendingcount,totalcount,assignedcount,tickets;
    TextView openc,closedc,solvedc,pendingc,totalc,assignedc;
    android.support.v7.widget.Toolbar toolbar;
    TextView userid,usertype,text,empid,ticketid,department;
    String UserId,UserType,Text,Empid,Department;
    HorizontalScrollView hsv1,hsv2,hsv3;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_ticketing__dashboard);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        department=(TextView) findViewById(R.id.department);
        newticket=(CardView)findViewById(R.id.newticket);
        mytickets=(CardView)findViewById(R.id.totaltickets);
        open_tickets =(CardView)findViewById(R.id.open_tickets);
        solved_tickets =(CardView)findViewById(R.id.solved_tickets);
        pending_tickets =(CardView)findViewById(R.id.pending_tickets);
        tickets_history=(CardView)findViewById(R.id.tickets_history);
        my_task=(CardView)findViewById(R.id.my_task);
        closed_tickets=(CardView)findViewById(R.id.closed_tickets);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);
        empid = findViewById(R.id.empid);
        ticketid = (TextView)findViewById(R.id.ticketid);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        openc = findViewById(R.id.openc);
        closedc = findViewById(R.id.closedc);
        solvedc = findViewById(R.id.solvedc);
        pendingc = findViewById(R.id.pendingc);
        totalc = findViewById(R.id.totalc);
        assignedc = findViewById(R.id.assignedc);

        Empid = getIntent().getStringExtra("empid");
        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");

        Department = getIntent().getStringExtra("department");
        department.setText(Department);

        empid.setText(Empid);
        userid.setText(UserId);
        usertype.setText(UserType);

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
                Intent intent = new Intent(Ticketing_Dashboard.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(Ticketing_Dashboard.this,HrDashboard.class);
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
                Intent intent = new Intent(Ticketing_Dashboard.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee")&&(department.getText().toString().equalsIgnoreCase("TicketingTool")))
        {
            text.setText("Employee Dashboard - Ticketing Dashboard");
            tickets_history.setVisibility(View.GONE);
            my_task.setVisibility(View.VISIBLE);
        }
        else if(UserType.equals("hr")&&(department.getText().toString().equalsIgnoreCase("TicketingTool")))
        {
            text.setText("HR Dashboard - Ticketing Dashboard");
            tickets_history.setVisibility(View.GONE);
            my_task.setVisibility(View.VISIBLE);
        }
        else if(UserType.equals("admin")&&(department.getText().toString().equalsIgnoreCase("TicketingTool")))
        {
            text.setText("Admin Dashboard - Ticketing Dashboard");
            my_task.setVisibility(View.VISIBLE);
            tickets_history.setVisibility(View.VISIBLE);
        }
        else if(UserType.equals("employee")&&(department.getText().toString().equalsIgnoreCase("")))
        {
            text.setText("Employee Dashboard - Ticketing Dashboard");
            tickets_history.setVisibility(View.GONE);
            my_task.setVisibility(View.GONE);
        }
        else if(UserType.equals("hr")&&(department.getText().toString().equalsIgnoreCase("")))
        {
            text.setText("HR Dashboard - Ticketing Dashboard");
            tickets_history.setVisibility(View.GONE);
            my_task.setVisibility(View.GONE);
        }





       /* if(department.getText().toString().equalsIgnoreCase("TicketingTool"))
        {
            my_task.setVisibility(View.VISIBLE);
            tickets_history.setVisibility(View.GONE);
        }
        else{
            my_task.setVisibility(View.GONE);
        }*/

        newticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,Ticket_Raising_Form.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                startActivity(i);
            }
        });
        mytickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,My_Tickets.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);
                startActivity(i);
            }
        });

       open_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,Open_Tickets.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);
                startActivity(i);
            }
        });

        closed_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,Closed_Tickets.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);
                startActivity(i);
            }
        });
           my_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,Assigned_Tickets.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);
                startActivity(i);
            }
        });
        tickets_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,Total_Tickets.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);
                startActivity(i);
            }
        });
       pending_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,Pending_Tickets.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);
                startActivity(i);
            }
        });
         solved_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ticketing_Dashboard.this,Solved_Tickets.class);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                i.putExtra("empid",Empid);
                startActivity(i);
            }
        });



        Query query = FirebaseDatabase.getInstance().getReference("Tickets")
                .orderByChild("status")
                .equalTo("open");
        query.addListenerForSingleValueEvent(valueEventListener);

        Query query1 = FirebaseDatabase.getInstance().getReference("Tickets")
                .orderByChild("status")
                .equalTo("solved and close Ticket");
        query1.addListenerForSingleValueEvent(valueEventListener1);

        Query query2 = FirebaseDatabase.getInstance().getReference("Tickets")
                .orderByChild("status")
                .equalTo("pending");
        query2.addListenerForSingleValueEvent(valueEventListener2);


        dbArtists = FirebaseDatabase.getInstance().getReference("Tickets");

        dbArtists.addListenerForSingleValueEvent(valueEventListener3);


        Query query3 = FirebaseDatabase.getInstance().getReference("Tickets")
                .orderByChild("empidlist")
                .equalTo(Empid);

        query3.addListenerForSingleValueEvent(valueEventListener4);

        Query query4 = FirebaseDatabase.getInstance().getReference("Tickets")
                .orderByChild("empid")
                .equalTo(Empid);
        query4.addListenerForSingleValueEvent(valueEventListener5);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    opencount=(int)dataSnapshot.getChildrenCount();

                    openc.setText(Integer.toString(opencount));
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    closedcount=(int)dataSnapshot.getChildrenCount();
                    closedc.setText(Integer.toString(closedcount));
                    solvedc.setText(Integer.toString(closedcount));
                }

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    pendingcount=(int)dataSnapshot.getChildrenCount();
                    pendingc.setText(Integer.toString(pendingcount));
                }

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener3 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    totalcount=(int)dataSnapshot.getChildrenCount();
                    totalc.setText(Integer.toString(totalcount));
                }

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener4 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    assignedcount=(int)dataSnapshot.getChildrenCount();
                    assignedc.setText(Integer.toString(assignedcount));
                }
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener5 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    tickets=(int)dataSnapshot.getChildrenCount();
                    ticketid.setText(Integer.toString(tickets));
                }
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };





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
            Intent i = new Intent(Ticketing_Dashboard.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
