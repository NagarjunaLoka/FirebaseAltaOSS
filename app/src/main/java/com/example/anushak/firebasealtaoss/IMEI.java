package com.example.anushak.firebasealtaoss;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IMEI extends Activity {

    TextView imei_number;
    Button get_imei;
    String IMEI_Number_Holder;
    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imei);

        imei_number = (TextView) findViewById(R.id.textView1);
        get_imei = (Button) findViewById(R.id.button1);

        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        IMEI_Number_Holder = telephonyManager.getDeviceId();
        imei_number.setText(IMEI_Number_Holder);

/*
        get_imei.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                IMEI_Number_Holder = telephonyManager.getDeviceId();

                imei_number.setText(IMEI_Number_Holder);
            }
        });
*/

    }

}