package com.example.anushak.firebasealtaoss;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anushak.firebasealtaoss.data.StaticConfig;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewEmployee extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    private TextView tvreglogin,gender,text,userid,dashboard;
    private Button save;

    EditText editTextEmpid,designation,editTextName,middlename,lastname,editTextEmail,editTextPassword;
    EditText dob,proofnumber,editTextPhone,interviewschedule,interviewdoneby,doj;
    EditText permanentaddress,correspondanceaddress,officialemail,personalemail;
    EditText projectmanagermail,projectmanagerid,hrmail,hrid,accountno,bankname,ifsccode,cardname,answer;
    Spinner department,bloodgroup,idproof,securityquestions,departmenttype;
    ImageView dobdate,interviewdate,dojdate,facereg;

    TextView referalcode;
    String ReferalCode;

    private CircleImageView profileImageView;
    private static final int REQUEST_PICK_PHOTO = 2;
    Uri FilePathUri;

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference;

    // Folder path for Firebase Storage.
    String Storage_Path = "Profile Pictures/";

    ProgressDialog progressDialog ;
    DatePickerDialog datePickerDialog;
    String UserId,Text;
    ImageView iv;
    HorizontalScrollView hsv1;
    Animation fromtop;

    Uri downloadUri;

    private AuthUtils1 authUtils1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;



    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAueTGKP0:APA91bHo7bOJDKkcUV3MJznbAfOhTVZ1A7IEP9fkBk_-70dM0tIYbis1cVhR4cBr-rV8FVN2FtQ7G9-snLfSHLgG_xB_vvvkNmduv7H91OGQMSNwvsQ0ybcxpNkFmiltRn4mBZfSphVe";
    //final private String serverKey = "key=" + "AIzaSyC47VjnJtOcX-g3m-yJ9piXymQNibn2K38";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    private static final String SUBSCRIBE_TO = "userABC";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;

    String NOTIFy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_new_employee);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(NewEmployee.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken", newToken);
                FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                Log.i("FirebaseInsatnceId", "onTokenRefresh completed with token: " + newToken);

            }
        });

        facereg = (ImageView) findViewById(R.id.facereg);
        userid = (TextView) findViewById(R.id.userid);
        text = (TextView) findViewById(R.id.text);
        editTextEmpid = findViewById(R.id.edit_text_empid);
        department = findViewById(R.id.department);
        departmenttype = findViewById(R.id.departmenttype);
        designation = findViewById(R.id.designation);
        editTextName = findViewById(R.id.edit_text_name);
        middlename = findViewById(R.id.middlename);
        lastname = findViewById(R.id.lastname);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        gender = findViewById(R.id.gender);
        dob = findViewById(R.id.dob);
        dobdate = findViewById(R.id.date_dob);
        bloodgroup = findViewById(R.id.etBloodGroup);
        idproof = findViewById(R.id.spidentity);
        proofnumber = findViewById(R.id.etDetails);
        editTextPhone = findViewById(R.id.edit_text_phone);
        interviewschedule = findViewById(R.id.etInterviewSchedule);
        interviewdate = findViewById(R.id.date_Interview);
        interviewdoneby = findViewById(R.id.etInterviewDoneBy);
        doj = findViewById(R.id.etDateofJoining);
        dojdate = findViewById(R.id.date_doj);
        permanentaddress = findViewById(R.id.etPermanentAddress);
        correspondanceaddress = findViewById(R.id.etCorresspondenceAddress);
        officialemail = findViewById(R.id.etOfficialEmail);
        personalemail = findViewById(R.id.etPersonalEmail);
        projectmanagermail = findViewById(R.id.projectmanagermail);
        projectmanagerid = findViewById(R.id.projectmanagerid);
        hrmail = findViewById(R.id.hrmail);
        hrid = findViewById(R.id.hrid);
        accountno = findViewById(R.id.accountno);
        bankname = findViewById(R.id.bankname);
        ifsccode = findViewById(R.id.ifsccode);
        cardname = findViewById(R.id.cardname);
        securityquestions = findViewById(R.id.spinner);
        answer = findViewById(R.id.answer);
        iv = (ImageView) findViewById(R.id.iv);
        dashboard = (TextView) findViewById(R.id.dashboard);
        hsv1 = (HorizontalScrollView) findViewById(R.id.hsv1);
        referalcode = findViewById(R.id.referalcode);

        save = findViewById(R.id.button_register);

      //  tvreglogin = findViewById(R.id.tvreguser);
        profileImageView = (CircleImageView) findViewById(R.id.profileImageView);
        progressDialog = new ProgressDialog(NewEmployee.this);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewEmployee.this, HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        hsv1.setAnimation(fromtop);
        UserId = getIntent().getStringExtra("userId");
        userid.setText(UserId);
        text.setText("HR Dashboard - New Employee Form");

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        mAuth = FirebaseAuth.getInstance();

        dobdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(NewEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dob.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        interviewdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(NewEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                interviewschedule.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        dojdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(NewEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                doj.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        facereg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewEmployee.this,FaceReg.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_register:
                        ReferalCode = referalcode.getText().toString();
                        ReferalCode = editTextEmpid.getText().toString() + editTextPhone.getText().toString();
                        referalcode.setText(ReferalCode);

                        registerUser();
                        //UploadImageFileToFirebaseStorage();

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{officialemail.getText().toString()});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Login Details");
                        i.putExtra(Intent.EXTRA_TEXT, "UserName:" + editTextEmpid.getText().toString() + "   Password:" + editTextPassword.getText().toString() + "   Security Question:" + securityquestions.getSelectedItem().toString() + "   Answer:" + answer.getText().toString() + " ReferalCode:" + referalcode.getText().toString());
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(NewEmployee.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }

                        break;
                }
            }
        });

       /* tvreglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewEmployee.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), REQUEST_PICK_PHOTO);

            }
        });
    }

    public void onRadioButtonClicked(View v)
    {
        RadioButton rb1 = (RadioButton) findViewById(R.id.male);
        RadioButton rb2 = (RadioButton) findViewById(R.id.female);

        boolean  checked = ((RadioButton) v).isChecked();
        switch(v.getId()){

            case R.id.male:
                if(checked)

                    gender.setText("Male");
                break;

            case R.id.female:
                if(checked)
                    gender.setText("Female");
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        final String empid = editTextEmpid.getText().toString().trim();
        final String Department = department.getSelectedItem().toString().trim();
        final String DepartmentType = departmenttype.getSelectedItem().toString().trim();
        final String Designation = designation.getText().toString().trim();
        final String Gender = gender.getText().toString().trim();
        final String Middlename = middlename.getText().toString().trim();
        final String Lastname = lastname.getText().toString().trim();
        final String DOB = dob.getText().toString().trim();
        final String Bloodgroup = bloodgroup.getSelectedItem().toString().trim();
        final String Idproof = idproof.getSelectedItem().toString().trim();
        final String Proofnumber = proofnumber.getText().toString().trim();
        final String Interviewschedule = interviewschedule.getText().toString().trim();
        final String Interviewdoneby = interviewdoneby.getText().toString().trim();
        final String DOJ = doj.getText().toString().trim();
        final String Permanentaddress = permanentaddress.getText().toString().trim();
        final String Correspondanceaddress = correspondanceaddress.getText().toString().trim();
        final String Officialemail = officialemail.getText().toString().trim();
        final String Personalemail = personalemail.getText().toString().trim();
        final String Projectmanagermail = projectmanagermail.getText().toString().trim();
        final String Projectmanagerid = projectmanagerid.getText().toString().trim();
        final String Hrmail = hrmail.getText().toString().trim();
        final String Hrid = hrid.getText().toString().trim();
        final String Accountno = accountno.getText().toString().trim();
        final String Bankname = bankname.getText().toString().trim();
        final String Ifsccode = ifsccode.getText().toString().trim();
        final String Cardname = cardname.getText().toString().trim();
        final String Securityquestions = securityquestions.getSelectedItem().toString().trim();
        final String Answer = answer.getText().toString().trim();
        final String ReferalCode = referalcode.getText().toString();

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextEmpid.setError(getString(R.string.input_error_empid));
            editTextEmpid.requestFocus();
            return;
        }
        if (FilePathUri != null) {



            // Creating second StorageReference.
            final StorageReference storageReference2nd = storageReference.child(Storage_Path + empid + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return storageReference2nd.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    downloadUri = task.getResult();
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        final User user = new User(userID,empid,Department,DepartmentType,Designation,name,Middlename,Lastname,email,password,Gender,DOB,Bloodgroup,
                                                Idproof,Proofnumber,phone,Interviewschedule,Interviewdoneby,DOJ,Permanentaddress,Correspondanceaddress,
                                                Officialemail,Personalemail,Projectmanagermail,Projectmanagerid,Hrmail,Hrid,Accountno,
                                                Bankname,Ifsccode,Cardname,Securityquestions,Answer,ReferalCode,downloadUri.toString());
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(userID)
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {


                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    AuthUtils1 authUtils1=new AuthUtils1();
                                                    authUtils1.initNewUserInfo();
                                                    Intent intent = new Intent(NewEmployee.this, MainActivity.class);
                                                    startActivity(intent);
                                                    Toast.makeText(NewEmployee.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                                } else {
                                                    //display a failure message
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(NewEmployee.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            });
        }


        TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to


        NOTIFICATION_TITLE = editTextName.getText().toString();
        NOTIFICATION_MESSAGE = department.getSelectedItem().toString();

        NOTIFy = "New Employee is added name :  "+NOTIFICATION_TITLE;

        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", NOTIFy);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
        sendNotification(notification);

    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        //editTextEmail.setText("");
                        //  department.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NewEmployee.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_PHOTO && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                profileImageView.setImageBitmap(bitmap);

                // After selecting image change choose button above text.

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;


    }


    //Query
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if (id == R.id.query) {
            Text = text.getText().toString();
            UserId = userid.getText().toString();
            Intent i = new Intent(NewEmployee.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
    class AuthUtils1 {
        void initNewUserInfo() {
            com.example.anushak.firebasealtaoss.model.User newUser = new com.example.anushak.firebasealtaoss.model.User();
            newUser.email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            newUser.name = editTextEmpid.getText().toString().trim();
            StaticConfig.UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
            newUser.avata = StaticConfig.STR_DEFAULT_BASE64;
            FirebaseDatabase.getInstance().getReference().child("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newUser);
        }
    }
}
