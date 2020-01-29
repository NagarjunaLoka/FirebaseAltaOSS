package com.example.anushak.firebasealtaoss;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.fingerprint.FingerprintManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anushak.firebasealtaoss.data.SharedPreferenceHelper;
import com.example.anushak.firebasealtaoss.data.StaticConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    TextView register,visitor;
    EditText email, password;
    Button login;
    private FirebaseAuth Auth;
    TextView forgotpassword;
    TextView empid,deptt;
    String Empid;
    TextView locationText;
    LocationManager locationManager;
    TextView time,time1;
    DatabaseReference dbref,dref_face,logtimedbref,dbref1;
    FirebaseDatabase fdatabse;
    TextView empid4,pref;
    ProgressBar progressBar;
    ImageView mFingerprintImage;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private Cipher cipher;
    private String KEY_NAME = "AndroidKey";
    String name;

    String EMP;
     private boolean fingeraccess=false;

    private static final String LOG_TAG = "FACE API";
    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    private ImageView imageView;
    private Uri imageUri;
    private FaceDetector detector;
    private static final int REQUEST_WRITE_PERMISSION = 200;
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_BITMAP = "bitmap";
    private static final String SAVED_INSTANCE_RESULT = "result";
    Bitmap editedBitmap;

    TextView smile,lefteye,righteye;
    private static String TAG = "MainActivity";
    private AuthUtils2 authUtils2;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);

        deptt = findViewById(R.id.deptt);

        Auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);

        if(checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }

        //Face Access
        if (savedInstanceState != null) {
            editedBitmap = savedInstanceState.getParcelable(SAVED_INSTANCE_BITMAP);
            if (savedInstanceState.getString(SAVED_INSTANCE_URI) != null) {
                imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
            }
            imageView.setImageBitmap(editedBitmap);
            scanResults.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
        }

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        forgotpassword = findViewById(R.id.forgot);
        time=findViewById(R.id.time);
        time1=findViewById(R.id.sam);
        visitor=findViewById(R.id.visitor);
        empid = findViewById(R.id.empid);
        locationText = (TextView) findViewById(R.id.locationText);

        fdatabse = FirebaseDatabase.getInstance();
        logtimedbref = fdatabse.getReference("login_details");

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                //  Toast.makeText(MainActivity.this,"halooo",Toast.LENGTH_LONG).show();

                final String nag = email.getText().toString();

                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
               // Query queryr=ref.orderByChild("email");//.child("email");
                final Query query=ref.orderByChild("email").equalTo(nag);

                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                        if (dataSnapshot.exists()) {
                            // Toast.makeText(MainActivity.this, "nagarjunaa", Toast.LENGTH_SHORT).show();
                            Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                            name = (String) map.get("department");
                            deptt.setText(name);

                            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(MainActivity.this, "value not fetching", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "value not fetching", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        SharedPreferences fingerprint=  getSharedPreferences("FingerPrint",MODE_PRIVATE);

        if ((Boolean)fingerprint.getBoolean("fingeraccess",Boolean.parseBoolean(""))){
            fingeraccess=fingerprint.getBoolean("fingeraccess",Boolean.parseBoolean(""));
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewEmployee.class));
                finish();
            }
        });

       /* visitor.setOnClickListener(new View.OnClickListener() {
            @Overrides3w-=
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Visitor.class));
            }
        });*/

   /*     GregorianCalendar gregorianCalendar=new GregorianCalendar();
        String date1=String.valueOf(gregorianCalendar.get(Calendar.DATE));
        String month=String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        String year=String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        String hours=String.valueOf(gregorianCalendar.get(Calendar.HOUR));
        String min=String.valueOf(gregorianCalendar.get(Calendar.MINUTE));
        String sec=String.valueOf(gregorianCalendar.get(Calendar.SECOND));

        time1.setText(month);
        String j=time1.getText().toString();
        int re=Integer.valueOf(j)+1;

        time.setText(year+"-"+re+"-"+date1+"::"+hours+":"+min+":"+sec);*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Loading...!");
                progressDialog.show();

                String Email = email.getText().toString();
                final String Password = password.getText().toString();

                Empid = empid.getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Empid)) {
                    Toast.makeText(MainActivity.this, "Please select department", Toast.LENGTH_SHORT).show();
                    return;
                }

                Auth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Sign in Error...! \n Please check your internet connection and try again", Toast.LENGTH_SHORT).show();

                                } else {

                                    AuthUtils2 authUtils2=new AuthUtils2();
                                    authUtils2.saveUserInfo();
                                    StaticConfig.UID=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    Toast.makeText(MainActivity.this, "SignIn Successfull...!", Toast.LENGTH_SHORT).show();

                                 /*   Intent intent = new Intent(MainActivity.this, Security.class);
                                    intent.putExtra("empid", Empid);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);*/
                                    //addUpdate();
                                    if (fingeraccess){

                                        SecurityDailog();

                                    }
                                    else{

                                        dbref1 = FirebaseDatabase.getInstance().getReference().child("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                        dbref1.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                String k = dataSnapshot.child("empid").getValue().toString();
                                                EMP=k;
                                                foo();

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    }



                                }

                            }
                        });

            }
        });


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getLocation();
                if(!email.getText().toString().isEmpty()){
                    Auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this, "Reset Link has been sent to mail", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter Mail Id", Toast.LENGTH_SHORT).show();
                }

            }
        });

        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MainActivity.this,IMEI.class);
                startActivity(intent);*/

                Intent intent = new Intent(MainActivity.this,Visitor.class);
                startActivity(intent);
            }
        });

    }


    private  boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        int location = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int phone = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int storage = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (location != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (phone != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

   /* void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText() + "\n" + addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
        } catch (Exception e) {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    void saveUserInfo() {
        FirebaseDatabase.getInstance().getReference().child("user/" + StaticConfig.UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap hashUser = (HashMap) dataSnapshot.getValue();
                com.example.anushak.firebasealtaoss.model.User userInfo = new User();
                userInfo.name = (String) hashUser.get("name");
                userInfo.email = (String) hashUser.get("email");
                userInfo.avata = (String) hashUser.get("avata");
                SharedPreferenceHelper.getInstance(MainActivity.this).saveUserInfo(userInfo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

/*
    private void addUpdate(){
        String Email = email.getText().toString();
        String Time = time.getText().toString();
        String Location = locationText.getText().toString();

        //  if(!TextUtils.isEmpty(Location)){

        String id = logtimedbref.push().getKey();
        //  String id = Empid;// this is for creating table in firebase  with employee id
        Login_Timings_Class login = new Login_Timings_Class(id,Email,Time,Location);

        logtimedbref.child(id).setValue(login);

        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

     */
/*   }else{
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
        }*//*

    }
*/

    public void onRadioButtonClicked(View v) {

        String dept=deptt.getText().toString();



        if (TextUtils.isEmpty(dept)) {
            Toast.makeText(MainActivity.this, "Please enter Registered Email Id", Toast.LENGTH_SHORT).show();
            return;
        }
        //require to import the RadioButton class
        RadioButton rb1 = (RadioButton) findViewById(R.id.emprd);
        RadioButton rb2 = (RadioButton) findViewById(R.id.hrrd);
        RadioButton rb3 = (RadioButton) findViewById(R.id.adminrd);

        if(name.equalsIgnoreCase("Employee") ){

            rb1.setChecked(true);
        }else if(name.equalsIgnoreCase("Hr")){

            rb3.setChecked(false);

        }else {


        }


        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {

            case R.id.adminrd:
                if (checked)
                    empid.setText("Admin");
                break;

            case R.id.hrrd:
                if (checked)
                    empid.setText("HR");
                break;

            case R.id.emprd:
                if (checked)
                    empid.setText("Employee");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loginmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.aboutus) {
            Intent intent = new Intent(MainActivity.this, AboutUs.class);
            startActivity(intent);
        }
        if (id == R.id.helpdesk) {
            Intent intent = new Intent(MainActivity.this, HelpDesk.class);
            startActivity(intent);
        }
        if (id == R.id.contactus) {
            Intent intent = new Intent(MainActivity.this, ContactUs.class);
            startActivity(intent);
        }
        if (id == R.id.services) {
            Intent intent=new Intent(MainActivity.this,Services.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void SecurityDailog() {
        progressDialog.dismiss();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.security_dialog, null);
        dialogBuilder.setView(dialogView);

        empid4 =dialogView.findViewById(R.id.EMPID);
        pref = dialogView.findViewById(R.id.pref);

        final Button finger =dialogView.findViewById(R.id.finger4);
        final Button face =dialogView.findViewById(R.id.face);
        final Button login =dialogView.findViewById(R.id.login);
        smile=dialogView.findViewById(R.id.smile);
        lefteye=dialogView.findViewById(R.id.lefteye);
        righteye=dialogView.findViewById(R.id.righteye);
        scanResults=dialogView.findViewById(R.id.results);
        imageView = dialogView.findViewById(R.id.scannedResults);

        dbref = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String k = dataSnapshot.child("empid").getValue().toString();
                EMP=k;
                empid4.setText(k);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        /////////////////////////////////////
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Preferences");
        // Query query = rootRef.child("Preferences");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(empid4.getText().toString())) {
                    Toast.makeText(MainActivity.this, empid4.getText().toString(), Toast.LENGTH_SHORT).show();
                    pref.setText(empid4.getText().toString());
                }
                else {
                    Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();
                    pref.setText("NO");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
            }
        };
        userNameRef.addListenerForSingleValueEvent(eventListener);





        finger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  progressBar = dialogView.findViewById(R.id.visprogress);

                if(pref.getText().toString().equals(empid4.getText().toString())) {
                    FingerAuth();
                }
                else {
                    Toast.makeText(MainActivity.this, "Contact Admin for PREFERENCES to login into Dashboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

       /* face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please use Finger authentication for Login", Toast.LENGTH_SHORT).show();
            }
        });*/

        detector = new FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(MainActivity.this, new
                        String[]{WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
                face.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Smile = smile.getText().toString();
                String Lefteye = lefteye.getText().toString();
                String Righteye = righteye.getText().toString();
                String Empid4 = empid4.getText().toString();

                faceaccess();

            }
        });

        final AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void FingerAuth() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.finger_auth_dialog, null);
        dialogBuilder.setView(dialogView);

        mFingerprintImage = dialogView.findViewById(R.id.fingerprintImage);
        TextView mParaLabel = dialogView.findViewById(R.id.paraLabel);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                mParaLabel.setText("Fingerprint Scanner not detected in Device");
            } else if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

                mParaLabel.setText("Permission not granted to use Fingerprint Scanner");

            } else if (!keyguardManager.isKeyguardSecure()) {

                mParaLabel.setText("Add Lock to your Phone in Settings");

            } else if (!fingerprintManager.hasEnrolledFingerprints()) {

                mParaLabel.setText("You should add atleast 1 Fingerprint to use this Feature");

            } else {

                mParaLabel.setText("Place your Finger on Scanner to Access the App.");

                generateKey();

                if (cipherInit()) {

                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler fingerprintHandler = new FingerprintHandler(MainActivity.this);
                    fingerprintHandler.startAuth(fingerprintManager, cryptoObject);
                }
            }
        }

        final AlertDialog finger = dialogBuilder.create();
        finger.show();
    }

    public void foo() {
        progressDialog.dismiss();
        if ((Empid.equalsIgnoreCase("Employee")) && ((EMP.toString().startsWith("A")) || (EMP.toString().startsWith("H")) || (EMP.toString().startsWith("E")))) {
            Toast.makeText(getApplicationContext(), "Employee Dashboard", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, EmployeeDashboard.class);
            intent.putExtra("empid",EMP.toString());
            startActivity(intent);
        }

        if ((Empid.equalsIgnoreCase("HR")) && ((EMP.toString().startsWith("A")) || (EMP.toString().startsWith("H")))) {
            Toast.makeText(getApplicationContext(), "HR Dashboard", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, HrDashboard.class);
            intent.putExtra("empid",EMP.toString());
            startActivity(intent);
        }

        if ((Empid.equalsIgnoreCase("Admin")) && ((EMP.toString().startsWith("A")))) {
            Toast.makeText(getApplicationContext(), "Admin Dashboard", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, AdminDashboard.class);
            intent.putExtra("empid",EMP.toString());
            startActivity(intent);
        }
      /*  if((Empid.equalsIgnoreCase("Admin")) && ((editTextName.getText().toString().startsWith("A")) || (editTextName.getText().toString().startsWith("H")))) {
            Toast.makeText(getApplicationContext(), "You are not Admin", Toast.LENGTH_LONG).show();
            mParaLabel.setVisibility(View.GONE);
        }

        if((Text.equalsIgnoreCase("HR")) && ((empid.getText().toString().startsWith("A")))) {
            Toast.makeText(getApplicationContext(), "You are not HR", Toast.LENGTH_LONG).show();
            mParaLabel.setVisibility(View.GONE);
        }*/
    }

    //finger print auth method

    @TargetApi(Build.VERSION_CODES.M)
    private void generateKey() {

        try {

            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();

        } catch (KeyStoreException | IOException | CertificateException
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | NoSuchProviderException e) {

            e.printStackTrace();

        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {

            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return true;

        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }

    }

    //face access
    public void faceaccess() {
        //  mreference = FirebaseDatabase.getInstance().getReference("FaceValues");
        //Query query=mreference.orderByChild("empid").equalTo("L1001");

        dref_face = FirebaseDatabase.getInstance().getReference("FaceValues");
        Query lastQuery = dref_face.child(empid4.getText().toString());
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String Smile = dataSnapshot.child("smile").getValue().toString();
                String Lefteye = dataSnapshot.child("lefteye").getValue().toString();
                String Righteye = dataSnapshot.child("righteye").getValue().toString();


                com.example.anushak.firebasealtaoss.User user=new com.example.anushak.firebasealtaoss.User();
                HashMap mapUser=(HashMap)dataSnapshot.getValue();
                user.smile=(String)mapUser.get("smile");
                user.lefteye=(String)mapUser.get("lefteye");
                user.righteye=(String)mapUser.get("righteye");

                //Toast.makeText(MainActivity.this, Smile+"\n"+Lefteye+"\n"+Righteye, Toast.LENGTH_LONG).show();

                if(Smile.equals(user.smile))
                {
                    Toast.makeText(MainActivity.this, Smile, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, EmployeeDashboard.class);
                    intent.putExtra("empid",empid4.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "NO"+Smile, Toast.LENGTH_LONG).show();

                }


              /*  if (dataSnapshot.getValue() != null) {

                    String Smile = dataSnapshot.child("smile").getValue().toString();
                    String Lefteye = dataSnapshot.child("lefteye").getValue().toString();
                    String Righteye = dataSnapshot.child("righteye").getValue().toString();

                    smile.setText(Smile);
                    lefteye.setText(Lefteye);
                    righteye.setText(Righteye);

                    Toast.makeText(MainActivity.this, smile.getText().toString(), Toast.LENGTH_LONG).show();

                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

      /*  dref_face = FirebaseDatabase.getInstance().getReference("FaceValues").child(empid4.getText().toString());
        dref_face.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                com.example.anushak.firebasealtaoss.User user=new com.example.anushak.firebasealtaoss.User();
                HashMap mapUser=(HashMap)dataSnapshot.getValue();
                user.smile=(String)mapUser.get("smile");
                user.lefteye=(String)mapUser.get("lefteye");
                user.righteye=(String)mapUser.get("righteye");
                Toast.makeText(MainActivity.this, user.smile, Toast.LENGTH_LONG).show();
               if((smile.getText().toString().equals(user.smile))||(lefteye.getText().toString().equals(user.lefteye))||(righteye.getText().toString().equals(user.righteye)))
                //if((smile.getText().toString().equals(user.smile)))
                {
                    Toast.makeText(MainActivity.this, "Employee Dashboard", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, EmployeeDashboard.class);
                    intent.putExtra("empid",empid4.getText().toString());
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); */

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
            launchMediaScanIntent();
            try {
                scanFaces();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT).show();
                Log.e(LOG_TAG, e.toString());
            }
        }
    }

    private void scanFaces() throws Exception {
        Bitmap bitmap = decodeBitmapUri(this, imageUri);
        if (detector.isOperational() && bitmap != null) {
            editedBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                    .getHeight(), bitmap.getConfig());
            float scale = getResources().getDisplayMetrics().density;
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.rgb(255, 61, 61));
            paint.setTextSize((int) (14 * scale));
            paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3f);
            Canvas canvas = new Canvas(editedBitmap);
            canvas.drawBitmap(bitmap, 0, 0, paint);
            Frame frame = new Frame.Builder().setBitmap(editedBitmap).build();
            SparseArray<Face> faces = detector.detect(frame);
            scanResults.setText(null);
            for (int index = 0; index < faces.size(); ++index) {
                Face face = faces.valueAt(index);
                canvas.drawRect(
                        face.getPosition().x,
                        face.getPosition().y,
                        face.getPosition().x + face.getWidth(),
                        face.getPosition().y + face.getHeight(), paint);
                scanResults.setText(scanResults.getText() + "Face " + (index + 1) + "\n");
                scanResults.setText(scanResults.getText() + "Smile probability:" + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(face.getIsSmilingProbability()) + "\n");
                scanResults.setText(scanResults.getText() + "Left Eye Open Probability: " + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(face.getIsLeftEyeOpenProbability()) + "\n");
                scanResults.setText(scanResults.getText() + "Right Eye Open Probability: " + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(face.getIsRightEyeOpenProbability()) + "\n");
                scanResults.setText(scanResults.getText() + "---------" + "\n");

                smile.setText(face.getIsSmilingProbability() + "\n");
                lefteye.setText(face.getIsLeftEyeOpenProbability() + "\n");
                righteye.setText(face.getIsRightEyeOpenProbability() + "\n");

                for (Landmark landmark : face.getLandmarks()) {
                    int cx = (int) (landmark.getPosition().x);
                    int cy = (int) (landmark.getPosition().y);
                    canvas.drawCircle(cx, cy, 5, paint);
                }


/////////////////////////////////
//                String  Smile = smile.getText().toString();
//                String Lefteye = lefteye.getText().toString();
//                String Righteye = righteye.getText().toString();
//                String Empid4 = empid4.getText().toString();
//
                faceaccess();

            }

            if (faces.size() == 0) {
                scanResults.setText("Scan Failed: Found nothing to scan");
            } else {
                imageView.setImageBitmap(editedBitmap);
                scanResults.setText(scanResults.getText() + "No of Faces Detected: " + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(faces.size()) + "\n");
                scanResults.setText(scanResults.getText() + "---------" + "\n");
            }
        } else {
            scanResults.setText("Could not set up the detector!");
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture.jpg");
        imageUri = FileProvider.getUriForFile(MainActivity.this,
                BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (imageUri != null) {
            outState.putParcelable(SAVED_INSTANCE_BITMAP, editedBitmap);
            outState.putString(SAVED_INSTANCE_URI, imageUri.toString());
            outState.putString(SAVED_INSTANCE_RESULT, scanResults.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }
    class AuthUtils2 {
        void saveUserInfo() {
            FirebaseDatabase.getInstance().getReference().child("user/" + StaticConfig.UID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    HashMap hashUser = (HashMap) dataSnapshot.getValue();
                    com.example.anushak.firebasealtaoss.model.User userInfo = new com.example.anushak.firebasealtaoss.model.User();
                    userInfo.name = (String) hashUser.get("name");
                    userInfo.email = (String) hashUser.get("email");
                    userInfo.avata = (String) hashUser.get("avata");
                    SharedPreferenceHelper.getInstance(MainActivity.this).saveUserInfo(userInfo);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}