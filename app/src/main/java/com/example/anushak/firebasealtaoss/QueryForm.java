package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class QueryForm extends AppCompatActivity {

    TextView empid,subject,emailTV,Mmail,Mid,Hmail,Hid,status,userid;

    DatabaseReference databaseReference,databaseReference1;
    EditText querymessage;
    Button querysubmit;
    String QueryMessage,Empid,Subject,Email,Memail,Mempid,Hemail,Hempid,Status,Userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_query_form);

        userid = (TextView)findViewById(R.id.userid);
        empid = (TextView)findViewById(R.id.empid);
        emailTV = (TextView)findViewById(R.id.email);
        Mid = (TextView)findViewById(R.id.toaddressid1);
        Mmail = (TextView)findViewById(R.id.toaddress1);
        Hid = (TextView)findViewById(R.id.toaddressid2);
        Hmail = (TextView)findViewById(R.id.toaddress2);
        subject = (TextView)findViewById(R.id.subject);
        status = (TextView)findViewById(R.id.status);
        querymessage = (EditText)findViewById(R.id.editText5);
        querysubmit = (Button)findViewById(R.id.querysubmit);

        Userid = getIntent().getStringExtra("userId");
        Subject = getIntent().getStringExtra("message");

        userid.setText(Userid);
        subject.setText(Subject);
        status.setText("Query Updated");

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        Query query = databaseReference.child(Userid);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Queries");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String PersonalEmail = dataSnapshot.child("personalemail").getValue().toString();
                String pmemail = dataSnapshot.child("projectmanagermail").getValue().toString();
                String pmempid = dataSnapshot.child("projectmanagerid").getValue().toString();
                String hremail = dataSnapshot.child("hrmail").getValue().toString();
                String hrempid = dataSnapshot.child("hrid").getValue().toString();
                String Empid = dataSnapshot.child("empid").getValue().toString();

                emailTV.setText(PersonalEmail);
                Mmail.setText(pmemail);
                Mid.setText(pmempid);
                Hmail.setText(hremail);
                Hid.setText(hrempid);
                empid.setText(Empid);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        querysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empid = empid.getText().toString();
                Email = emailTV.getText().toString();
                Memail = Mmail.getText().toString();
                Mempid = Mid.getText().toString();
                Hemail = Hmail.getText().toString();
                Hempid = Hid.getText().toString();
                Subject = subject.getText().toString();
                Status = status.getText().toString();
                QueryMessage = querymessage.getText().toString();
                if(!TextUtils.isEmpty(QueryMessage)){
                    String QueryId = databaseReference1.push().getKey();
                    Equery equery = new Equery(QueryId,Empid,Email,Memail,Mempid,Hemail,Hempid,Subject,Status,QueryMessage);
                    databaseReference1.child(Empid).child(QueryId).setValue(equery);
                    Toast.makeText(QueryForm.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(QueryForm.this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                //i.putExtra(Intent.ACTION_SEND, email);
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{Hemail,Memail});
                i.putExtra(Intent.EXTRA_SUBJECT, Subject);
                //i.putExtra(Intent.EXTRA_TEXT   , Mobile);
                i.putExtra(Intent.EXTRA_TEXT   , QueryMessage);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(QueryForm.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
