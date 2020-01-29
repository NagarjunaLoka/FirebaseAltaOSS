package com.example.anushak.firebasealtaoss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.anushak.firebasealtaoss.EmpProjects.KEY_PROJECTNAME;
import static com.example.anushak.firebasealtaoss.R.drawable.preview;

public class ProjectEmpList extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    ListView listView, listviewfetch;
    private EmpAdapter mList;
    ArrayList<User> EmpList = new ArrayList<User>();
    User user;
    DatabaseReference dref;
    DatabaseReference reference, mDatabase;
    TextView text;
    String Text,ID;

    SearchView searchView;

    FirebaseProjectAdapter<User> Sample1;
    FirebaseProjectAdapter<User> Sample2;

    String UserType,Message,UserId;
    TextView usertype,message,userId;
    HorizontalScrollView hsv1;
    Animation fromtop;
    ImageView iv,preview;
    TextView dashboard,list,preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_project_emp_list);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView = (ListView) this.findViewById(R.id.listView);
        listviewfetch = (ListView) this.findViewById(R.id.listViewfetch);
        preview =findViewById(R.id.preview);

        listviewfetch.setEmptyView(preview);

        text = (TextView) findViewById(R.id.text);
        userId = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        message = (TextView)findViewById(R.id.message);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        list = (TextView)findViewById(R.id.list);
        preferences = (TextView)findViewById(R.id.preferences);

        Text = getIntent().getStringExtra(KEY_PROJECTNAME);
        text.setText(Text);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        userId.setText(UserId);
        usertype.setText(UserType);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        hsv1.setAnimation(fromtop);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectEmpList.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectEmpList.this,Preferences.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectEmpList.this,ProjectPreferences.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(UserType.equals("admin"))
        {
            message.setText("Admin Dashboard - Preferences - Project Preferences - Employee List");
        }


        reference = FirebaseDatabase.getInstance().getReference("Users");

        Sample1 = new FirebaseProjectAdapter<User>(reference, User.class, R.layout.emp_project_list, this) {
            private TextView Name, Empid, Designation;
            CheckBox checkBox;
            ImageView profile;
            Context context;

            @Override
            protected void populateView(View v, final User model) {
                Name = (TextView) v.findViewById(R.id.name);
                Name.setText(String.valueOf(model.getName().toString()) + model.getMiddlename().toString() + "." + model.getLastname().toString());
                Empid = (TextView) v.findViewById(R.id.empid);
                Empid.setText(String.valueOf(model.getEmpid().toString()));
                Designation = (TextView) v.findViewById(R.id.designation);
                Designation.setText(String.valueOf(model.getDesignation()));
                profile = (ImageView) v.findViewById(R.id.profileImageView);
                Picasso.with(context).load(String.valueOf(model.getImageURL())).into(profile);
                checkBox = (CheckBox) v.findViewById(R.id.checkbox);
                dref = FirebaseDatabase.getInstance().getReference("ProjectPreferences");
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        User user = new User(model.getEmpid(), Text);
                        dref.child(Text+model.getEmpid()).setValue(user);

                    }
                });

            }
        };
        listView.setAdapter(Sample1);


        mDatabase=FirebaseDatabase.getInstance().getReference("ProjectPreferences");
        Query query=mDatabase.orderByChild("prefproject").equalTo(Text);

        Sample2=new FirebaseProjectAdapter<User>(query,User.class, R.layout.emp_delete_list,this) {
            private TextView Empid;
            Button delete;
            Context context1;

            @Override
            protected void populateView(View v, final User model) {
                Empid = (TextView) v.findViewById(R.id.empid);
                Empid.setText(String.valueOf(model.getEmpid().toString()));

                delete = (Button) v.findViewById(R.id.delete);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase.child(Text+model.getEmpid()).removeValue();
                        startActivity(getIntent());
                        Toast.makeText(getApplicationContext(),model.getEmpid()+"is Deleted",Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        listviewfetch.setAdapter(Sample2);
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
            UserId = userId.getText().toString();
            Message = message.getText().toString();
            Intent i = new Intent(ProjectEmpList.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Message);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
