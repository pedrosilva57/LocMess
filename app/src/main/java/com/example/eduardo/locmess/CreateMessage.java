package com.example.eduardo.locmess;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

import static android.R.attr.onClick;

public class CreateMessage extends AppCompatActivity {
    private String[] local_list = {"Almada", "Lisboa", "Oeiras", "Cacém", "Loures", "Setúbal", "Corroios", "Seixal", "Costa da Caparica", "Sesimbra", "Faro", "Coimbra", "Leiria"};
    private LinearLayout mLayout;
    private Spinner spinner;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance(DateFormat.DATE_FIELD, DateFormat.SHORT);
    Calendar dateTime = Calendar.getInstance();
    Calendar dateTimeEnd = Calendar.getInstance();
    Button date_start, hour_start, date_end, hour_end;
    TextView show_date_start, show_date_end;
    private int start_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_message);

        //Back bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("New Message");    //View title on top

        //Location Selection
        addLocationSelectionSpinner();

        //List Selection
        mLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

        //Time Window Selection
        addTimeWindowSelection();

        Button final_send_button = (Button) findViewById(R.id.send_button);
        final_send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalSendMessage();
            }
        });

    }

    private void finalSendMessage() {
        //add stuff here
        printSendMessage();
        this.finish();
    }

    private void printSendMessage(){
        Context context = getApplicationContext();
        CharSequence text = "Message Sent";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //Related to Time Window Selection......................................................................................................
    private void addTimeWindowSelection(){
        //Buttons
        date_start = (Button) findViewById(R.id.start_date);
        hour_start = (Button) findViewById(R.id.start_hour);
        date_end = (Button) findViewById(R.id.end_date);
        hour_end = (Button) findViewById(R.id.end_hour);

        //Views
        show_date_start = (TextView) findViewById(R.id.start_date_view);
        show_date_end = (TextView) findViewById(R.id.end_date_view);

        //Click Listeners

        //START
        date_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {updateDate(1);}
        });

        hour_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {updateHour(1);}
        });

        //END
        date_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateDate(2);}
        });

        hour_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { updateHour(2);}
        });
    }

    //Updates the label that shows the date and time selected
    private void updateTextLabel(int option){
        switch (option) {
            case 1:
                show_date_start.setText(formatDateTime.format(dateTime.getTime()));
                break;
            case 2:
                show_date_end.setText(formatDateTime.format(dateTimeEnd.getTime()));
                break;
        }
    }

    //Allows the user to change the date
    private void updateDate(int option){
        switch (option){
            case 1:
                new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case 2:
                new DatePickerDialog(this, k, dateTimeEnd.get(Calendar.YEAR), dateTimeEnd.get(Calendar.MONTH), dateTimeEnd.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }

    }

    //Allows the user to change the hour
    private void updateHour(int option){
        switch(option){
            case 1:
                new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
                break;
            case 2:
                new TimePickerDialog(this, h, dateTimeEnd.get(Calendar.HOUR_OF_DAY), dateTimeEnd.get(Calendar.MINUTE), true).show();
                break;
        }

    }

    //DataPickerDialog for choosing starting date of message
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth){
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateTextLabel(1);
        }
    };

    //DataPickerDialog for choosing starting time of message
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet (TimePicker view, int hourOfDay, int minute){
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);

            updateTextLabel(1);
        }
    };

    public String getStartTime(){
        return formatDateTime.format(dateTime.getTime());
    }

    //DataPickerDialog for choosing ending date of message
    DatePickerDialog.OnDateSetListener k = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth){
            dateTimeEnd.set(Calendar.YEAR, year);
            dateTimeEnd.set(Calendar.MONTH, monthOfYear);
            dateTimeEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateTextLabel(2);
        }
    };

    //DataPickerDialog for choosing ending time of message
    TimePickerDialog.OnTimeSetListener h = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet (TimePicker view, int hourOfDay, int minute){
            dateTimeEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTimeEnd.set(Calendar.MINUTE, minute);

            updateTextLabel(2);
        }
    };

    public String getEndTime(){
        return formatDateTime.format(dateTimeEnd.getTime());
    }

    //Related to Location Selection.........................................................................................
    public void addLocationSelectionSpinner(){
        //Location Selection
        spinner = (Spinner) findViewById(R.id.location_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, local_list); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    public String getLocation(){
        return spinner.getSelectedItem().toString();
    }

    //Related to List Selection............................................................................................
    //Adds another view after click in ADD button
    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        mLayout.addView(rowView, mLayout.getChildCount() - 1);
    }

    //Related to List Selection
    //Removes the view selected
    public void onDelete(View v) {
        mLayout.removeView((View) v.getParent());
    }

    //Related to Contacts.....................................................................................................

    public String getPhoneContact(){
        EditText phone_contact = (EditText) findViewById(R.id.contact_phone);
        return PhoneNumberUtils.formatNumber(phone_contact.getText().toString());
    }

    public String getEmailAddress(){
        EditText mail_add = (EditText) findViewById(R.id.contact_email);
        return mail_add.getText().toString();
    }

    //Related to Message Content..............................................................................................
    public String getMessageContent(){
        EditText msg_content = (EditText) findViewById(R.id.editMessage);
        return msg_content.getText().toString();
    }

    //Current Time
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    //To return to previous view
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }
}
