package com.example.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        calendarView=findViewById(R.id.calender_view);
        calendarView.setOnDateChangeListener(this);


    }

    @Override
    public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
        Intent intent=new Intent(DateActivity.this,MainActivity.class);
        String date=Integer.toString(year);
        month+=1;
        if(month<10){
            date+="-0"+month;
        }else{
            date+="-"+month;
        }
        if(dayOfMonth<10){
            date+="-0"+dayOfMonth;
        }else{
            date+="-"+dayOfMonth;
        }
        intent.putExtra("date",date);
        startActivity(intent);
        finish();
    }
}
