package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Show_Reference_List extends AppCompatActivity {

    TextView interid,name,fresh,mobile,desgin,refname,refid;
    String Interid, Name, Fresh , Mobile ,Desgin,Refname, Refid,Update,Quali,Eid,Proj,Sta;
    Button edit,select,rejected,notconfirm;
    private DatabaseReference mDatabase;

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
        setContentView(R.layout.activity_show__reference__list);
        mDatabase = FirebaseDatabase.getInstance().getReference("Interviews");
        interid=(TextView)findViewById(R.id.inid);
        name=(TextView)findViewById(R.id.name);
        fresh=(TextView)findViewById(R.id.fre);
        mobile=(TextView)findViewById(R.id.phone);
        desgin=(TextView)findViewById(R.id.destion);
        refname=(TextView)findViewById(R.id.refrename);
        refid=(TextView)findViewById(R.id.refeerid);
        edit=(Button)findViewById(R.id.buttonUpdate);
        select=(Button)findViewById(R.id.selected);
        rejected=(Button)findViewById(R.id.rejected);
        notconfirm=(Button)findViewById(R.id.notconfirmed);

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
            text.setText("HR Dashboard - Interview Dashboard - Reference Candidates List - Candidate Details");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Interview Dashboard - Reference Candidates List - Candidate Details");
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
                Intent intent = new Intent(Show_Reference_List.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_Reference_List.this,InterviewDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_Reference_List.this,ReferenceActivity.class);
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
                Intent intent = new Intent(Show_Reference_List.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_Reference_List.this,InterviewDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_Reference_List.this,ReferenceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        Interid = getIntent().getStringExtra("Interviewid");
        Name = getIntent().getStringExtra("Name");
        Quali=getIntent().getStringExtra("qualification");
        Fresh = getIntent().getStringExtra("fresher");
        Eid = getIntent().getStringExtra("emaid");
        Mobile = getIntent().getStringExtra("Mobile");
        Desgin = getIntent().getStringExtra("designation");
        Refname = getIntent().getStringExtra("referencename");
        Refid = getIntent().getStringExtra("referenceid");
        Proj = getIntent().getStringExtra("proje");
        Update=getIntent().getStringExtra("update");
        Sta=getIntent().getStringExtra("status");

        interid.setText(Interid);
        name.setText(Name);
        fresh.setText(Fresh);
        mobile.setText(Mobile);
        desgin.setText(Desgin);
        refname.setText(Refname);
        refid.setText(Refid);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Show_Reference_List.this,updatereferenceActivity.class);
                i.putExtra("Interviewid",Interid);
                i.putExtra("Name",Name);
                i.putExtra("quali",Quali);
                i.putExtra("fresher",Fresh);
                i.putExtra("elid",Eid);
                i.putExtra("Mobile",Mobile);
                i.putExtra("designation",Desgin);
                i.putExtra("referencename",Refname);
                i.putExtra("referenceid",Refid);
                i.putExtra("project",Proj);
                i.putExtra("upd",Update);
                i.putExtra("stat",Sta);
                i.putExtra("userId",UserId);
                i.putExtra("usertype",UserType);
                startActivity(i);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(Interid).child("select").setValue("Selected");
                Toast.makeText(Show_Reference_List.this, "Candidate is selected", Toast.LENGTH_SHORT).show();

            }
        });
        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(Interid).child("select").setValue("Rejected");
                Toast.makeText(Show_Reference_List.this, "Candidate is Rejected", Toast.LENGTH_SHORT).show();

            }
        });
        notconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(Interid).child("select").setValue("Not_Confirm");
                Toast.makeText(Show_Reference_List.this, "Candidate is Not Appeared", Toast.LENGTH_SHORT).show();

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
        int id=item.getItemId();
        UserId = userid.getText().toString();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i=new Intent(Show_Reference_List.this,QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
