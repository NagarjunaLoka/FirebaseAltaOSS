package com.example.anushak.firebasealtaoss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class NotificationUpdate extends AppCompatActivity {

    TextView title,message,date,dashboard,notification,empid,text,userid;
    EditText ettitle,etmessage,etdate;
    String Title,Message,Date,Empid,Text,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    Button edit,delete,update;
    ImageView iv25;
    android.support.v7.widget.Toolbar toolbar;
    String ID;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_notification_update);

        title = (TextView)findViewById(R.id.title);
        message = (TextView)findViewById(R.id.message);
        date = (TextView)findViewById(R.id.date);
        ettitle = (EditText) findViewById(R.id.ettitle);
        etmessage = (EditText) findViewById(R.id.etmessage);
        etdate = (EditText) findViewById(R.id.etdate);
        edit = (Button)findViewById(R.id.edit);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iv25 = (ImageView)findViewById(R.id.iv25);
        dashboard = (TextView)findViewById(R.id.dashboard);
        notification= (TextView)findViewById(R.id.notification);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);
        userid = (TextView)findViewById(R.id.userid);

        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);

        Title = getIntent().getStringExtra("title");
        Message = getIntent().getStringExtra("message");
        Date = getIntent().getStringExtra("Date");
        ID = getIntent().getStringExtra("ID");
        String Userid = getIntent().getStringExtra("userid");

        title.setText(Title);
        message.setText(Message);
        date.setText(Date);
        userid.setText(Userid);

        ettitle.setText(Title);
        etmessage.setText(Message);
        etdate.setText(Date);
        text.setText("Admin Dashboard - Notifications Dashboard - Update Notification("+Title+")");

        edit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                title.setVisibility(8);
                ettitle.setVisibility(0);

                message.setVisibility(8);
                etmessage.setVisibility(0);

                date.setVisibility(8);
                etdate.setVisibility(0);

                edit.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Update();

                title.setText(Title);
                message.setText(Message);
                date.setText(Date);

                title.setVisibility(0);
                ettitle.setVisibility(8);

                message.setVisibility(0);
                etmessage.setVisibility(8);

                date.setVisibility(0);
                etdate.setVisibility(8);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = ettitle.getText().toString();
                Message = etmessage.getText().toString();
                Date = etdate.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("Notifications").child(ID).removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(NotificationUpdate.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NotificationUpdate.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationUpdate.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationUpdate.this, NotificationsDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

    public void Update()
    {
        Title = ettitle.getText().toString();
        Message = etmessage.getText().toString();
        Date = etdate.getText().toString();
        Notification notification = new Notification(ID,Empid,Title,Message,Date);

        FirebaseDatabase.getInstance().getReference().child("Notifications").child(ID).setValue(notification);
    }





    //Query
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.query)
        {
            Text=text.getText().toString();
            Empid = empid.getText().toString();
            Intent i = new Intent(NotificationUpdate.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",userid.getText().toString());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}
