package com.example.eduardo.locmess;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowMessage extends AppCompatActivity {
    public String message = "On a dark desert highway, cool wind in my hair\n" +
            "Warm smell of colitas, rising up through the air\n" +
            "Up ahead in the distance, I saw a shimmering light\n" +
            "My head grew heavy and my sight grew dim\n" +
            "I had to stop for the night\n" +
            "There she stood in the doorway;\n" +
            "I heard the mission bell\n" +
            "And I was thinking to myself,\n" +
            "'This could be Heaven or this could be Hell'\n" +
            "Then she lit up a candle and she showed me the way\n" +
            "There were voices down the corridor,\n" +
            "I thought I heard them say...";
    public String user_id = "Ana Martins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_message);

        //Back bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get position from previous activity
        Intent myIntent = getIntent(); // gets the previously created intent
        int position = myIntent.getIntExtra("position", 0);

        //Views
        TextView user_id_view = (TextView) findViewById(R.id.user_id_show);
        TextView date_view = (TextView) findViewById(R.id.date_show);
        TextView message_view = (TextView) findViewById(R.id.message_content);

        setTitle("SMS " +(position+1));     //set view title on top

        if (position == 0) {
            user_id_view.setText(user_id);
            date_view.setText(getCurrentTimeStamp());
            message_view.setText(message);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }

    //Just to test the interface (delete during implementation)
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
