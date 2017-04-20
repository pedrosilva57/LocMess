package com.example.eduardo.locmess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String val = getIntent().getExtras().getString("Local");

        TextView txtEdit = (TextView) findViewById(R.id.txtLocal);

        txtEdit.setText(val);
    }
}
