package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anushak.firebasealtaoss.util1.Helper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Documents extends AppCompatActivity {

    TextView joiningletter,releavingletter,presentofferletter,previousofferletter,
            previousexperienceletter,previouspayslips,certificates,bankstatements,coursecertificates,patent;
    TextView empId,mtext,name;
    String empid,Name,Mname,Lname;
    private StorageReference pdfref;

    TextView text,usertype,equery;
    HorizontalScrollView hsv1,hsv2;
    Animation fromtop;
    String Text,UserType,Equery;

    android.support.v7.widget.Toolbar toolbar;

    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_documents);

        joiningletter = (TextView) findViewById(R.id.joiningletter);
        releavingletter = (TextView) findViewById(R.id.releavingletter);
        presentofferletter = (TextView) findViewById(R.id.presentofferletter);
        previousofferletter=(TextView)findViewById(R.id.previousofferletter);
        previousexperienceletter=(TextView)findViewById(R.id.previousexperienceletter);
        previouspayslips = (TextView)findViewById(R.id.previouspayslips);
        certificates = (TextView)findViewById(R.id.certificates);
        bankstatements = (TextView)findViewById(R.id.bankstatements);
        coursecertificates = (TextView)findViewById(R.id.coursecertificates);
        patent =(TextView)findViewById(R.id.patent);
        name = (TextView)findViewById(R.id.tvFirstName);

        mtext=(TextView)findViewById(R.id.mtext);

        empId = (TextView) findViewById(R.id.tvEmpID);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.text);
        usertype = (TextView)findViewById(R.id.usertype);
        equery = (TextView)findViewById(R.id.query);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);

        Name = getIntent().getStringExtra("name");
        Mname = getIntent().getStringExtra("middlename");
        Lname = getIntent().getStringExtra("lastname");
        Equery = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        usertype.setText(UserType);
        equery.setText(Equery);

        empid = getIntent().getStringExtra("EmpId");

        name.setText(Name+Mname+"."+Lname);
        empId.setText(empid);

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
                Intent intent = new Intent(Documents.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Documents.this,EmpDocuments.class);
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
                Intent intent = new Intent(Documents.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Documents.this,EmpDocuments.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Employee List - Documents");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Employee List - Documents");
        }


        //bank statements
        bankstatements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Bank Statements(Past 3Months)";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();

            }
        });
        //joining letter
        joiningletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Joining Letter";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //releaving letter
        releavingletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Re-leaving Letter";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //present offer letter
        presentofferletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Present Offer Letter";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //previous offer letter
        previousofferletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Previous Offer Letter";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //previous experience letter
        previousexperienceletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Previous Experience Letter";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //payslips
        previouspayslips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Previous Payslips(3 Months)";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //certificates
        certificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Certificates";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //course
        coursecertificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Course certificates";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });
        //patent
        patent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Type="Patent Certificates";
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(Type).child(Type + empid+ ".pdf");
                downloadDataViaUrl();
            }
        });

    }

    private void downloadDataViaUrl() {
        Helper.showDialog(this);
        pdfref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Helper.dismissDialog();
                mtext.setText(uri.toString());
                Intent i=new Intent(Documents.this,EmpDoc.class);
                i.putExtra("Empid",empid);
                i.putExtra("URL",mtext.getText().toString());
                i.putExtra("Type", Type);
                i.putExtra("userId",Equery);
                i.putExtra("usertype",UserType);
                startActivity(i);
                Toast.makeText(Documents.this, "Click on PDF to download", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Helper.dismissDialog();
                Toast.makeText(Documents.this, "Cannot found Related file", Toast.LENGTH_SHORT).show();
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
            Equery = equery.getText().toString();
            Intent i = new Intent(Documents.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",Equery);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
