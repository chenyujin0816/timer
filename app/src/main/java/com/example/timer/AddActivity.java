package com.example.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

public class AddActivity extends AppCompatActivity {

    TimePicker begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Begin();
    }

    public void Begin(){
        begin=(TimePicker)findViewById(R.id.begin);
        begin.setIs24HourView(true);//是否显示24小时制？默认false

    }
}
