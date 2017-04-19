package com.example.eduardo.locmess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddLocationActivity extends AppCompatActivity {

    double longitude;
    double latitude;
    Button b_get;
    private TrackGPS gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

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
    protected void onDestroy() {
        super.onDestroy();
        gps.stopUsingGPS();
    }
}
