package com.example.sylviatan.lab22;

import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonContacts, buttonViewMaps, buttonAlarm, buttonCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonContacts = findViewById(R.id.buttonContacts);
        buttonViewMaps = findViewById(R.id.buttonViewMap);
        buttonAlarm = findViewById(R.id.buttonAlarm);
        buttonCalendar = findViewById(R.id.buttonCalendar);
        buttonContacts.setOnClickListener(this);
        buttonCalendar.setOnClickListener(this);
        buttonViewMaps.setOnClickListener(this);
        buttonAlarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch(v.getId()){

            case R.id.buttonContacts:
                //Remember to request for permission to READ_CONTACTS
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
                break;

            case R.id.buttonAlarm:
                setAlarm();
                break;

            case R.id.buttonViewMap:
                // Map point based on address
                //3.2151346,101.724356,17 Tunku Abdul Rahman University College
                Uri location = Uri.parse("geo:0,0?q=Kolej+University+Tunku+Abdul+Rahman");
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(location);
                startActivity(intent);
                break;

            case R.id.buttonCalendar:
                intent.setAction(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                Calendar endTime = Calendar.getInstance();
                beginTime.set(2018,5,22,9,00,00);
                endTime.set(2018,5,24,17,00,00);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                intent.putExtra(CalendarContract.Events.TITLE, "Android App Development Workshop");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "B228");
                startActivity(intent);
                break;
        }
    }

    public void setAlarm(){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Wake Up Call");
        intent.putExtra(AlarmClock.EXTRA_HOUR, 9);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
