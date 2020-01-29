package com.example.anushak.firebasealtaoss;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendanceNew extends FragmentActivity implements LocationListener, OnMapReadyCallback{
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 5445;

    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentLocationMarker;
    private Location currentLocation;
    private boolean firstTimeFlag = true;

    private final View.OnClickListener clickListener = view -> {
        if (view.getId() == R.id.currentLocationImageButton && googleMap != null && currentLocation != null)
            animateCamera(currentLocation);
    };

    Button intime, outtime;
    Context context;
    ImageView applyleave,query;

    android.support.v7.widget.Toolbar toolbar;
    TextView userid,text,usertype;
    String UserId,Text,UserType;
    HorizontalScrollView hsv1,hsv2;
    //Paths
    ImageView iv,iv1;
    TextView dashboard,dashboard1;

    TextView notetxt,serviceno,modelno,outserviceno,outmodelno,newtime;
    EditText note,outnote;
    String Note,Serviceno,Modelno,OutServiceno,OutModelno;
    TextView empid, date, time, locationtext, monthandyear, delayedtime, outingtime;
    DatabaseReference databaseReference, dbref, fdatabase;
    LocationManager locationManager;

    private static final String TAG = AttendanceNew.class.getSimpleName();

    @BindView(R.id.lat)
    TextView lat;

    @BindView(R.id.longi)
    TextView longi;

    @BindView(R.id.updated_on)
    TextView txtUpdatedOn;

    private String mLastUpdateTime;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;

    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;

    String serial_no,Empid;

    Button history;
    String date1,month,year;

    //  private static String TAG = "AttendanceNew";

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_new);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        supportMapFragment.getMapAsync(this);
        findViewById(R.id.currentLocationImageButton).setOnClickListener(clickListener);

        history = findViewById(R.id.history);
        note = findViewById(R.id.note);
        outnote = findViewById(R.id.outnote);
        notetxt = findViewById(R.id.notetxt);
        serviceno = findViewById(R.id.serviceno);
        modelno = findViewById(R.id.modelno);
        outserviceno = findViewById(R.id.outserviceno);
        outmodelno = findViewById(R.id.outmodelno);
        newtime = findViewById(R.id.newtime);
        delayedtime = findViewById(R.id.delayedtime);
        outingtime = findViewById(R.id.outingtime);
        applyleave = findViewById(R.id.applyleave);
        query = findViewById(R.id.query);

        Serviceno = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);

        Modelno = Build.MANUFACTURER
                + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                + " " + Build.VERSION_CODES.class.getFields()[Build.VERSION.SDK_INT].getName();

        serviceno.setText(Serviceno);
        modelno.setText(Modelno);

        outserviceno.setText(Serviceno);
        outmodelno.setText(Modelno);

        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);
        userid = (TextView)findViewById(R.id.userid);

        empid = findViewById(R.id.empid);
        intime = findViewById(R.id.intime);
        outtime = findViewById(R.id.outtime);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        monthandyear = findViewById(R.id.monthandyear);
        locationtext = findViewById(R.id.locationtext);

        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);

        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);

        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        UserType = getIntent().getStringExtra("usertype");
        UserId = getIntent().getStringExtra("userId");

        usertype.setText(UserType);
        userid.setText(UserId);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttendanceNew.this, EmpAttendanceHistory.class);
                intent.putExtra("empid",Empid);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                startActivity(intent);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttendanceNew.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(AttendanceNew.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("employee")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(UserType.equals("employee"))
        {
            text.setText("Employee Dashboard - Attendance");
        }
        else
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Attendance");
        }

        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();

        ButterKnife.bind(this);

        getlocation();

        // initialize the necessary libraries
        init();

        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            openSettings();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();


        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        date1 = String.valueOf(gregorianCalendar.get(Calendar.DATE));
        month = String.valueOf(gregorianCalendar.get(Calendar.MONTH));
        year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));

        String Date = date.getText().toString();
        int re1 = Integer.valueOf(Date) + 1;
        date.setText(year + "-" + re1 + "-" + date1);

        String Monthandyear = monthandyear.getText().toString();
        int re2 = Integer.valueOf(Monthandyear) + 1;
        monthandyear.setText(year + "-" + re2);

        intime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = txtUpdatedOn.getText().toString();
                if (time.isEmpty()) {
                    Toast.makeText(AttendanceNew.this, "Something went wrong Please logged in again", Toast.LENGTH_SHORT).show();
                }
                else{
                    intime();
                }
            }
        });

        outtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outtime();
            }
        });

        applyleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AttendanceNew.this,Leave.class);
                intent.putExtra("userId",UserId);
                intent.putExtra("usertype",UserType);
                intent.putExtra("empid",Empid);
                startActivity(intent);
            }
        });

        Text = text.getText().toString();
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AttendanceNew.this, QueryForm.class);
                i.putExtra("message",Text);
                i.putExtra("userId",UserId);
                startActivity(i);
            }
        });
    }
    // on Create ends

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private void startCurrentLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AttendanceNew.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                return;
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status)
            return true;
        else {
            if (googleApiAvailability.isUserResolvableError(status))
                Toast.makeText(this, "Please Install google play services to use this application", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                Toast.makeText(this, "Permission denied by uses", Toast.LENGTH_SHORT).show();
            else if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startCurrentLocationUpdates();
        }
    }

    private void animateCamera(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(16).build();
    }

    private void showMarker(@NonNull Location currentLocation) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        if (currentLocationMarker == null)
            currentLocationMarker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng));
        else
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());
    }

    public void getlocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        locationtext.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationtext.setText(addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
        } catch (Exception e) {

        }

    }

    @Override

    public void onProviderDisabled(String provider) {
        Toast.makeText(AttendanceNew.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        mLocationCallback = new LocationCallback() {
            @SuppressLint("NewApi")
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                updateLocationUI();

                if (locationResult.getLastLocation() == null)
                    return;
                currentLocation = locationResult.getLastLocation();
                if (firstTimeFlag && googleMap != null) {
                    animateCamera(currentLocation);
                    firstTimeFlag = false;
                }
                showMarker(currentLocation);
            }
        };

        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    @SuppressLint("NewApi")
    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

        updateLocationUI();
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void updateLocationUI() {
        if (mCurrentLocation != null) {
            lat.setText(
                    ""+mCurrentLocation.getLatitude()
            );

            longi.setText(
                            ""+ mCurrentLocation.getLongitude()
            );

            // giving a blink animation on TextView
            lat.setAlpha(0);
            longi.setAlpha(0);
            lat.animate().alpha(1).setDuration(300);
            longi.animate().alpha(1).setDuration(300);

            // location last updated time
            txtUpdatedOn.setText(mLastUpdateTime);

            String startDate = txtUpdatedOn.getText().toString();
            String stopDate = "09:30:00 AM";
            String endDate = "03:00:00 PM";

            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ss a");
            Date date1 = null;
            Date date2 = null;
            Date date3 = null;
            try {
                date1 = parseFormat.parse(startDate);
                date2 = parseFormat.parse(stopDate);
                date3 = parseFormat.parse(endDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            long diff = date2.getTime() - date1.getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            newtime.setText(diffSeconds+"seconds.");

            long diff1 = date1.getTime() - date2.getTime();
            long diffHours1 = diff1 / (60 * 60 * 1000);
            long diffMinutes1 = diff1 / (60 * 1000);
            long diffSeconds1 = diff1 / 1000;
            delayedtime.setText(diffHours1+"hrs :"+diffMinutes1+"mins :"+diffSeconds1+"secs");

            long diff2 = date3.getTime() - date1.getTime();
            long diffHours2 = diff2 / (60 * 60 * 1000);
            long diffMinutes2 = diff2 / (60 * 1000);
            long diffSeconds2 = diff2 / 1000;
            outingtime.setText(diffHours2+"hrs :"+diffMinutes2+"mins :"+diffSeconds2+"secs");

// Custom date format
          /*  SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss aa");

            Date d1 = null;
            Date d2 = null;
            Date d3 = null;
            try {
                d1 = format.parse(startDate);
                d2 = format.parse(stopDate);
                d3 = format.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

// Get msec from each, and subtract.
            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            newtime.setText(diffSeconds+"seconds.");*/

           // System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));

           /* long diff1 = d1.getTime() - d2.getTime();
            long diffHours1 = diff1 / (60 * 60 * 1000);
            long diffMinutes1 = diff1 / (60 * 1000);
            long diffSeconds1 = diff1 / 1000;
            delayedtime.setText(diffHours1+"hrs :"+diffMinutes1+"mins :"+diffSeconds1+"secs");

            long diff2 = d3.getTime() - d1.getTime();
            long diffHours2 = diff2 / (60 * 60 * 1000);
            long diffMinutes2 = diff2 / (60 * 1000);
            long diffSeconds2 = diff2 / 1000;
            outingtime.setText(diffHours2+"hrs :"+diffMinutes2+"mins :"+diffSeconds2+"secs");*/

       /* System.out.println("Time in seconds: " + diffSeconds + " seconds.");
        System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        System.out.println("Time in hours: " + diffHours + " hours.");*/

           /* if(newtime.getText().toString().startsWith("-"))
            {
                notetxt.setVisibility(View.VISIBLE);
                note.setVisibility(View.VISIBLE);
            }
            else {
                notetxt.setVisibility(View.GONE);
                note.setVisibility(View.GONE);
            }*/
        }

        //  toggleButtons();


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint({"MissingPermission", "NewApi"})
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                        Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        updateLocationUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(AttendanceNew.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(AttendanceNew.this, errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (fusedLocationProviderClient != null)
            fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }



    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        if (isGooglePlayServicesAvailable()) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            startCurrentLocationUpdates();
        }

        updateLocationUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fusedLocationProviderClient = null;
        googleMap = null;
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }



    public void intime()
    {

        /*Toast toast = Toast.makeText(AttendanceNew.this,"You are delayed by"+delayedtime.getText().toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();*/

        String Intime = txtUpdatedOn.getText().toString();
        final String Empid = empid.getText().toString();
        final String Date = date.getText().toString();
        String Lat = lat.getText().toString();
        String Longi = longi.getText().toString();
        String Address = locationtext.getText().toString();
        String Monthandyear = monthandyear.getText().toString();
        String Status = "Present";
        String Outtime = "not logged out";
        String Outlat = "0.000";
        String Outlongi = "0.000";
        String Outaddress = "address";
        String Serviceno = serviceno.getText().toString();
        String Modelno = modelno.getText().toString();
        String OutServiceno = "serviceno";
        String OutModelno = "modelno";
        String Note = note.getText().toString();
        String OutNote = "not yet logged out";

        if(newtime.getText().toString().startsWith("-")) {

            notetxt.setVisibility(View.VISIBLE);
            note.setVisibility(View.VISIBLE);
            outnote.setVisibility(View.GONE);

            if (note.getText().toString().isEmpty()) {
                note.setError(getString(R.string.input_error_name));
                note.requestFocus();
                return;
            }
        }
            else {
            notetxt.setVisibility(View.GONE);
            note.setVisibility(View.GONE);
            outnote.setVisibility(View.GONE);

                note.setText("In-Time");
                //String Note = "In-Time";
                Note = note.getText().toString();
            }


        //Note = note.getText().toString();


        //String id = dbref.push().getKey();

        final String id = Empid + Date;
        final Login_Timings_Class login = new Login_Timings_Class(id, Intime, Empid, Lat, Longi, Outtime, Date, Address, Status, Monthandyear, Outlat, Outlongi, Outaddress, Note, OutNote, Serviceno, Modelno, OutServiceno, OutModelno);


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Attendance Details").child(Empid);
        // Query query = rootRef.child("Preferences");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(Empid + Date)) {
                    fdatabase = FirebaseDatabase.getInstance().getReference("Attendance Details");
                    dbref = fdatabase.child(Empid);
                    dbref.child(id).setValue(login);
                    Toast.makeText(AttendanceNew.this, "Logged-In successful", Toast.LENGTH_SHORT).show();

                    Toast toast = Toast.makeText(AttendanceNew.this,"You are delayed by"+delayedtime.getText().toString(), Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else if (dataSnapshot.hasChild(Empid + Date)) {
                    Toast.makeText(AttendanceNew.this, "You have already Logged-In", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
            }
        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }

    public void outtime()
    {

        String OutNote = outnote.getText().toString();
        if(!outingtime.getText().toString().startsWith("-")) {

            notetxt.setVisibility(View.VISIBLE);
            note.setVisibility(View.GONE);
            outnote.setVisibility(View.VISIBLE);

            if (outnote.getText().toString().isEmpty()) {
                outnote.setError(getString(R.string.input_error_name));
                outnote.requestFocus();
                return;
            }
        }
        else {
            notetxt.setVisibility(View.GONE);
            note.setVisibility(View.GONE);
            outnote.setVisibility(View.GONE);

            outnote.setText("In-Time");
            OutNote = outnote.getText().toString();
            //String Note = "In-Time";
        }
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outtime");
        // Query query = rootRef.child("Preferences");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             /* if (!dataSnapshot.child("outtime").exists()) {
                  Toast.makeText(AdminAttendance.this, "You have already Logged out", Toast.LENGTH_SHORT).show();
              }
              else*/if(dataSnapshot.exists()) {
                    update();
                    Toast.makeText(AttendanceNew.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AttendanceNew.this, "Not Logged out successfully", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("AdminAttendance", databaseError.getMessage()); //Don't ignore errors!
            }
        };
        userNameRef.addListenerForSingleValueEvent(eventListener);
    }

    public  void update()
    {
        String Punchout = txtUpdatedOn.getText().toString();
        String Outlat = lat.getText().toString();
        String Outlongi = longi.getText().toString();
        String Outaddress = locationtext.getText().toString();
        String OutServiceno = outserviceno.getText().toString();
        String OutModelno = outmodelno.getText().toString();
        String OutNote = outnote.getText().toString();

        updateuser(Punchout, Outlat, Outlongi, Outaddress, OutServiceno, OutModelno, OutNote);
    }

    private boolean updateuser(String outtime, String outlat, String outlongi, String outaddress, String outserviceno, String outmodelno, String outnote)
    {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outtime");
        DatabaseReference dR1 = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outlat");
        DatabaseReference dR2 = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outlongi");
        DatabaseReference dR3 = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outaddress");
        DatabaseReference dR4 = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outserviceno");
        DatabaseReference dR5 = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outmodelno");
        DatabaseReference dR6 = FirebaseDatabase.getInstance().getReference("Attendance Details").child(empid.getText().toString()).child(empid.getText().toString()+date.getText().toString()).child("outnote");


        dR.setValue(outtime);
        dR1.setValue(outlat);
        dR2.setValue(outlongi);
        dR3.setValue(outaddress);
        dR4.setValue(outserviceno);
        dR5.setValue(outmodelno);
        dR6.setValue(outnote);
        return true;
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        UserId = userid.getText().toString();
        UserType = usertype.getText().toString();

        if(id == R.id.query)
        {
            Text = text.getText().toString();
            Intent i = new Intent(AttendanceNew.this, QueryForm.class);
            i.putExtra("message",Text);
            i.putExtra("userId",UserId);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
*/
}