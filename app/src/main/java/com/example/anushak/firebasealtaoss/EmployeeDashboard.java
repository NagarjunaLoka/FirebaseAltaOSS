package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

public class EmployeeDashboard extends AppCompatActivity {

    CardView profile,newproject,message,mypay,attendance,ourupdates,myproject,calendar,careers,ticketing,visitors;
    TextView tvFirstName,tvMiddleName,tvLastName,Empid,usertype,userid;
    String EMPID,UserType,UserId,Firstname,Middlename,Lastname,EmailAddress,Image;
    DatabaseReference databaseReference;

    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    private FirebaseAuth Auth;

    ImageView logo;
    TextView Name,Email,EmpId,department;
    ImageView navprofile;

    TextView profilepref,projectpref,messagepref,mypaypref,payslipspref,reimbursementuploadpref,attendancepref,ourupdatespref;
    TextView myprojectspref,careerspref;

    String Profilepref,Projectpref,Messagepref,Mypaypref,Payslipspref,Reimbursementpref,Attendancepref,Ourupdatespref;
    String Myprojectspref,Careerspref;

    User user;
    FirebaseDatabase fdatabse;
    DatabaseReference dref;

    private String SUBSCRIBE_TO = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_employee_dashboard);

        Auth = FirebaseAuth.getInstance();

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        View navView =  navigationView.inflateHeaderView(R.layout.header);
        logo = (ImageView)toolbar.findViewById(R.id.logo);
        Name = (TextView) navView.findViewById(R.id.adminname);
        Email = (TextView) navView.findViewById(R.id.adminemail);
        EmpId = (TextView) navView.findViewById(R.id.adminempid);
        navprofile=(ImageView)navView.findViewById(R.id.navprofile);

        profile = (CardView)findViewById(R.id.profile);
        newproject = (CardView)findViewById(R.id.newproject);
        message = (CardView)findViewById(R.id.message);
        mypay = (CardView)findViewById(R.id.mypay);
        attendance = (CardView)findViewById(R.id.attendance);
        ourupdates = (CardView)findViewById(R.id.ourupdates);
        myproject = (CardView)findViewById(R.id.myproject);
        calendar = (CardView)findViewById(R.id.calendar);
        careers = (CardView)findViewById(R.id.careers);
        ticketing = (CardView)findViewById(R.id.ticketing);
        visitors =(CardView)findViewById(R.id.visitors);

        userid = (TextView)findViewById(R.id.userid);
        Empid = (TextView) findViewById(R.id.Empid);
        tvFirstName = (TextView)findViewById(R.id.tvFirstName);
        tvMiddleName = (TextView)findViewById(R.id.tvMiddleName);
        tvLastName = (TextView)findViewById(R.id.tvLastName);
        usertype = (TextView)findViewById(R.id.usertype);

        profilepref = (TextView)findViewById(R.id.profilepref);
        projectpref = (TextView)findViewById(R.id.projectpref);
        messagepref = (TextView)findViewById(R.id.messagepref);
        mypaypref = (TextView)findViewById(R.id.mypaypref);
        payslipspref = (TextView)findViewById(R.id.payslipspref);
        reimbursementuploadpref = (TextView)findViewById(R.id.reimbursementuploadpref);
        attendancepref = (TextView)findViewById(R.id.attendancepref);
        ourupdatespref = (TextView)findViewById(R.id.ourupdatespref);
        myprojectspref = (TextView)findViewById(R.id.myprojectspref);
        careerspref = (TextView)findViewById(R.id.careerspref);

        department = findViewById(R.id.department);

        EMPID = getIntent().getStringExtra("empid");

        Empid.setText(EMPID);
        usertype.setText("employee");

        SUBSCRIBE_TO = EMPID;

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(EmployeeDashboard.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken", newToken);
                FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                Log.i("FirebaseInsatnceId", "onTokenRefresh completed with token: " + newToken);

            }
        });

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Firstname = dataSnapshot.child("name").getValue().toString();
                 Middlename = dataSnapshot.child("middlename").getValue().toString();
                 Lastname = dataSnapshot.child("lastname").getValue().toString();
                 EmailAddress = dataSnapshot.child("officialemail").getValue().toString();
                 Image=dataSnapshot.child("imageURL").getValue().toString();
                 UserId = dataSnapshot.child("userId").getValue().toString();
                 Picasso.with(EmployeeDashboard.this).load(Image).into(navprofile);
                 //Picasso.with(EmployeeDashboard.this).load(Image).into(profileImageView);

                 tvFirstName.setText(Firstname);
                 tvMiddleName.setText(Middlename);
                 tvLastName.setText(Lastname);

                 userid.setText(UserId);
                 Name.setText(Firstname+Middlename+"."+Lastname);
                 Email.setText(EmailAddress);
                 EmpId.setText(EMPID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Preferences
        EMPID = Empid.getText().toString();
        dref = FirebaseDatabase.getInstance().getReference("Preferences");
        Query lastQuery = dref.child(EMPID);

        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Profile = dataSnapshot.child("profile").getValue().toString();
                String Project = dataSnapshot.child("project").getValue().toString();
                String Message = dataSnapshot.child("message").getValue().toString();
                String Mypay = dataSnapshot.child("mypay").getValue().toString();
                String Payslips = dataSnapshot.child("payslips").getValue().toString();
                String Reimbursementupload = dataSnapshot.child("reimbursementupload").getValue().toString();
                String Attendance = dataSnapshot.child("attendance").getValue().toString();
                String Ourupdates = dataSnapshot.child("ourupdates").getValue().toString();
                String Myprojects = dataSnapshot.child("myprojects").getValue().toString();
                String Careers = dataSnapshot.child("careers").getValue().toString();

                profilepref.setText(Profile);
                projectpref.setText(Project);
                messagepref.setText(Message);
                mypaypref.setText(Mypay);
                payslipspref.setText(Payslips);
                reimbursementuploadpref.setText(Reimbursementupload);
                attendancepref.setText(Attendance);
                ourupdatespref.setText(Ourupdates);
                myprojectspref.setText(Myprojects);
                careerspref.setText(Careers);

                UserType = usertype.getText().toString();
                UserId = userid.getText().toString();
                ///Attendance
               /* attendance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();
                        Intent intent = new Intent(EmployeeDashboard.this,Attendance_Activity.class);
                        intent.putExtra("empid", Empid.getText().toString());
                        intent.putExtra("officialemail",EmailAddress);
                        intent.putExtra("name",tvFirstName.getText().toString());
                        intent.putExtra("imageURL",Image);
                        intent.putExtra("usertype",UserType);
                        intent.putExtra("userId",UserId);
                        startActivity(intent);
                    }
                });*/

               //Attendance
               Attendancepref = attendancepref.getText().toString();
               if(Attendancepref.equalsIgnoreCase("YES")||(Attendancepref.equalsIgnoreCase("EDIT"))) {
                   attendance.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(EmployeeDashboard.this, AttendanceNew.class);
                           intent.putExtra("empid", Empid.getText().toString());
                           intent.putExtra("usertype", UserType);
                           intent.putExtra("userId", UserId);
                           startActivity(intent);
                       }
                   });
               }else if (Attendancepref.equalsIgnoreCase("NO")){
                   attendance.setVisibility(View.GONE);
               }

                //Profile
                Profilepref = profilepref.getText().toString();
                if (Profilepref.equalsIgnoreCase("YES")) {
                    profile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EmployeeDashboard.this,MyProfile.class);
                            intent.putExtra("empid",EMPID);
                            intent.putExtra("usertype",UserType);
                            startActivity(intent);
                        }
                    });
                } else if (Profilepref.equalsIgnoreCase("NO")) {
                    profile.setVisibility(View.GONE);
                }
                else if (Profilepref.equals("EDIT")) {
                    profile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EmployeeDashboard.this,EditProfile.class);
                            intent.putExtra("empid",EMPID);
                            intent.putExtra("usertype",UserType);
                            startActivity(intent);
                        }
                    });
                }

                //Project
                Projectpref = projectpref.getText().toString();
                if (Projectpref.equalsIgnoreCase("YES")||(Projectpref.equalsIgnoreCase("EDIT"))) {
                    newproject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EmployeeDashboard.this,NewProject.class);
                            intent.putExtra("empid",EMPID);
                            intent.putExtra("usertype",UserType);
                            intent.putExtra("userId",UserId);
                            startActivity(intent);
                        }
                    });
                } else if (Projectpref.equalsIgnoreCase("NO")) {
                    newproject.setVisibility(View.GONE);
                }
                //Careers
                Careerspref = careerspref.getText().toString();
                if (Careerspref.equalsIgnoreCase("YES")||(Careerspref.equalsIgnoreCase("EDIT"))) {
                    careers.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EmployeeDashboard.this,Careers.class);
                            intent.putExtra("empid",EMPID);
                            intent.putExtra("userId",UserId);
                            intent.putExtra("usertype",UserType);
                            startActivity(intent);
                        }
                    });
                } else if (Careerspref.equalsIgnoreCase("NO")) {
                    careers.setVisibility(View.GONE);
                }
                //MyPay
                Mypaypref = mypaypref.getText().toString();
                if (Mypaypref.equalsIgnoreCase("YES")||(Mypaypref.equalsIgnoreCase("EDIT"))) {
                    mypay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EmployeeDashboard.this,MyPay.class);
                            intent.putExtra("empid",EMPID);
                            intent.putExtra("name",Firstname);
                            intent.putExtra("middlename",Middlename);
                            intent.putExtra("lastname",Lastname);
                            intent.putExtra("userId",UserId);
                            intent.putExtra("usertype",UserType);
                            startActivity(intent);
                        }
                    });
                } else if (Mypaypref.equalsIgnoreCase("NO")) {
                    mypay.setVisibility(View.GONE);
                }
                //OurUpdates
                Ourupdatespref = ourupdatespref.getText().toString();
                if (Ourupdatespref.equalsIgnoreCase("YES")||(Ourupdatespref.equalsIgnoreCase("EDIT"))) {
                    ourupdates.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EmployeeDashboard.this,OurUpdates.class);
                            intent.putExtra("empid",EMPID);
                            intent.putExtra("userId",UserId);
                            intent.putExtra("usertype",UserType);
                            startActivity(intent);
                        }
                    });
                } else if (Ourupdatespref.equalsIgnoreCase("NO")) {
                    ourupdates.setVisibility(View.GONE);
                }
                //TicketingTool
                ticketing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EmployeeDashboard.this,Ticketing_Dashboard.class);
                        intent.putExtra("empid",EMPID);
                        intent.putExtra("userId",UserId);
                        intent.putExtra("usertype",UserType);
                        intent.putExtra("department",department.getText().toString());
                        startActivity(intent);
                    }
                });
                //Calendar
                calendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EmployeeDashboard.this,CalendarActivity.class);
                        intent.putExtra("userId",UserId);
                        intent.putExtra("usertype",UserType);
                        startActivity(intent);
                    }
                });
                //Messages
                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EmployeeDashboard.this,MainActivityChat.class);
                        startActivity(intent);
                    }
                });
                //MyProject
                Myprojectspref = myprojectspref.getText().toString();
                if (Myprojectspref.equalsIgnoreCase("YES")||(Myprojectspref.equalsIgnoreCase("EDIT"))) {
                    myproject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(EmployeeDashboard.this,MyProjects.class);
                            intent.putExtra("empid",EMPID);
                            intent.putExtra("userId",UserId);
                            intent.putExtra("usertype",UserType);
                            startActivity(intent);
                        }
                    });
                } else if (Myprojectspref.equalsIgnoreCase("NO")) {
                    myproject.setVisibility(View.GONE);
                }
                visitors.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EmployeeDashboard.this,VisDirectory.class);
                        intent.putExtra("userId",UserId);
                        intent.putExtra("usertype",UserType);
                        intent.putExtra("empid",EMPID);
                        startActivity(intent);
                    }
                });


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.setting:
                        Intent intent=new Intent(EmployeeDashboard.this,Settings1.class);
                        startActivity(intent);
                        break;
                    case R.id.rewards:
                        Intent intent2 = new Intent(EmployeeDashboard.this, MyRewards.class);
                        intent2.putExtra("userId",UserId);
                        intent2.putExtra("usertype",UserType);
                        intent2.putExtra("empid",EMPID);
                        startActivity(intent2);
                        break;
                    case R.id.querydashboard:
                        Intent intent3=new Intent(EmployeeDashboard.this,EmpQueryDashboard.class);
                        intent3.putExtra("userId",UserId);
                        intent3.putExtra("usertype",UserType);
                        intent3.putExtra("empid",EMPID);
                        startActivity(intent3);
                        break;
                    case R.id.mydownloads:
                        Intent intent5=new Intent(EmployeeDashboard.this,MyDownloads.class);
                        intent5.putExtra("userId",UserId);
                        intent5.putExtra("usertype",UserType);
                        intent5.putExtra("empid",EMPID);
                        startActivity(intent5);
                        break;
                    case R.id.changepassword:
                        if(!Email.getText().toString().isEmpty()){
                            Auth.sendPasswordResetEmail(Email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(EmployeeDashboard.this, "Reset Link has been sent to mail", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(EmployeeDashboard.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else{
                            Toast.makeText(EmployeeDashboard.this, "Please Enter Mail Id", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.logout:
                        Intent intent4=new Intent(EmployeeDashboard.this,MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });


          Query query6 = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("department")
                .equalTo("TicketingTool Department");
        query6.addListenerForSingleValueEvent(valueEventListener6);

    }

    ValueEventListener valueEventListener6 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    department.setText("TicketingTool");
                }
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ///Menu items

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.newsfeed)
        {
            Intent intent = new Intent(EmployeeDashboard.this,NewsFeed.class);
            intent.putExtra("userId",UserId);
            intent.putExtra("usertype",UserType);
            intent.putExtra("empid",EMPID);
            startActivity(intent);
        }
        if(id == R.id.notification)
        {
            Intent intent = new Intent(EmployeeDashboard.this,NotificationsDashboard.class);
            intent.putExtra("userId",UserId);
            intent.putExtra("usertype",UserType);
            intent.putExtra("empid",EMPID);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
