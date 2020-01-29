package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CC_Camera extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Login_Timings_Adapter adapter;
    private List<Login_Timings_Class> loginlist;
    DatabaseReference dbref;

    Button location;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_cc__camera);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loginlist = new ArrayList<>();
        adapter = new Login_Timings_Adapter(this, loginlist);
        recyclerView.setAdapter(adapter);
        location=findViewById(R.id.location);
        tv1=findViewById(R.id.tv1);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:12.803915,77.514190"));
                startActivity(intent);

            }
        });



        dbref = FirebaseDatabase.getInstance().getReference("login_details");


        dbref.addListenerForSingleValueEvent(valueEventListener);

      /*  recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:12.803915,77.514190"));
                startActivity(intent);
            }
        });*/


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            loginlist.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Login_Timings_Class logintime = snapshot.getValue(Login_Timings_Class.class);
                    loginlist.add(logintime);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



}