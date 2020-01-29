package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminVisUpdate extends AppCompatActivity {

    EditText fname, lname, email, phone, contactperson, empid, companyname, companybranch, dateofvisit, reasonforvisit, personalid, typeofvisit;
    DatabaseReference databaseReference;
    Button update;
    String VisitorID,Profile,Mname1,personal;
    DatabaseReference reference;
    FirebaseDatabase database;

    TextView userid,text,dashboard,list,visitor,edit;
    ImageView iv;
    String UserId,Text;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_admin_vis_update);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);
        dashboard = (TextView)findViewById(R.id.dashboard);
        list = (TextView)findViewById(R.id.list);
        visitor = (TextView)findViewById(R.id.visitor);
        edit = (TextView)findViewById(R.id.edit);
        iv = (ImageView)findViewById(R.id.iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisUpdate.this, AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisUpdate.this, VisitorDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        visitor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisUpdate.this, AdminEditVisitor.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminVisUpdate.this, AdminVisEditDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        UserId = getIntent().getStringExtra("userId");
        userid.setText(UserId);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("VisitorDetails");

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
        if (null != intent) {

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
            Profile=getIntent().getStringExtra("profile");
            Mname1=getIntent().getStringExtra("mname");
            personal=getIntent().getStringExtra("personal");
        }

        fname = (EditText)findViewById(R.id.viseditfname);
        lname = (EditText)findViewById(R.id.viseditlname);
        email = (EditText)findViewById(R.id.viseditemail);
        phone = (EditText)findViewById(R.id.viseditphone);
        contactperson = (EditText)findViewById(R.id.viseditcontactperson);
        empid = (EditText)findViewById(R.id.viseditempid);
        companyname = (EditText)findViewById(R.id.viseditcompanyname);
        companybranch = (EditText)findViewById(R.id.viseditcompanyaddress);
        dateofvisit = (EditText)findViewById(R.id.viseditdateofvisit);
        reasonforvisit = (EditText)findViewById(R.id.viseditreasonforvisit);
        personalid = (EditText)findViewById(R.id.viseditpersonalid);
        typeofvisit = (EditText)findViewById(R.id.visedittypeofvisit);
        update = findViewById(R.id.viseditupdate);


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

        text.setText("Admin dashboard - Visitor Dashboard - Visitros List(Edit) - Update("+Fname1+"."+Lname1+")");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Fname = fname.getText().toString().trim();
                final String Mname =Mname1;
                final String Lname = lname.getText().toString().trim();
                final String Email = email.getText().toString().trim();
                final String Phone = phone.getText().toString().trim();
                final String Contactperson = contactperson.getText().toString().trim();
                final String Empid = empid.getText().toString().trim();
                final String Companyname = companyname.getText().toString().trim();
                final String Companybranch = companybranch.getText().toString().trim();
                final String Dateofvisit = dateofvisit.getText().toString().trim();
                final String Reasonforvisit = reasonforvisit.getText().toString().trim();
                final String Sppersonal = personal;
                final String Personal = personalid.getText().toString().trim();
                final String Typeofvisit = typeofvisit.getText().toString().trim();
                final String Profile1=Profile;

                VisUser user = new VisUser(VisitorID, Fname, Mname, Lname, Email, Phone, Contactperson, Empid, Companyname, Companybranch, Dateofvisit, Reasonforvisit, Sppersonal, Personal, Typeofvisit,Profile1.toString());
                reference.child(VisitorID).setValue(user);

                Intent intent = new Intent(AdminVisUpdate.this, VisitorDashboard.class);
                startActivity(intent);
                Toast.makeText(AdminVisUpdate.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(AdminVisUpdate.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
