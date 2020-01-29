package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends AppCompatActivity {

    EditText name,Email,Mobile,comment;
    Button submit;
    DatabaseReference databaseReference;
    TextView location,contact,mobile1,email1,website;
    ImageView fb,insta,twitter,pinterest,youtube,linkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_contact_us);

        databaseReference = FirebaseDatabase.getInstance().getReference("ContactUs");

        name = (EditText)findViewById(R.id.name);
        Email = (EditText)findViewById(R.id.email);
        Mobile = (EditText)findViewById(R.id.mobile);
        comment = (EditText)findViewById(R.id.comment);
        submit = (Button) findViewById(R.id.submit);
        location =  findViewById(R.id.location);
        contact =  findViewById(R.id.contact);
        mobile1 =  findViewById(R.id.mobile1);
        email1 =  findViewById(R.id.email1);
        website =  findViewById(R.id.website);

        fb = (ImageView) findViewById(R.id.fb);
        insta = (ImageView) findViewById(R.id.insta);
        twitter = (ImageView) findViewById(R.id.twitter);
        pinterest = (ImageView) findViewById(R.id.pin);
        youtube = (ImageView) findViewById(R.id.youtube);
        linkedin = (ImageView) findViewById(R.id.linkedin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                sendEmail();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:12.803915,77.514190"));
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:080-28432432"));
                startActivity(intent);
            }
        });

        mobile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7337774647"));
                startActivity(intent);
            }
        });

        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"infoalta@altaitsolutions.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactUs.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.altaitsolutions.com"));
                startActivity(intent);
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/altaitsolutions"));
                startActivity(intent);
            }
        });


        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/altaitsolutions"));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://twitter.com/alta_it"));
                startActivity(intent);
            }
        });

        pinterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://in.pinterest.com/altaitsolutions"));
                startActivity(intent);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCG-9-2kBC3GCwIXlNrFSSsQ?view_as=subscriber"));
                startActivity(intent);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/company/alta-it-solutions/"));
                startActivity(intent);
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
