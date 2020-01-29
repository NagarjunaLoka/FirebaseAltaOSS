package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

public class NewUpdate extends AppCompatActivity {

    EditText projectname,notes, updatedby ;
    TextView date1,empid,userid,usertype,text,ourupdate;
    Button submit;
    Spinner taskstatus;
    ImageView dateimage;
    DatabaseReference databaseReference;
    Calendar calendar;
    String Empid,UserId,UserType,Text;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2;
    HorizontalScrollView hsv1,hsv2,hsv3;
    android.support.v7.widget.Toolbar toolbar;


    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAueTGKP0:APA91bHo7bOJDKkcUV3MJznbAfOhTVZ1A7IEP9fkBk_-70dM0tIYbis1cVhR4cBr-rV8FVN2FtQ7G9-snLfSHLgG_xB_vvvkNmduv7H91OGQMSNwvsQ0ybcxpNkFmiltRn4mBZfSphVe";
    //final private String serverKey = "key=" + "AIzaSyC47VjnJtOcX-g3m-yJ9piXymQNibn2K38";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    private static final String SUBSCRIBE_TO = "userABC";
    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_new_update);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( NewUpdate.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);
                FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                Log.i("FirebaseInsatnceId", "onTokenRefresh completed with token: " + newToken);

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("OurUpdates");
        userid = (TextView)findViewById(R.id.userid);
        usertype =(TextView)findViewById(R.id.usertype);
        empid=(TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        projectname = (EditText) findViewById(R.id.projectname);
        notes = (EditText) findViewById(R.id.notes);
        taskstatus = (Spinner) findViewById(R.id.taskstatus);
        updatedby = (EditText) findViewById(R.id.updatedby);
        date1 = (TextView) findViewById(R.id.date);
        submit = (Button) findViewById(R.id.submit);
        dateimage = (ImageView) findViewById(R.id.date1);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        hsv3 = (HorizontalScrollView)findViewById(R.id.hsv3);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);
        list2 = (TextView)findViewById(R.id.list2);
        ourupdate = (TextView)findViewById(R.id.update);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Empid = getIntent().getStringExtra("empid");

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(Empid);
        ourupdate.setText("Updates");

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Our Updates - New Update");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Our Updates - New Update");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Our Updates - New Update");
        }

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUpdate.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUpdate.this,OurUpdates.class);
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
                Intent intent = new Intent(NewUpdate.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUpdate.this,OurUpdates.class);
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
                Intent intent = new Intent(NewUpdate.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUpdate.this,OurUpdates.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        dateimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  HH:mm a"); //Date and time
                String currentDate = sdf.format(calendar.getTime());

                SimpleDateFormat sdf_ = new SimpleDateFormat("EEE");
                Date date = new Date();
                String dayName = sdf_.format(date);
                date1.setText("" + dayName + "  " + currentDate + "");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUpdate();

                TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
                NOTIFICATION_TITLE = projectname.getText().toString();
                NOTIFICATION_MESSAGE = updatedby.getText().toString();

                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();
                try {
                    notifcationBody.put("title", NOTIFICATION_TITLE);
                    notifcationBody.put("message", NOTIFICATION_MESSAGE);

                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);

                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage() );
                }
                sendNotification(notification);
            }



        });
    }

    private void addUpdate(){
        String Empid = empid.getText().toString();
        String projectn = projectname.getText().toString();
        String note = notes.getText().toString();
        String task = taskstatus.getSelectedItem().toString();
        String update = updatedby.getText().toString().trim();
        String dat=date1.getText().toString();
        String utype = ourupdate.getText().toString();

        if(!TextUtils.isEmpty(projectn)){
            String id = databaseReference.push().getKey();

            User user = new User(id,Empid,projectn, note, task, update,dat,utype);
            databaseReference.child(id).setValue(user);
            Notification notification=new Notification(id,Empid,"New Update",projectn,dat);
            FirebaseDatabase.getInstance().getReference().child("Notifications").child(id).setValue(notification);


            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
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
        int id=item.getItemId();
        UserId = userid.getText().toString();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i=new Intent(NewUpdate.this,QueryForm.class);
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
                        projectname.setText("");
                        updatedby.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewUpdate.this, "Request error", Toast.LENGTH_LONG).show();
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
