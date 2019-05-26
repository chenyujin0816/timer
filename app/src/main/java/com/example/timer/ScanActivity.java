package com.example.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    Record record = new Record();
    TextView data;
    TextView title;
    TextView adress;
    TextView begin;
    TextView end;
    TextView body;
    ImageView edit;
    ImageView share;
    ImageView delete;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        record.setTitle("sdfsf");
        record.setAddress("sffgg");
        record.setEndTime(System.nanoTime());
        record.setEndTime(System.nanoTime());
        record.setDetail("sdfsfsffff");
        record.setDate("adadsdad");

        initView();
        initPage();
        setListener();
        intent=getIntent();


    }

    private void initView(){

        data=(TextView)findViewById(R.id.date);
        title=(TextView) findViewById(R.id.title);
        adress=(TextView)findViewById(R.id.address);
        begin=(TextView)findViewById(R.id.begin);
        end=(TextView)findViewById(R.id.end);
        body=(TextView)findViewById(R.id.body);
        edit=(ImageView)findViewById(R.id.edit);
        share=(ImageView)findViewById(R.id.share);
        delete=(ImageView)findViewById(R.id.delete);
    }

    private void initPage(){
        data.setText(record.getDate());
        title.setText(record.getTitle());
        adress.setText(record.getAddress());
        body.setText(record.getDetail());
        begin.setText(DateUtil.getDateToString(record.getBeginTime(),DateUtil.stdTimePattern));
        end.setText(DateUtil.getDateToString(record.getEndTime(),DateUtil.stdTimePattern));
    }

    private void setListener(){
        edit.setOnClickListener(this);
        share.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit:
                Intent intent=new Intent(ScanActivity.this,AddActivity.class);
                intent.putExtra("edit",record);
                startActivity(intent);
                break;
            case R.id.share:
                break;
            case R.id.delete:
                GlobalUtil.getInstance().recordDatabaseHelper.removeRecord(record.getUuid());
                finish();
                break;
        }
    }
}
