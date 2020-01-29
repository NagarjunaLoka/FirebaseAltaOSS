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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anushak.firebasealtaoss.util1.Helper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;

public class PaySlips extends AppCompatActivity {

    TextView payslips,text,empId,mon,yr,mTextview;
    android.widget.Spinner Spinner,Spinner1;
    TextView  name,userid,usertype;
    String Empid,Text,UserId,UserType,Name,month,year;

    android.support.v7.widget.Toolbar toolbar;

    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> months = new ArrayList<String>();

    static final String[] Months = new String[] { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };

    String ty="Payslips";

    private StorageReference pdfref;

    ImageView imag;
    HorizontalScrollView hsv1,hsv2,hsv3;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_pay_slips);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2018; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        Spinner1 =(android.widget.Spinner)findViewById(R.id.yearspinner);
        Spinner1.setAdapter(adapter);

        text=(TextView)findViewById(R.id.text);
        userid=(TextView)findViewById(R.id.userid);
        usertype=(TextView)findViewById(R.id.usertype);

        payslips = (TextView) findViewById(R.id.paySlips);
        empId = (TextView) findViewById(R.id.tvEmpID);
        name = (TextView) findViewById(R.id.tvName);
        Spinner =(android.widget.Spinner)findViewById(R.id.monthspinner);
        mon = (TextView) findViewById(R.id.month);
        yr = (TextView) findViewById(R.id.year);
        imag = (ImageView) findViewById(R.id.imag);
        mTextview = (TextView) findViewById(R.id.textview);

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

        Name = getIntent().getStringExtra("name");
        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Empid=getIntent().getStringExtra("empid");

        name.setText(Name);
        userid.setText(UserId);
        usertype.setText(UserType);
        empId.setText(Empid);

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - My Pay - PaySlips");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - My Pay - PaySlips");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - My Pay - PaySlips");
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
                Intent intent = new Intent(PaySlips.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,MyPay.class);
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
                Intent intent = new Intent(PaySlips.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,MyPay.class);
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
                Intent intent = new Intent(PaySlips.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    final int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
                    for (int i = 1; i <= Months.length; i++) {
                        //Spinner =(Spinner)findViewById(Months[i]);
                        months.add(Integer.toString(i));
                        //Spinner.setAdapter(Months[i]);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(PaySlips.this, android.R.layout.simple_spinner_item, Months);
                        Spinner.setAdapter(adapter1);
                    }

                } else {
                    final int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
                    for (int i = 1; i <= Months.length - thisMonth; i++) {
                        months.add(Integer.toString(i));
                        //Spinner.setAdapter(Months[i]);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(PaySlips.this, android.R.layout.simple_spinner_item, Months);
                        Spinner.setAdapter(adapter1);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        payslips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empid = empId.getText().toString();
                month = Spinner.getSelectedItem().toString();
                year = Spinner1.getSelectedItem().toString();
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                pdfref = storageRef.child("uploads/").child(ty).child(ty + Empid + year + month +".pdf");
                downloadDataViaUrl();

            }
        });
        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=mTextview.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void downloadDataViaUrl() {
        Helper.showDialog(this);
        pdfref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Helper.dismissDialog();
                mon.setText(month);
                yr.setText(year);
                mon.setVisibility(View.VISIBLE);
                yr.setVisibility(View.VISIBLE);
                mTextview.setText(uri.toString());
                Toast.makeText(PaySlips.this, "Click on PDF to download", Toast.LENGTH_SHORT).show();
                imag.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Helper.dismissDialog();
                // mTextview.setText(String.format("Failure: %s", exception.getMessage()));
                Toast.makeText(PaySlips.this, "Cannot found Related file", Toast.LENGTH_SHORT).show();
                imag.setVisibility(View.INVISIBLE);
                mon.setVisibility(View.INVISIBLE);
                yr.setVisibility(View.INVISIBLE);

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
            Intent i = new Intent(PaySlips.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}