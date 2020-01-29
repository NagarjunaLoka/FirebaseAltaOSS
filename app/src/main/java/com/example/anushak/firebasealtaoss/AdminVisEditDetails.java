package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminVisEditDetails extends AppCompatActivity {

    TextView fname, lname, email, phone, contactperson, empid, companyname, companybranch, dateofvisit, reasonforvisit, personalid, typeofvisit;
    DatabaseReference databaseReference;
    Button edit, delete;
    String VisitorID,Mname,personal,Profile;

    TextView text,userid;
    String Text,UserId;
    TextView dashboard,list,visitor;
    ImageView iv;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_admin_vis_edit_details);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView)findViewById(R.id.dashboard);
        list = (TextView)findViewById(R.id.list);
        visitor = (TextView)findViewById(R.id.visitor);

        UserId = getIntent().getStringExtra("userId");
        userid.setText(UserId);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisEditDetails.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisEditDetails.this, VisitorDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        visitor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisEditDetails.this, AdminEditVisitor.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("VisitorDetails");

        String Fname1 ="";
        String Lname1 = "";
        String Email1 = "";
        String Phone1 = "";
        String Contactperson1 = "";
        String Empid1 = "";
        String Companyname1 = "";
        String CompanyBranch1 = "";
        String Dateofvisit1 = "";
        String Reasonforvisit1 = "";
        String Personalid1 = "";
        String Typeofvisit1 = "";

        Intent intent = getIntent();
        if(null != intent){
            Fname1 = getIntent().getStringExtra("fname");
            Lname1 = getIntent().getStringExtra("lname");
            Email1 = getIntent().getStringExtra("email");
            Phone1 = getIntent().getStringExtra("phone");
            Contactperson1 = getIntent().getStringExtra("contactperson");
            Empid1 = getIntent().getStringExtra("empid");
            Companyname1 = getIntent().getStringExtra("companyname");
            CompanyBranch1 = getIntent().getStringExtra("companybranch");
            Dateofvisit1 = getIntent().getStringExtra("dateofvisit");
            Reasonforvisit1 = getIntent().getStringExtra("reasonforvisit");
            Personalid1 = getIntent().getStringExtra("personalid");
            Typeofvisit1 = getIntent().getStringExtra("typeofvisit");
            VisitorID=getIntent().getStringExtra("id");
            Profile= getIntent().getStringExtra("profile");
            Mname=getIntent().getStringExtra("mname");
            personal=getIntent().getStringExtra("personal");
        }

        fname =findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        contactperson = findViewById(R.id.contactperson);
        empid = findViewById(R.id.empid);
        companyname = findViewById(R.id.companyname);
        companybranch = findViewById(R.id.companybranch);
        dateofvisit = findViewById(R.id.dateofvisit);
        reasonforvisit = findViewById(R.id.reasonforvisit);
        personalid = findViewById(R.id.personalid);
        typeofvisit = findViewById(R.id.typeofvisit);
        edit = findViewById(R.id.visedit);
        delete = findViewById(R.id.visdelete);

        fname.setText(Fname1);
        lname.setText(Lname1);
        email.setText(Email1);
        phone.setText(Phone1);
        contactperson.setText(Contactperson1);
        empid.setText(Empid1);
        companyname.setText(Companyname1);
        companybranch.setText(CompanyBranch1);
        dateofvisit.setText(Dateofvisit1);
        reasonforvisit.setText(Reasonforvisit1);
        personalid.setText(Personalid1);
        typeofvisit.setText(Typeofvisit1);

        text.setText("Admin Dashboard - Visitor Dashboard - Visitor List(Edit/Delete) - Edit("+Fname1+"."+Lname1+")");

        UserId = userid.getText().toString();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisEditDetails.this, AdminVisUpdate.class);
                intent.putExtra("fname", fname.getText().toString());
                intent.putExtra("id",VisitorID);
                intent.putExtra("lname", lname.getText().toString());
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("contactperson", contactperson.getText().toString());
                intent.putExtra("empid", empid.getText().toString());
                intent.putExtra("companyname", companyname.getText().toString());
                intent.putExtra("companybranch", companybranch.getText().toString());
                intent.putExtra("reasonforvisit", reasonforvisit.getText().toString());
                intent.putExtra("dateofvisit", dateofvisit.getText().toString());
                intent.putExtra("personalid", personalid.getText().toString());
                intent.putExtra("typeofvisit", typeofvisit.getText().toString());
                intent.putExtra("personal", personal);
                intent.putExtra("mname", Mname);
                intent.putExtra("profile", Profile);
                intent.putExtra("userId",UserId);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(VisitorID).removeValue();
                Intent intent = new Intent(AdminVisEditDetails.this, VisitorDashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.queryform,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        UserId = userid.getText().toString();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(AdminVisEditDetails.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}

