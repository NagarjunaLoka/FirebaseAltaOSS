package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updatereferenceActivity extends AppCompatActivity {

    EditText idinter, name, fresh, mobile, designation,referencenameup,referenceidup;
    String Idinter, Name, Fresh , Mobile ,Designation, Referencenameup,Referenceidup,Qualify ,Eid,Proj,Stats,Updates;
    Button update;
    DatabaseReference databaseReference;
    android.support.v7.widget.Toolbar toolbar;
    TextView text,userid,usertype;
    String Text,UserId,UserType;
    HorizontalScrollView hsv1,hsv2;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1,history,history1,details,details1;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_updatereference);

        idinter=(EditText)findViewById(R.id.idinter);
        name=(EditText)findViewById(R.id.namee);
        fresh=(EditText)findViewById(R.id.fresh);
        mobile=(EditText)findViewById(R.id.mobile);
        designation=(EditText)findViewById(R.id.desg);
        referencenameup=(EditText)findViewById(R.id.renameup);
        referenceidup=(EditText)findViewById(R.id.reidup);
        update=(Button)findViewById(R.id.UpdateButton);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);
        history = (TextView)findViewById(R.id.history);
        history1 = (TextView)findViewById(R.id.history1);
        details = (TextView)findViewById(R.id.details);
        details1 = (TextView)findViewById(R.id.details1);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        userid.setText(UserId);
        usertype.setText(UserType);
        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Interview Dashboard - Reference Candidates List - Candidate Details - Update");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Interview Dashboard - Reference Candidates List - Candidate Details - Update");
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
                Intent intent = new Intent(updatereferenceActivity.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatereferenceActivity.this,InterviewDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatereferenceActivity.this,ReferenceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        details.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatereferenceActivity.this,Show_Reference_List.class);
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
                Intent intent = new Intent(updatereferenceActivity.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatereferenceActivity.this,InterviewDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        history1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatereferenceActivity.this,ReferenceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        details1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updatereferenceActivity.this,Show_Reference_List.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Interviews");

        Idinter = getIntent().getStringExtra("Interviewid");
        Name = getIntent().getStringExtra("Name");
        Qualify=getIntent().getStringExtra("quali");
        Fresh = getIntent().getStringExtra("fresher");
        Eid=getIntent().getStringExtra("elid");
        Mobile = getIntent().getStringExtra("Mobile");
        Designation = getIntent().getStringExtra("designation");
        Referencenameup = getIntent().getStringExtra("referencename");
        Referenceidup = getIntent().getStringExtra("referenceid");
        Proj=getIntent().getStringExtra("project");
        Updates=getIntent().getStringExtra("upd");
        Stats=getIntent().getStringExtra("stat");

        idinter.setText(Idinter);
        name.setText(Name);
        fresh.setText(Fresh);
        mobile.setText(Mobile);
        designation.setText(Designation);
        referencenameup.setText(Referencenameup);
        referenceidup.setText(Referenceidup);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter();
            }
        });

    }


    private void inter(){
        String FullName = name.getText().toString().trim();
        String Qualifications = Qualify.toString().trim();
        String Fresher =  fresh.getText().toString().trim();
        String Email = Eid.toString().trim();
        String Mobile = mobile.getText().toString().trim();
        String Designation = designation.getText().toString().trim();
        String Project = Proj.toString().trim();
        String Rename = referencenameup.getText().toString().trim();
        String Reid = referenceidup.getText().toString().trim();
        String Sel = Updates.toString().trim();
        String Walk = Stats.toString().trim();

        if(!TextUtils.isEmpty(FullName)){
            //String id = databaseReference.push().getKey();
            Intervi intervi = new Intervi(Idinter, FullName,Qualifications, Fresher, Email,Mobile ,Designation ,Project,Rename,Reid,Sel,Walk);
            databaseReference.child(Idinter).setValue(intervi);
            Toast.makeText(this, "Updated Details", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "you should enter Fields", Toast.LENGTH_SHORT).show();
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
            Intent i=new Intent(updatereferenceActivity.this,QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
