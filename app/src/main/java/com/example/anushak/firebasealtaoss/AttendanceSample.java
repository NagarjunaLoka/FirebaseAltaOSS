package com.example.anushak.firebasealtaoss;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class AttendanceSample extends AppCompatActivity {

    TextView empid,date,noofemp;
    LinearLayout linearattend;
    DatabaseReference databaseReference;
    String date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_attendance_sample);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Attendance Details");

        empid = findViewById(R.id.empid);

        date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Toast.makeText(this, date1, Toast.LENGTH_SHORT).show();

        String Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);
        linearattend=findViewById(R.id.linearattend);
        linearattend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }

        });
       /* databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.)

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
       // Query query=databaseReference.orderByChild("date").equalTo(date1);
       // Toast.makeText(this, query.toString(), Toast.LENGTH_SHORT).show();
        /*query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap mapValue = (HashMap) dataSnapshot.getValue();
                String Dat = (String) mapValue.get("status");
                Toast.makeText(AttendanceSample.this, Dat, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
