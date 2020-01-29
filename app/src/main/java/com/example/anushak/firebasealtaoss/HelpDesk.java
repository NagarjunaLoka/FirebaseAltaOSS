package com.example.anushak.firebasealtaoss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpDesk extends AppCompatActivity {

    EditText name,Email,Mobile,comment;
    Button submit;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_help_desk);

        databaseReference = FirebaseDatabase.getInstance().getReference("ContactUs");

        name = (EditText)findViewById(R.id.name);
        Email = (EditText)findViewById(R.id.email);
        Mobile = (EditText)findViewById(R.id.mobile);
        comment = (EditText)findViewById(R.id.comment);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                sendEmail();
            }
        });
    }

    private void addData(){
        String Name = name.getText().toString();
        String email = Email.getText().toString();
        String mobile = Mobile.getText().toString();
        String Comment = comment.getText().toString();

        if(!TextUtils.isEmpty(email)){
            String id = databaseReference.push().getKey();

            Feedback feedback = new Feedback(id,Name,email, mobile, Comment);
            databaseReference.child(id).setValue(feedback);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data  not Inserted", Toast.LENGTH_SHORT).show();
        }

    }

    private void sendEmail() {
        //Getting content for email
        String email = Email.getText().toString().trim();
        String subject = name.getText().toString().trim();
        String message = comment.getText().toString().trim();
        String mobile = Mobile.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message, mobile);

        //Executing sendmail to send email
        sm.execute();
    }
}
