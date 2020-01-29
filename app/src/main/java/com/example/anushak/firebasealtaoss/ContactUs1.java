package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUs1 extends AppCompatActivity {

    ImageView fb,insta,twitter,pinterest,tumblr,youtube,linkedin,google;
    LinearLayout linear1,linear2,linear3,linear4;
    ImageView back;
    android.support.v7.widget.Toolbar toolbar;
    TextView social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_contact_us1);
        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar);
        social = (TextView)findViewById(R.id.social);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fb = (ImageView) findViewById(R.id.fb);
        insta = (ImageView) findViewById(R.id.insta);
        twitter = (ImageView) findViewById(R.id.twitter);
        pinterest = (ImageView) findViewById(R.id.pin);
        tumblr = (ImageView) findViewById(R.id.tumblr);
        youtube = (ImageView) findViewById(R.id.youtube);
        linkedin = (ImageView) findViewById(R.id.linkedin);
        google = (ImageView) findViewById(R.id.googleplus);

        linear1  = (LinearLayout)findViewById(R.id.linear1);
        linear2  = (LinearLayout)findViewById(R.id.linear2);
        linear3  = (LinearLayout)findViewById(R.id.linear3);
        linear4  = (LinearLayout)findViewById(R.id.linear4);

        SpannableString content = new SpannableString("Follow us on Social Media");
        content.setSpan(new UnderlineSpan(),0,content.length(),0);
        social.setText(content);

        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7337774647"));
                startActivity(intent);
            }
        });

        linear2.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(ContactUs1.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:12.803915,77.514190"));
                startActivity(intent);

            }
        });

        linear4.setOnClickListener(new View.OnClickListener() {
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


        tumblr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://goaltait.tumblr.com/"));
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

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://plus.google.com/u/0/112220474128522817130"));
                startActivity(intent);
            }
        });
    }
}
