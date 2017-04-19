package com.example.eduardo.locmess;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddLocationActivity extends AppCompatActivity {

    double longitude;
    double latitude;
    private TrackGPS gps;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gps = new TrackGPS(AddLocationActivity.this);

        if(gps.canGetLocation()){

            longitude = gps.getLongitude();
            latitude = gps .getLatitude();

            Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();
        }
        else
        {
            gps.showSettingsAlert();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gps.stopUsingGPS();
    }
}
