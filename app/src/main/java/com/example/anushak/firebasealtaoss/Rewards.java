package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.media.Image;
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

public class Rewards extends AppCompatActivity {

    TextView empid,name,department,description,userid,usertype,text,reward;
    Spinner category,type;
    Button submit;
    DatabaseReference databaseReference;
    String Name,Middlename,Lastname,Empid,Department,UserId,UserType,Text;
    HorizontalScrollView hsv1,hsv2;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;
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







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_rewards);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);








        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( Rewards.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);
                FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                Log.i("FirebaseInsatnceId", "onTokenRefresh completed with token: " + newToken);

            }
        });





        databaseReference = FirebaseDatabase.getInstance().getReference("OurUpdates");

        empid = (TextView)findViewById(R.id.empid);
        name = (TextView)findViewById(R.id.name);
        department = (TextView)findViewById(R.id.department);
        description = (TextView)findViewById(R.id.description);
        category = (Spinner)findViewById(R.id.category);
        type = (Spinner)findViewById(R.id.type);
        department = (TextView)findViewById(R.id.department);
        reward = (TextView)findViewById(R.id.reward);
        submit = (Button)findViewById(R.id.submit);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Name = getIntent().getStringExtra("name");
        Middlename = getIntent().getStringExtra("middlename");
        Lastname = getIntent().getStringExtra("lastname");
        Empid = getIntent().getStringExtra("empid");
        Department = getIntent().getStringExtra("department");

        usertype.setText(UserType);
        userid.setText(UserId);
        name.setText(Name+Middlename+"."+Lastname);
        empid.setText(Empid);
        department.setText(Department);
        reward.setText("Reward");

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
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
                Intent intent = new Intent(Rewards.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rewards.this,EmpRewards.class);
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
                Intent intent = new Intent(Rewards.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rewards.this,EmpRewards.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - EmpList - Reward Form");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - EmpList - Reward Form");
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Empid = empid.getText().toString();
                String projectn = name.getText().toString();
                String note = department.getText().toString();
                String task = category.getSelectedItem().toString();
                String update = type.getSelectedItem().toString().trim();
                String dat = description.getText().toString();
                String utype = reward.getText().toString();


                if (!TextUtils.isEmpty(projectn)) {
                    String id = databaseReference.push().getKey();
                    calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  HH:mm a"); //Date and time
                    String currentDate = sdf.format(calendar.getTime());

                    SimpleDateFormat sdf_ = new SimpleDateFormat("EEE");
                    Date date = new Date();
                    String dayName = sdf_.format(date);

                    String date1="" + dayName + "  " + currentDate + "";

                    User user = new User(id, Empid, projectn, note, task, update, dat, utype);
                    databaseReference.child(id).setValue(user);

                    Notification notification=new Notification(id,Empid,"Rewards Alert",Empid+" has got the Reward on Category of "+task,date1);
                    FirebaseDatabase.getInstance().getReference().child("Notifications").child(id).setValue(notification);
                    Toast.makeText(Rewards.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Rewards.this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
                }




                TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
                NOTIFICATION_TITLE = name.getText().toString();
                NOTIFICATION_MESSAGE = department.getText().toString();
                NOTIFY="Mr ."+NOTIFICATION_TITLE+"got new Reword";

                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();
                try {
                    notifcationBody.put("title", NOTIFICATION_TITLE);
                    notifcationBody.put("message", NOTIFICATION_MESSAGE);

                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage());
                }
                sendNotification(notification);


            }


        });
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
            Intent i = new Intent(Rewards.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
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
                        Toast.makeText(Rewards.this, "Request error", Toast.LENGTH_LONG).show();
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
