package com.example.anushak.firebasealtaoss;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Mobile extends AppCompatActivity {

    ListView approvelist,rejectlist;
    TextView mobile;
    SwipeMenuListView listView;
    ReimbursementAdapter mList,mList1,mList2;
    private DatabaseReference mDatabase;
    String Category,UserId,UserType,Text,Type;
    TextView userid,usertype,text;
    android.support.v7.widget.Toolbar toolbar;

    ImageView iv,iv1;
    TextView dashboard,dashboard1,list,list1,type,type1;
    HorizontalScrollView hsv1,hsv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mobile);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mobile=(TextView)findViewById(R.id.mobile);
        userid = (TextView)findViewById(R.id.userid);
        usertype = (TextView)findViewById(R.id.usertype);
        text = (TextView)findViewById(R.id.text);

        hsv1 = (HorizontalScrollView)findViewById(R.id.hsv1);
        hsv2 = (HorizontalScrollView)findViewById(R.id.hsv2);
        type = (TextView)findViewById(R.id.type);
        type1 = (TextView)findViewById(R.id.type1);
        iv = (ImageView)findViewById(R.id.iv);
        iv1 = (ImageView)findViewById(R.id.iv1);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        list = (TextView)findViewById(R.id.list);
        list1 = (TextView)findViewById(R.id.list1);

        UserId = getIntent().getStringExtra("userId");
        UserType = getIntent().getStringExtra("usertype");
        Category=getIntent().getStringExtra("category");

        usertype.setText(UserType);
        userid.setText(UserId);
        mobile.setText(Category);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mobile.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mobile.this,ReimbursementDashboard.class);
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
                Intent intent = new Intent(Mobile.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mobile.this,ReimbursementDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        hsv1.setVisibility(UserType.equals("admin")? View.VISIBLE : View.GONE);
        hsv2.setVisibility(UserType.equals("hr")? View.VISIBLE : View.GONE);

        if(Category.equals("Mobile"))
        {
            type.setText("  Mobile");
            type1.setText("  Mobile");
        }
        else if(Category.equals("Conveyances"))
        {
            type.setText("  Conveyances");
            type1.setText("  Conveyances");
        }
        else if(Category.equals("General Expenses"))
        {
            type.setText("  General Expenses");
            type1.setText("  General Expenses");
        }

        Type = type.getText().toString();
        if(UserType.equals("hr"))
        {
            text.setText("HR Dashboard - Reimbursement Dashboard - "+ Type);
        }
        else if(UserType.equals("admin"))
        {
            text.setText("Admin Dashboard - Reimbursement Dashboard - "+ Type);
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("Reimbursement").child(Category);
        Query query = mDatabase.orderByChild("update").equalTo("update");
        Query query1 = mDatabase.orderByChild("update").equalTo("approve");
        Query query2 = mDatabase.orderByChild("update").equalTo("reject");

        mList = new ReimbursementAdapter(query, this, R.layout.reimbursement_list);
        mList1 = new ReimbursementAdapter(query1, this, R.layout.reimbursement_list);
        mList2 = new ReimbursementAdapter(query2, this, R.layout.reimbursement_list);

        listView = (SwipeMenuListView)findViewById(R.id.listView);
        approvelist=(ListView)findViewById(R.id.approvelist);
        rejectlist=(ListView)findViewById(R.id.rejectlist);

        approvelist.setAdapter(mList1);
        rejectlist.setAdapter(mList2);
        listView.setAdapter(mList);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set item title
                deleteItem.setTitle("Close");
                // set item title fontsize
                deleteItem.setTitleSize(18);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        UserId = userid.getText().toString();
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Reimbursement value = (Reimbursement) mList.getItem(position);
                switch (index) {
                    case 0:
                        Intent intent = new Intent(Mobile.this,MobileStatus.class);
                        intent.putExtra("Eid",value.getEmid());
                        intent.putExtra("Category",value.getSpinner());
                        intent.putExtra("mobileno",value.getType());
                        intent.putExtra("Uploaded",value.getUploaded().toString());
                        intent.putExtra("Update",value.getUpdate().toString());
                        intent.putExtra("Amount",value.getClaimedamount().toString());
                        intent.putExtra("URL",value.getUrl());
                        intent.putExtra("ID",value.getUpdateid());
                        intent.putExtra("type",Type);
                        intent.putExtra("usertype",UserType);
                        intent.putExtra("userId",UserId);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(Mobile.this,"Close", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        getMenuInflater().inflate(R.menu.reimbursement_menu,menu);
        getMenuInflater().inflate(R.menu.refresh_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.query) {
            UserId = userid.getText().toString();
            Text = text.getText().toString();
            Intent i = new Intent(Mobile.this, QueryForm.class);
            i.putExtra("userId",UserId);
            i.putExtra("message",Text);
            startActivity(i);
        }
        if (id == R.id.approve)
        {
            listView.setVisibility(View.GONE);
            approvelist.setVisibility(View.VISIBLE);
            rejectlist.setVisibility(View.GONE);
        }
        if (id == R.id.reject)
        {
            listView.setVisibility(View.GONE);
            rejectlist.setVisibility(View.VISIBLE);
            approvelist.setVisibility(View.GONE);
        }
        if (id == R.id.refresh)
        {
            listView.setVisibility(View.VISIBLE);
            rejectlist.setVisibility(View.GONE);
            approvelist.setVisibility(View.GONE);
        }

        return super.onOptionsItemSelected(item);
    }

}
