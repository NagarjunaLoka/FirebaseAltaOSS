package com.example.anushak.firebasealtaoss;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewProject extends AppCompatActivity {

    EditText projectname, teamleadername, clientname;
    Spinner projectstatus;
    TextView startdate,enddate,empid,userid,text,usertype;
    Button projectsubmit;
    ImageView date1,date2;
    DatePickerDialog datePickerDialog;

    DatabaseReference databaseReference;

    String Empid,UserId,Text,UserType;
    HorizontalScrollView hsv1,hsv2,hsv3;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2;
    Animation fromtop;
    android.support.v7.widget.Toolbar toolbar;

    Calendar calendar;





    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAueTGKP0:APA91bHo7bOJDKkcUV3MJznbAfOhTVZ1A7IEP9fkBk_-70dM0tIYbis1cVhR4cBr-rV8FVN2FtQ7G9-snLfSHLgG_xB_vvvkNmduv7H91OGQMSNwvsQ0ybcxpNkFmiltRn4mBZfSphVe";
    //final private String serverKey = "key=" + "AIzaSyC47VjnJtOcX-g3m-yJ9piXymQNibn2K38";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    private static final String SUBSCRIBE_TO = "userABC";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;
    String NOTIFY;

    String NOTIFY1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_new_project);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);








        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( NewProject.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);
                FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                Log.i("FirebaseInsatnceId", "onTokenRefresh completed with token: " + newToken);

            }
        });






        databaseReference = FirebaseDatabase.getInstance().getReference("Projects");

        projectname = (EditText) findViewById(R.id.projectname);
        teamleadername = (EditText) findViewById(R.id.teamleadername);
        clientname = (EditText) findViewById(R.id.clientname);
        projectstatus = (Spinner) findViewById(R.id.projectstatus);
        empid = (TextView) findViewById(R.id.empid);
        startdate = (TextView) findViewById(R.id.startdate);
        enddate = (TextView) findViewById(R.id.enddate);
        date1 = (ImageView) findViewById(R.id.date1);
        date2 = (ImageView) findViewById(R.id.date2);
        projectsubmit = (Button) findViewById(R.id.projectsubmit);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        Empid = getIntent().getStringExtra("empid");

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(Empid);

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
                Intent intent = new Intent(NewProject.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(NewProject.this,HrDashboard.class);
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
                Intent intent = new Intent(NewProject.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - New Project");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - New Project");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - New Project");
        }
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(NewProject.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(NewProject.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                enddate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        projectsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUpdate();

            }
        });

    }

    private void addUpdate(){
        String Empid = empid.getText().toString();
        String ProjectName = projectname.getText().toString();
        String TeamLeaderName = teamleadername.getText().toString();
        String ClientName = clientname.getText().toString();
        String StartDate = startdate.getText().toString();
        String EndDate = enddate.getText().toString();
        String ProjectStatus = projectstatus.getSelectedItem().toString();

        if(!TextUtils.isEmpty(ProjectName)){
            String ProjectId = databaseReference.push().getKey();

           /* for (int i = 0; i < 10; i++) {
                // Now this will create an item with the id number
                // ex: https://YOUR-FIREBASE.firebaseio.com/items/1
                databaseReference.child('/' + i).set(new ClipData.Item(i));
            };*/

            Project project = new Project(ProjectId,Empid,ProjectName,TeamLeaderName,ClientName,StartDate,EndDate,ProjectStatus);
            databaseReference.child(ProjectId).setValue(project);

            calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  HH:mm a"); //Date and time
            String currentDate = sdf.format(calendar.getTime());

            SimpleDateFormat sdf_ = new SimpleDateFormat("EEE");
            Date date = new Date();
            String dayName = sdf_.format(date);

            String date1="" + dayName + "  " + currentDate + "";

            Notification notification1=new Notification(ProjectId,Empid,"New Project Alert","New Project has been created by "+Empid,date1);
            FirebaseDatabase.getInstance().getReference().child("Notifications").child(ProjectId).setValue(notification1);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();



            TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
            NOTIFICATION_TITLE = projectname.getText().toString();
            NOTIFICATION_MESSAGE = startdate.getText().toString();
            NOTIFY="Hi Every one our new project name: "+NOTIFICATION_TITLE;
            NOTIFY1 ="Started Date : "+NOTIFICATION_MESSAGE;

            JSONObject notification = new JSONObject();
            JSONObject notifcationBody = new JSONObject();
            try {
                notifcationBody.put("title", NOTIFY);
                notifcationBody.put("message", NOTIFY1);

                notification.put("to", TOPIC);
                notification.put("data", notifcationBody);
            } catch (JSONException e) {
                Log.e(TAG, "onCreate: " + e.getMessage());
            }
            sendNotification(notification);


        }else{
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
        }




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
            Intent i = new Intent(NewProject.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        //  projectname.setText("");
                        //updatedby.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewProject.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
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



}
