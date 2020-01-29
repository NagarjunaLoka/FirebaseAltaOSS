package com.example.anushak.firebasealtaoss;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReimbursementUpload extends AppCompatActivity {

    LinearLayout linear1,linear2,linear3;

    Spinner spinner1,mobilemonth1,mobileyear1,spinner2;
    EditText editText,editText1,editText2,editText4;
    TextView editText3;
    ProgressBar progressBar;
    ImageView general_date;
    Button button,mobileupload;
    String Mobileno,Operator,Billno,Billdate,Claimedamount;

    Spinner generalmonth,generalyear,generalspinner1,expensesspinner2;
    EditText expenseseditText1,expenseseditText2,expenseseditText3;
    ProgressBar progressBar1;
    Button expensesbutton,expensesupload;
    ImageView dateimage;

    Spinner conveyancemonth1,conveyanceyear1,conveyanceSpinner1,conveyanceSpinner2;
    TextView conveyanceeditText1,conveyanceeditText2,conveyancetextview;
    EditText conveyanceeditText3,conveyanceeditText4,conveyanceeditText5,conveyanceeditText6,conveyancesbill;
    ProgressBar progressBar2;
    Button conveyancebutton,conveyanceupload;
    ImageView date_re,date1_re,conveyancebilldate;
    String Startdate,Enddate,Source,Destination,Totalkms,Rate;

    TextView empid,update,text,userid,usertype;

    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;

    final static int PICK_PDF_CODE = 2342;
    DatePickerDialog datePickerDialog;

    public static final String STORAGE_PATH_UPLOADS = "uploads/";
    public static final String DATABASE_PATH_UPLOADS = "Reimbursement";

    Calendar calendar;
    TextView datemobile,dategeneral,dateconveyances;
    ImageView datemobile1,dategeneral1,dateconveyances1;

    public Uri filePath;

    String EMPID,Text,UserId,UserType;

    String MOBILE,GENERAL,CONVEYANCES;

    String Update;
    android.support.v7.widget.Toolbar toolbar;

    HorizontalScrollView hsv1,hsv2,hsv3;
    ImageView iv,iv1,iv2;
    TextView dashboard,dashboard1,dashboard2,list,list1,list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_reimbursement_upload);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        empid=(TextView)findViewById(R.id.empid);
        text=(TextView)findViewById(R.id.text);
        userid=(TextView)findViewById(R.id.userid);
        usertype=(TextView)findViewById(R.id.usertype);

        spinner1 = (Spinner)findViewById(R.id.spinner1);

        linear1 = (LinearLayout)findViewById(R.id.linear1);
        linear2 = (LinearLayout)findViewById(R.id.linear2);
        linear3 = (LinearLayout)findViewById(R.id.linear3);

        mobilemonth1 = (Spinner)findViewById(R.id.mobilemonth1);
        mobileyear1 = (Spinner)findViewById(R.id.mobileyear1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        editText = (EditText)findViewById(R.id.editText);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (TextView)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        general_date = (ImageView) findViewById(R.id.general_date);
        button = (Button)findViewById(R.id.button);
        mobileupload=(Button)findViewById(R.id.mobileupload);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        generalmonth = (Spinner)findViewById(R.id.generalmonth1);
        generalyear = (Spinner)findViewById(R.id.generalyear1);
        generalspinner1 = (Spinner)findViewById(R.id.generalSpinner1);
        expensesspinner2 = (Spinner)findViewById(R.id.expensesspinner2);
        expenseseditText1 = (EditText)findViewById(R.id.expenseseditText1);
        expenseseditText2 = (EditText)findViewById(R.id.expenseseditText2);
        expenseseditText3 = (EditText)findViewById(R.id.expenseseditText3);
        dateimage = (ImageView) findViewById(R.id.dateimage);
        expensesbutton = (Button)findViewById(R.id.expensesbutton);
        expensesupload=(Button)findViewById(R.id.expensesupload);
        progressBar1 = (ProgressBar) findViewById(R.id.progressbar1);

        conveyancemonth1 = (Spinner)findViewById(R.id.conveyancemonth1);
        conveyanceyear1 = (Spinner)findViewById(R.id.conveyanceyear1);
        conveyanceSpinner1 = (Spinner)findViewById(R.id.conveyanceSpinner1);
        conveyanceSpinner2 = (Spinner)findViewById(R.id.conveyanceSpinner2);
        conveyanceeditText1 = (TextView)findViewById(R.id.conveyanceeditText1);
        conveyanceeditText2 = (TextView)findViewById(R.id.conveyanceeditText2);
        conveyancetextview = (TextView)findViewById(R.id.conveyancetextview);
        conveyanceeditText3 = (EditText)findViewById(R.id.conveyanceeditText3);
        conveyanceeditText4 = (EditText)findViewById(R.id.conveyanceeditText4);
        conveyanceeditText5 = (EditText)findViewById(R.id.conveyanceeditText5);
        conveyanceeditText6 = (EditText)findViewById(R.id.conveyanceeditText6);

        conveyancesbill=(EditText)findViewById(R.id.conveyancesbill);

        conveyancebutton = (Button)findViewById(R.id.conveyancebutton);
        conveyanceupload=(Button)findViewById(R.id.conveyanceupload);
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar2);
        date_re = (ImageView) findViewById(R.id.date_re);
        date1_re = (ImageView) findViewById(R.id.date1_re);

        datemobile=(TextView)findViewById(R.id.datemobile);
        dategeneral=(TextView)findViewById(R.id.dategeneral);
        dateconveyances=(TextView)findViewById(R.id.dateconveyances);

        update=(TextView)findViewById(R.id.update);

        conveyancebilldate=(ImageView)findViewById(R.id.conveyancebilldate);

        datemobile1=(ImageView)findViewById(R.id.datemobile1);
        dategeneral1=(ImageView)findViewById(R.id.dategeneral1);
        dateconveyances1=(ImageView)findViewById(R.id.dateconveyances1);

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

        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(DATABASE_PATH_UPLOADS);

        EMPID=getIntent().getStringExtra("empid");
        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");

        userid.setText(UserId);
        usertype.setText(UserType);
        empid.setText(EMPID);

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);
        hsv3.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - My Pay - Reimbursement Upload");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - My Pay - Reimbursement Upload");
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - My Pay - Reimbursement Upload");
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
                Intent intent = new Intent(ReimbursementUpload.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReimbursementUpload.this,MyPay.class);
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
                Intent intent = new Intent(ReimbursementUpload.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReimbursementUpload.this,MyPay.class);
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
                Intent intent = new Intent(ReimbursementUpload.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReimbursementUpload.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        conveyanceeditText6.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (conveyanceeditText5.getText().toString().trim().length()== 0)
                {
                    Toast.makeText(ReimbursementUpload.this, "Enter Wages First", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (conveyanceeditText5.getText().toString().trim().length() != 0 && conveyanceeditText6.getText().toString().trim().length() > 0) {
                    float a = Float.parseFloat(conveyanceeditText5.getText().toString().trim());
                    float b = Float.parseFloat(conveyanceeditText6.getText().toString().trim());
                    float c = a * b;
                    conveyancetextview.setText("" + c);

                }
            }
        });


        general_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ReimbursementUpload.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                editText3.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        dateimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ReimbursementUpload.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                expenseseditText2.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        date_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ReimbursementUpload.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                conveyanceeditText1.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        date1_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ReimbursementUpload.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                conveyanceeditText2.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        conveyancebilldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ReimbursementUpload.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                conveyancesbill.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        datemobile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
//date format is:  "Date-Month-Year Hour:Minutes am/pm"
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  HH:mm a"); //Date and time
                String currentDate = sdf.format(calendar.getTime());

//Day of Name in full form like,"Saturday", or if you need the first three characters you have to put "EEE" in the date format and your result will be "Sat".
                SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
                Date date = new Date();
                String dayName = sdf_.format(date);
                datemobile.setText("" + dayName + "  " + currentDate + "");
            }
        });
        dategeneral1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
//date format is:  "Date-Month-Year Hour:Minutes am/pm"
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  HH:mm a"); //Date and time
                String currentDate = sdf.format(calendar.getTime());

//Day of Name in full form like,"Saturday", or if you need the first three characters you have to put "EEE" in the date format and your result will be "Sat".
                SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
                Date date = new Date();
                String dayName = sdf_.format(date);
                dategeneral.setText("" + dayName + "  " + currentDate + "");
            }
        });
        dateconveyances1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
//date format is:  "Date-Month-Year Hour:Minutes am/pm"
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  HH:mm a"); //Date and time
                String currentDate = sdf.format(calendar.getTime());

//Day of Name in full form like,"Saturday", or if you need the first three characters you have to put "EEE" in the date format and your result will be "Sat".
                SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
                Date date = new Date();
                String dayName = sdf_.format(date);
                dateconveyances.setText("" + dayName + "  " + currentDate + "");
            }
        });



///Mobile
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {

                    case 0:
                        // set editbox invivible
                        linear1.setVisibility(View.VISIBLE);
                        linear2.setVisibility(View.GONE);
                        linear3.setVisibility(View.GONE);
                        break;
                    case 1:
                        // set editbox invivible
                        linear1.setVisibility(View.GONE);
                        linear2.setVisibility(View.VISIBLE);
                        linear3.setVisibility(View.GONE);
                        break;
                    case 2:
                        // set editbox invivible
                        linear1.setVisibility(View.GONE);
                        linear2.setVisibility(View.GONE);
                        linear3.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // set editbox invivible

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPDF();

                Mobileno = editText.getText().toString();
                Operator = editText1.getText().toString();
                Billno = editText2.getText().toString();
                Billdate = editText3.getText().toString();
                Claimedamount = editText4.getText().toString();
                MOBILE = datemobile.getText().toString();
                Update=update.getText().toString();

                if (TextUtils.isEmpty(Mobileno)) {
                    editText.setError("Please enter Mobile Number");
                    editText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Operator)) {
                    editText1.setError("Please enter Operator Name");
                    editText1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Billno)) {
                    editText2.setError("Please enter Bill Number");
                    editText2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Billdate)) {
                    editText3.setError("Please enter Bill Date");
                    editText3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Claimedamount)) {
                    editText4.setError("Please enter Claimed Amount");
                    editText4.requestFocus();
                    return;
                }
            }
        });
        mobileupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    uploadFile(filePath);
                }
                else{
                    Toast.makeText(ReimbursementUpload.this, "No file chosen", Toast.LENGTH_SHORT).show();

                }


            }
        });




////General Expenses


        expensesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getGeneralPDF();

                Billno = expenseseditText1.getText().toString();
                Billdate = expenseseditText2.getText().toString();
                Claimedamount = expenseseditText3.getText().toString();
                GENERAL=dategeneral.getText().toString();
                Update=update.getText().toString();

                if (TextUtils.isEmpty(Billno)) {
                    expenseseditText1.setError("Please enter Bill Number");
                    expenseseditText1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Billdate)) {
                    expenseseditText2.setError("Please enter Bill Date");
                    expenseseditText2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Claimedamount)) {
                    expenseseditText3.setError("Please enter Claimed Amount");
                    expenseseditText3.requestFocus();
                    return;
                }
            }
        });
        expensesupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    uploadGeneralFile(filePath);
                }
                else{
                    Toast.makeText(ReimbursementUpload.this, "No file chosen", Toast.LENGTH_SHORT).show();

                }


            }
        });

        /////Conveyances

        conveyancebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getConveyancesPDF();

                Startdate = conveyanceeditText1.getText().toString();
                Enddate = conveyanceeditText2.getText().toString();
                Source = conveyanceeditText3.getText().toString();
                Destination = conveyanceeditText4.getText().toString();
                Totalkms = conveyanceeditText5.getText().toString();
                Rate = conveyanceeditText6.getText().toString();
                Claimedamount = conveyancetextview.getText().toString();
                CONVEYANCES=dateconveyances.getText().toString();
                Billdate = conveyancesbill.getText().toString();
                Update=update.getText().toString();


                if (TextUtils.isEmpty(Startdate)) {
                    conveyanceeditText1.setError("Please enter Start Date");
                    conveyanceeditText1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Enddate)) {
                    conveyanceeditText2.setError("Please enter End Date");
                    conveyanceeditText2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Billdate)) {
                    conveyancesbill.setError("Please enter Bill Date");
                    conveyancesbill.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Source)) {
                    conveyanceeditText3.setError("Please enter Source");
                    conveyanceeditText3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Destination)) {
                    conveyanceeditText4.setError("Please enter Destination");
                    conveyanceeditText4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Totalkms)) {
                    conveyanceeditText5.setError("Please enter Total Kms");
                    conveyanceeditText5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Rate)) {
                    conveyanceeditText6.setError("Please enter Rate/Km");
                    conveyanceeditText6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Claimedamount)) {
                    conveyancetextview.setError("Please enter Claimed Amount");
                    conveyancetextview.requestFocus();
                    return;
                }
            }
        });
        conveyanceupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    uploadConveyancesFile(filePath);
                }
                else{
                    Toast.makeText(ReimbursementUpload.this, "No file chosen", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }





    /////Mobile
    private void getPDF() {

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_PDF_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            if(spinner1.getSelectedItem().toString().equals("Mobile")) {
                //uploadFile(data.getData());
                button.setText("PDF is Selected");
            }
            else if(spinner1.getSelectedItem().toString().equals("General Expenses")) {
                // uploadGeneralFile(data.getData());
                expensesbutton.setText("PDF is Selected");
            }
            else if(spinner1.getSelectedItem().toString().equals("Conveyances")) {
                // uploadConveyancesFile(data.getData());
                conveyancebutton.setText("PDF is Selected");
            }

        }
    }

    private void uploadFile(Uri data) {
        progressBar.setVisibility(View.VISIBLE);
        final StorageReference sRef = mStorageReference.child(STORAGE_PATH_UPLOADS).child(spinner1.getSelectedItem().toString())
                .child(spinner1.getSelectedItem().toString()+EMPID+MOBILE+ ".pdf");
        sRef.putFile(data)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return sRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    if(spinner1.getSelectedItem().toString().equals("Mobile")) {
                        progressBar.setVisibility(View.GONE);
                        mobileupload.setText("File Uploaded Successfully");
                    }
                    else if(spinner1.getSelectedItem().toString().equals("General Expenses")) {
                        progressBar1.setVisibility(View.GONE);
                        expensesupload.setText("File Uploaded Successfully");
                    }
                    else if(spinner1.getSelectedItem().toString().equals("Conveyances")) {
                        progressBar2.setVisibility(View.GONE);
                        conveyanceupload.setText("File Uploaded Successfully");
                    }


                    // mMessagesDatabaseReference.push().setValue(friendlyMessage);
                    String id = mDatabaseReference.push().getKey();
                    Reimbursement upload = new Reimbursement(spinner1.getSelectedItem().toString(), mobilemonth1.getSelectedItem().toString(),
                            mobileyear1.getSelectedItem().toString(), editText.getText().toString(),
                            editText1.getText().toString(), editText2.getText().toString(),
                            editText3.getText().toString(), spinner2.getSelectedItem().toString(), editText4.getText().toString(),
                            downloadUri.toString(),EMPID,MOBILE,Update,id);
                    mDatabaseReference.child(spinner1.getSelectedItem().toString()).child(id).setValue(upload);

                } else {
                    Toast.makeText(ReimbursementUpload.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    //////General Expenses


    private void getGeneralPDF() {

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_PDF_CODE);
    }


    private void uploadGeneralFile(Uri data) {
        progressBar1.setVisibility(View.VISIBLE);
        final StorageReference sRef = mStorageReference.child(STORAGE_PATH_UPLOADS).child(spinner1.getSelectedItem().toString())
                .child(spinner1.getSelectedItem().toString()+EMPID+GENERAL+ ".pdf");
        sRef.putFile(data)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return sRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    //String TempImageName = ImageName.getText().toString().trim();


                    if(spinner1.getSelectedItem().toString().equals("Mobile")) {
                        progressBar.setVisibility(View.GONE);
                        mobileupload.setText("File Uploaded Successfully");
                    }
                    else if(spinner1.getSelectedItem().toString().equals("General Expenses")) {
                        progressBar1.setVisibility(View.GONE);
                        expensesupload.setText("File Uploaded Successfully");
                    }
                    else if(spinner1.getSelectedItem().toString().equals("Conveyances")) {
                        progressBar2.setVisibility(View.GONE);
                        conveyanceupload.setText("File Uploaded Successfully");
                    }

                    String id = mDatabaseReference.push().getKey();
                    Reimbursement upload = new Reimbursement(spinner1.getSelectedItem().toString(), generalmonth.getSelectedItem().toString(),
                            generalyear.getSelectedItem().toString(), generalspinner1.getSelectedItem().toString(),
                            expenseseditText1.getText().toString(), expenseseditText2.getText().toString(),
                            expensesspinner2.getSelectedItem().toString(), expenseseditText3.getText().toString(),
                            downloadUri.toString(),EMPID,GENERAL,Update,id);

                    mDatabaseReference.child(spinner1.getSelectedItem().toString()).child(id).setValue(upload);


                } else {
                    Toast.makeText(ReimbursementUpload.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


///////Conveyances


    private void getConveyancesPDF() {

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_PDF_CODE);
    }


    private void uploadConveyancesFile(Uri data) {
        progressBar2.setVisibility(View.VISIBLE);
        final StorageReference sRef = mStorageReference.child(STORAGE_PATH_UPLOADS).child(spinner1.getSelectedItem().toString())
                .child(spinner1.getSelectedItem().toString()+EMPID+CONVEYANCES+ ".pdf");

        sRef.putFile(data)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return sRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    if(spinner1.getSelectedItem().toString().equals("Mobile")) {
                        progressBar.setVisibility(View.GONE);
                        mobileupload.setText("File Uploaded Successfully");
                    }
                    else if(spinner1.getSelectedItem().toString().equals("General Expenses")) {
                        progressBar1.setVisibility(View.GONE);
                        expensesupload.setText("File Uploaded Successfully");
                    }
                    else if(spinner1.getSelectedItem().toString().equals("Conveyances")) {
                        progressBar2.setVisibility(View.GONE);
                        conveyanceupload.setText("File Uploaded Successfully");
                    }

                    String id = mDatabaseReference.push().getKey();
                    Reimbursement upload = new Reimbursement(spinner1.getSelectedItem().toString(), conveyancemonth1.getSelectedItem().toString(),
                            conveyanceyear1.getSelectedItem().toString(), conveyanceSpinner1.getSelectedItem().toString(),
                            conveyanceeditText1.getText().toString(), conveyanceeditText2.getText().toString(),
                            conveyanceeditText3.getText().toString(), conveyanceeditText4.getText().toString(),
                            conveyanceeditText5.getText().toString(), conveyanceeditText6.getText().toString(),
                            conveyanceSpinner2.getSelectedItem().toString(),
                            conveyancetextview.getText().toString(), downloadUri.toString(),EMPID,
                            CONVEYANCES,conveyancesbill.getText().toString(),Update,id);

                    mDatabaseReference.child(spinner1.getSelectedItem().toString()).child(id).setValue(upload);


                } else {
                    Toast.makeText(ReimbursementUpload.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
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
            Intent i = new Intent(ReimbursementUpload.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
