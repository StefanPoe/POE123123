package com.example.coachit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class CalendarActivity extends AppCompatActivity {

    CustomCalendarView customCalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


            customCalendarView = (CustomCalendarView) findViewById(R.id.customcalendarview);
    }
}