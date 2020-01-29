package com.example.anushak.firebasealtaoss;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Leave extends AppCompatActivity {

    TextView text,userid,usertype,empid;
    TextView PmMail,PmEmpid,HrMail,HrEmpid,Email,OfficialEmail;
    String Text,UserId,UserType,Empid;

    TextView fromdate,todate;
    EditText reason,noofdays,appliedId,description;
    Spinner leavetype;
    ImageView date1,date2;
    DatePickerDialog datePickerDialog;
    Button submit,history;
    HorizontalScrollView hsv1;
    ImageView iv;
    TextView dashboard,attendance;

    Toolbar toolbar;
    DatabaseReference databaseReference;

    String month,monthandyear;
    int yearname;
    EditText password;
    String PersonalEmail,pmemail,pmempid,hremail,hrempid,officialemail;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAueTGKP0:APA91bHo7bOJDKkcUV3MJznbAfOhTVZ1A7IEP9fkBk_-70dM0tIYbis1cVhR4cBr-rV8FVN2FtQ7G9-snLfSHLgG_xB_vvvkNmduv7H91OGQMSNwvsQ0ybcxpNkFmiltRn4mBZfSphVe";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    String NOTIFy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_leave);

        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        password = findViewById(R.id.password);
        leavetype=findViewById(R.id.leavetype);
        reason=findViewById(R.id.reason);
        fromdate=findViewById(R.id.fromdate);
        todate=findViewById(R.id.todate);
        noofdays=findViewById(R.id.noofdays);
        appliedId=findViewById(R.id.applyto);
        date1=findViewById(R.id.date1);
        date2=findViewById(R.id.date2);
        description = findViewById(R.id.description);
        submit=findViewById(R.id.submit);
        history = findViewById(R.id.history);

        PmMail = findViewById(R.id.pmmail);
        OfficialEmail = findViewById(R.id.officialemail);
        PmEmpid = findViewById(R.id.pmempid);
        HrMail = findViewById(R.id.hrmail);
        HrEmpid = findViewById(R.id.hrempid);
        Email = findViewById(R.id.email);

        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        attendance = (TextView)findViewById(R.id.attendance);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Empid = getIntent().getStringExtra("empid");

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(Empid);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leave.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leave.this,AttendanceNew.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        text.setText("Employee Dashboard - Attendance - Leave Module(Apply Leave)");

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        Query query = databaseReference.child(UserId);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pmemail = dataSnapshot.child("projectmanagermail").getValue().toString();
                pmempid = dataSnapshot.child("projectmanagerid").getValue().toString();
                hremail = dataSnapshot.child("hrmail").getValue().toString();
                hrempid = dataSnapshot.child("hrid").getValue().toString();

                PmMail.setText(pmemail);
                PmEmpid.setText(pmempid);
                HrMail.setText(hremail);
                HrEmpid.setText(hrempid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        appliedId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                final String AppliedTo = appliedId.getText().toString();

                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                //Query query=ref.orderByChild("email").//.child("email");
                final Query query = ref.orderByChild("empid").equalTo(AppliedTo);

                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        if (dataSnapshot.exists()) {
                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                            PersonalEmail = (String) map.get("personalemail");
                            officialemail = (String) map.get("officialemail");

                            Email.setText(PersonalEmail);
                            OfficialEmail.setText(officialemail);

                            Toast.makeText(Leave.this, "hi", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Leave.this, "value not fetching", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Leave.this, "value not fetching", Toast.LENGTH_SHORT).show();

                    }
                });
            }

        });

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Leave.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                fromdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                                Calendar cal=Calendar.getInstance();
                                SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                                int monthnum=monthOfYear;
                                cal.set(Calendar.MONTH,monthnum);

                                month = month_date.format(cal.getTime());
                                yearname=year;
                                monthandyear=month+yearname;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Leave.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                todate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                                String startDate = fromdate.getText().toString();
                                String stopDate = todate.getText().toString();

                                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");

                                /*Date d1 = null;
                                Date d2 = null;
                                try {
                                    d1 = format.parse(startDate);
                                    d2 = format.parse(stopDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                long diff = d2.getDate() - d1.getDate();

                                noofdays.setText(diff+"");*/

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Leaves");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyLeave();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leave.this,LeavesHistory.class);
                intent.putExtra("empid",Empid);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });
    }

    public  void ApplyLeave() {
        String Empid = empid.getText().toString();
        String Leavetype = leavetype.getSelectedItem().toString();
        String Reason = reason.getText().toString();
        String Fromdate = fromdate.getText().toString();
        String Todate = todate.getText().toString();
        String Noofdays = noofdays.getText().toString();
        String Applyto = appliedId.getText().toString();
        String Description = description.getText().toString();
        String Status="Leave Request";
        String Month=month;
        String Year= String.valueOf(yearname).toString();
        String month_year=monthandyear;
        String EmpidMonthandYear=Empid+month_year;
        String EmpidYear = Empid+Year;

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        //i.putExtra(Intent.ACTION_SEND, email);
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{officialemail,PersonalEmail});
        i.putExtra(Intent.EXTRA_SUBJECT, Empid+"-Leave Request");
        //i.putExtra(Intent.EXTRA_TEXT   , Mobile);
        i.putExtra(Intent.EXTRA_TEXT   , "Hi Sir/Mam,\n\n"+Leavetype+"\n"+Noofdays+"\n"+Description);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Leave.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

        if (!TextUtils.isEmpty(Empid)) {

            String id=Empid + Fromdate + month_year;

            Project project = new Project(Empid,Leavetype, Reason, Fromdate, Todate, Noofdays, Applyto,Description,Status,Month,Year,month_year,EmpidMonthandYear,EmpidYear);
            databaseReference.child(id).setValue(project);
            Toast.makeText(Leave.this, "Data Inserted", Toast.LENGTH_SHORT).show();

            TOPIC = "/topics/"+appliedId.getText().toString(); //topic has to match what the receiver subscribed to

            NOTIFICATION_TITLE = Empid.toString();
            NOTIFICATION_MESSAGE = Leavetype.toString();

            NOTIFy = "Leave Request from :  "+NOTIFICATION_TITLE;

            JSONObject notification = new JSONObject();
            JSONObject notifcationBody = new JSONObject();
            try {
                notifcationBody.put("title", NOTIFy);
                notifcationBody.put("message", NOTIFICATION_MESSAGE+"\n"+"No.of Days:"+Noofdays);

                notification.put("to", TOPIC);
                notification.put("data", notifcationBody);
            } catch (JSONException e) {
                Log.e(TAG, "onCreate: " + e.getMessage() );
            }
            sendNotification(notification);
        }

    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Leave.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
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
            Intent i = new Intent(Leave.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}

