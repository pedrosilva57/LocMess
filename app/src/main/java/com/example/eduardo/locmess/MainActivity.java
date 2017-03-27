package com.example.eduardo.locmess;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_location:
                    actionBar.setTitle("Location");
                    setLocationFragment();
                    return true;
                case R.id.navigation_messages:
                    actionBar.setTitle("Messages");
                    setMessagesFragment();
                    return true;
                case R.id.navigation_profile:
                    actionBar.setTitle("Profile");
                    setProfileFragment();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    public void setLocationFragment(){
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Fragment locationFragment=LocationFragment.newInstance();
        ft.replace(R.id.locationListView,locationFragment);
        ft.commit();
    }

    public void setMessagesFragment(){
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Fragment messagesFragment=MessagesFragment.newInstance();
        ft.replace(R.id.messagesListView,messagesFragment);
        ft.commit();
    }

    public void setProfileFragment(){
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Fragment profileFragment=ProfileFragment.newInstance();
        ft.replace(R.id.content_id,profileFragment);
        ft.commit();
    }
}
