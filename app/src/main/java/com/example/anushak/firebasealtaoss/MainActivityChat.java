package com.example.anushak.firebasealtaoss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.anushak.firebasealtaoss.data.StaticConfig;
import com.example.anushak.firebasealtaoss.service.ServiceUtils;
import com.example.anushak.firebasealtaoss.ui.FriendsFragment;
import com.example.anushak.firebasealtaoss.ui.GroupFragment;
import com.example.anushak.firebasealtaoss.ui.UserProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivityChat extends AppCompatActivity {

    private static String TAG = "MainActivityChat";
    private ViewPager viewPager;
    private TabLayout tabLayout = null;
    public static String STR_FRIEND_FRAGMENT = "FRIEND";
    public static String STR_GROUP_FRAGMENT = "GROUP";
    public static String STR_INFO_FRAGMENT = "INFO";

    private FloatingActionButton floatButton;
    private ViewPagerAdapter adapter;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    ImageView back;

    // DrawerLayout mDrawerLayout;
    android.support.v7.widget.Toolbar toolbar;
   // ActionBarDrawerToggle actionBarDrawerToggle;
    //NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main_chat);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

      /*  mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);*/
      //  navigationView = (NavigationView)findViewById(R.id.navigation_view);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        floatButton = (FloatingActionButton) findViewById(R.id.fab);
        initTab();
        initFirebase();

        SharedPreferences userDetails1 = MainActivityChat.this.getSharedPreferences("Vibration", MODE_PRIVATE);
        SharedPreferences.Editor edit1 = userDetails1.edit();
        edit1.clear();
        edit1.putBoolean("boolean1", true);
        edit1.commit();

        SharedPreferences userDetails = MainActivityChat.this.getSharedPreferences("Notification", MODE_PRIVATE);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.clear();
        edit.putBoolean("boolean", true);
        edit.commit();

       /* navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.settings:
                        Intent intent=new Intent(MainActivityChat.this,Settings1.class);
                        startActivity(intent );
                        break;
                    case R.id.share:
                        Intent shareIntent =   new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                        String app_url = "https://www.altaitsolutions.com";
                        shareIntent.putExtra(Intent.EXTRA_TEXT,app_url);
                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                        break;
                    case R.id.feedback:
                        Intent intent1=new Intent(MainActivityChat.this,Feedback1.class);
                        startActivity(intent1);
                        break;
                    case R.id.contact:
                        Intent intent2=new Intent(MainActivityChat.this,ContactUs1.class);
                        startActivity(intent2);
                        break;
                    case R.id.help:
                        Intent intent3=new Intent(MainActivityChat.this,HelpDesk1.class);
                        startActivity(intent3);
                        break;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        FriendDB.getInstance(MainActivityChat.this).dropDB();
                        GroupDB.getInstance(MainActivityChat.this).dropDB();
                        ServiceUtils.stopServiceFriendChat(MainActivityChat.this.getApplicationContext(), true);
                        finish();
                        break;
                }
                return false;
            }
        });*/
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       // actionBarDrawerToggle.syncState();
    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    StaticConfig.UID = user.getUid();
                } else {
                    MainActivityChat.this.finish();
                    // User is signed in
                    startActivity(new Intent(MainActivityChat.this, MainActivity.class));
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
       //ServiceUtils.stopServiceFriendChat(getApplicationContext(), false);
        ServiceUtils.startServiceFriendChat(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
       // ServiceUtils.startServiceFriendChat(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        //ServiceUtils.startServiceFriendChat(getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
       //ServiceUtils.startServiceFriendChat(getApplicationContext());
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ServiceUtils.startServiceFriendChat(getApplicationContext());
    }

    private void initTab() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIndivateTab));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.string.chats,
                R.string.groups,
                R.string.profile
        };

        tabLayout.getTabAt(0).setText(tabIcons[0]);
        tabLayout.getTabAt(1).setText(tabIcons[1]);
        tabLayout.getTabAt(2).setText(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FriendsFragment(), STR_FRIEND_FRAGMENT);
        adapter.addFrag(new GroupFragment(), STR_GROUP_FRAGMENT);
        adapter.addFrag(new UserProfileFragment(), STR_INFO_FRAGMENT);
        floatButton.setOnClickListener(((FriendsFragment) adapter.getItem(0)).onClickFloatButton.getInstance(this));
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onPageSelected(int position) {
              //  ServiceUtils.stopServiceFriendChat(MainActivityChat.this.getApplicationContext(), false);
                ServiceUtils.startServiceFriendChat(getApplicationContext());

                if (adapter.getItem(position) instanceof FriendsFragment) {
                    floatButton.setVisibility(View.VISIBLE);
                    floatButton.setOnClickListener(((FriendsFragment) adapter.getItem(position)).onClickFloatButton.getInstance(MainActivityChat.this));
                    floatButton.setImageResource(R.drawable.ic_person_add_black_24dp);
                } else if (adapter.getItem(position) instanceof GroupFragment) {
                    floatButton.setVisibility(View.VISIBLE);
                    floatButton.setOnClickListener(((GroupFragment) adapter.getItem(position)).onClickFloatButton.getInstance(MainActivityChat.this));
                    floatButton.setImageResource(R.drawable.ic_group_add_black_24dp);
                } else {
                    floatButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.query) {
            Intent intent = new Intent(MainActivityChat.this,QueryForm.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return null;
        }
    }
}