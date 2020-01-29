package com.example.anushak.firebasealtaoss;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaySlips_Form extends AppCompatActivity {


    Button payslip;

    ImageView calendar;

    TextView dateandtime;

    DatePickerDialog datePickerDialog;

    FirebaseDatabase fdatabse;
    DatabaseReference dref;

    TextView name,empid,designation,location,pannum,payabledays,paiddays,monthandyear1;
    EditText basicpay,houserentallowence,convencesallowence,specialallowence,otherallowences,medical,epf,transportation,others,ptd;

    EditText monthandyear;

    Button submit;

    String EMPID;

    TextView text,userid,usertype;
    String Text,UserId,UserType;

    ImageView iv;
    TextView dashboard,dept,emp;

    HorizontalScrollView hsv1;
    Animation frombottom,fromtop,fromleft,fromright;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_pay_slips__form);

        dateandtime=findViewById(R.id.monthandyear);
        calendar=findViewById(R.id.calendar);

        fdatabse = FirebaseDatabase.getInstance();
        dref = fdatabse.getReference("Salary Details");

        name=findViewById(R.id.name);
        empid=findViewById(R.id.empid);
        designation=findViewById(R.id.designation);
        location=findViewById(R.id.location);
        pannum=findViewById(R.id.pan);
        payabledays=findViewById(R.id.payabledays);
        paiddays=findViewById(R.id.paiddays);

        basicpay=findViewById(R.id.basicpay);
        houserentallowence=findViewById(R.id.houserent);
        convencesallowence=findViewById(R.id.convences);
        specialallowence=findViewById(R.id.specialallowence);
        otherallowences=findViewById(R.id.otherallowence);
        medical=findViewById(R.id.medical);
        epf=findViewById(R.id.pf);
        transportation=findViewById(R.id.transortation);
        others=findViewById(R.id.others);
        ptd=findViewById(R.id.ptd);
        submit=findViewById(R.id.submit);
        monthandyear=findViewById(R.id.monthandyear);
        monthandyear1=findViewById(R.id.monthandyear1);

       // EMPID="l1001";


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usertype = (TextView)findViewById(R.id.usertype);
        userid = (TextView)findViewById(R.id.userid);
        text = (TextView)findViewById(R.id.text);

        iv = (ImageView)findViewById(R.id.iv);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dept = (TextView) findViewById(R.id.dept);
        emp = (TextView) findViewById(R.id.emp);
        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);

        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");
        EMPID = getIntent().getStringExtra("empid");

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(EMPID);

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
                Intent intent = new Intent(PaySlips_Form.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        dept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips_Form.this,HrPayslips.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips_Form.this,Employees_in_Dept.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        text.setText("HR Dashboard - Dept.List(Payslips) - Employee List - Payslips Form");


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day



                // date picker dialog
                datePickerDialog = new DatePickerDialog(PaySlips_Form.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateandtime.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);


                                // String n=dateandtime.getText().toString();

                              /*  dateandtime.setText(month_name+n);
                                monthandyear1.setText(EMPID+month_name+n);
*/
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUpdate();
            }
        });


    }

    private void addUpdate(){

        String Name = name.getText().toString();
        String Empid = empid.getText().toString();
        String Designation = designation.getText().toString();
        String Location = location.getText().toString();
        String Payabledays = payabledays.getText().toString();
        String Paiddays = paiddays.getText().toString();
        String Pan=pannum.getText().toString();

        String Basicpay=basicpay.getText().toString();
        String Houserentallowence=houserentallowence.getText().toString();
        String Convencesallowence=convencesallowence.getText().toString();
        String Specialallowence=specialallowence.getText().toString();
        String Otherallowences=otherallowences.getText().toString();

        String Medical=medical.getText().toString();
        String Epf=epf.getText().toString();

        String Transportation=transportation.getText().toString();
        String Others=others.getText().toString();
        String Ptd=ptd.getText().toString();
        String Monthandyear=monthandyear.getText().toString();
        String Monthandyear1=monthandyear1.getText().toString();


        if(!TextUtils.isEmpty(Name)){

            String id = dref.push().getKey();
            //  String id = Empid;// this is for creating table in firebase  with employee id
            Salary salary = new Salary(id,Name,Empid,Designation,Location,Pan,Payabledays,Paiddays,Basicpay,Houserentallowence,Convencesallowence,Specialallowence,Otherallowences,Medical,Epf,Transportation,Others,Ptd,Monthandyear,Monthandyear1);

            dref.child(id).setValue(salary);

            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();

        }
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
            Intent i = new Intent(PaySlips_Form.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }



}
