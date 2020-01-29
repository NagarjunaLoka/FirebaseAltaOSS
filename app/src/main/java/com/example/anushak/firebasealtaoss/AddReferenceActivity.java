package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class AddReferenceActivity extends AppCompatActivity {

    EditText fullname , qualifi, freshers, email, mobileno,desgination, project,rename,reid;
    Button register;
    TextView selected,referencestatus;
    DatabaseReference databaseReference;

    android.support.v7.widget.Toolbar toolbar;
    TextView text,userid,usertype;
    String Text,UserId,UserType;
    HorizontalScrollView hsv1,hsv2;
    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1,history,history1;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_add_reference);

        databaseReference = FirebaseDatabase.getInstance().getReference("Interviews");
        selected = (TextView) findViewById(R.id.selected);
        referencestatus = (TextView) findViewById(R.id.referencesta);
        fullname= (EditText)findViewById(R.id.fullname);
        qualifi= (EditText)findViewById(R.id.Qualification);
        freshers= (EditText)findViewById(R.id.fresh);
        email= (EditText)findViewById(R.id.email);
        mobileno= (EditText)findViewById(R.id.mobile);
        desgination= (EditText)findViewById(R.id.desgnation);
        project= (EditText)findViewById(R.id.project);
        register=(Button)findViewById(R.id.btn1);
        rename=(EditText)findViewById(R.id.referencename);
        reid=(EditText)findViewById(R.id.referenceid);

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

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        userid.setText(UserId);
        usertype.setText(UserType);

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Interview Dashboard - Reference Candidates List - New Candidate Form");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Interview Dashboard - Reference Candidates List - New Candidate Form");
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
                Intent intent = new Intent(AddReferenceActivity.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReferenceActivity.this,InterviewDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReferenceActivity.this,ReferenceActivity.class);
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
                Intent intent = new Intent(AddReferenceActivity.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReferenceActivity.this,InterviewDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        history1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddReferenceActivity.this,ReferenceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refere();
            }
        });
    }

    private void refere(){
        String FullName = fullname.getText().toString().trim();
        String Qualifications = qualifi.getText().toString().trim();
        String Fresher =  freshers.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Mobile = mobileno.getText().toString().trim();
        String Designation = desgination.getText().toString().trim();
        String Project = project.getText().toString().trim();
        String Rename = rename.getText().toString().trim();
        String Reid = reid.getText().toString().trim();
        String Sel = selected.getText().toString().trim();
        String Ref = referencestatus.getText().toString().trim();

        if(!TextUtils.isEmpty(FullName)){
            String id = databaseReference.push().getKey();

            Intervi intervi = new Intervi(id, FullName,Qualifications, Fresher, Email,Mobile ,Designation ,Project,Rename,Reid,Sel,Ref);
            databaseReference.child(id).setValue(intervi);
            Toast.makeText(this, "Employee added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "you should enter Employee Name", Toast.LENGTH_SHORT).show();
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
            Intent i=new Intent(AddReferenceActivity.this,QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
