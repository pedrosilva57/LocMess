package com.example.eduardo.locmess;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_location:
                    actionBar.setTitle("Location");
                    selectedFragment = LocationFragment.newInstance();
                    break;
                case R.id.navigation_messages:
                    actionBar.setTitle("Messages");
                    selectedFragment = MessagesFragment.newInstance();
                    break;
                case R.id.navigation_profile:
                    actionBar.setTitle("Profile");
                    selectedFragment = ProfileFragment.newInstance();
                    break;
            }
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, selectedFragment);
            ft.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, LocationFragment.newInstance());
        transaction.commit();
    }
}
