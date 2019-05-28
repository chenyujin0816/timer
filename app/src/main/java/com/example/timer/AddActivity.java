package com.example.timer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    TimePicker begin;
    TimePicker end;
    ImageView cancle_add;
    ImageView finish_add;
    EditText title;
    EditText adress;
    EditText body;

    Record record;
    Intent intent;

    String stitle;
    String sadress;
    String sbody;
    String sbegin;
    String send;
    long lbegin;
    long lend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        intent=this.getIntent();
        initView();
        setListener();

    }

    private void  initView(){
        begin=(TimePicker)findViewById(R.id.begin);
        end=(TimePicker) findViewById(R.id.end);
        begin.setIs24HourView(true);//是否显示24小时制？默认false
        end.setIs24HourView(true);
        begin.setHour(0);
        begin.setMinute(0);
        end.setHour(23);
        end.setMinute(59);
        cancle_add=(ImageView)findViewById(R.id.cancle_add);
        finish_add=(ImageView)findViewById(R.id.finish_add);
        title=(EditText)findViewById(R.id.title);
        adress=(EditText)findViewById(R.id.address);
        body=(EditText)findViewById(R.id.body);
        if (intent.getStringExtra("date")!=null){
            record=new Record();
            record.setDate(intent.getStringExtra("date"));
        }
        else if(intent.getSerializableExtra("edit")!=null){
            record=(Record)intent.getSerializableExtra("edit");
            initPage();
        }
    }

    private void initPage(){
        title.setText(record.getTitle());
        adress.setText(record.getAddress());
        body.setText(record.getDetail());

        String hour =new String();
        String min=new String();
        String time=new String();
        time=DateUtil.getDateToString(record.getBeginTime(),DateUtil.stdTimePattern);
        hour=time.substring(0,1);
        min=time.substring(3,4);
        begin.setHour(Integer.parseInt(hour));
        begin.setMinute(Integer.parseInt(min));
        time=DateUtil.getDateToString(record.getEndTime(),DateUtil.stdTimePattern);
        hour=time.substring(0,1);
        min=time.substring(3,4);
        end.setHour(Integer.parseInt(hour));
        end.setMinute(Integer.parseInt(min));
    }

    private void setListener(){
        cancle_add.setOnClickListener(this);
        finish_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancle_add  :
                finish();
                break;
            case R.id.finish_add:
                writeToDB();
                break;
        }
    }

    public void writeToDB(){

        stitle=title.getText().toString();
        sadress=adress.getText().toString();
        sbody=body.getText().toString();
        sbegin=Integer.toString(begin.getHour())+":"+Integer.toString(begin.getMinute());
        send=Integer.toString(end.getHour())+":"+Integer.toString(end.getMinute());
        lbegin=DateUtil.getStringToDate(sbegin,DateUtil.stdTimePattern);
        lend=DateUtil.getStringToDate(send,DateUtil.stdTimePattern);

        record.setTitle(stitle);
        record.setAddress(sadress);
        record.setDetail(sbody);
        record.setBeginTime(lbegin);
        record.setEndTime(lend);

        if(intent.getSerializableExtra("edit")!=null){
            if(isAnyNull()) {
                GlobalUtil.getInstance().recordDatabaseHelper.editRecord(record.getUuid(),record);
                finish();
            }
            else{
                Log.e("isA", ""+isAnyNull() );
                sendADialog();
            }
        }
        else if(intent.getSerializableExtra("date")!=null){
            if (isAnyNull()){
                GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
                finish();
            }
            else {
                sendADialog();
            }
        }

    }

    private boolean isAnyNull(){
        if (stitle.length()==0)
            return false;
        else if(sadress.length()==0)
            return false;
        else if (sbody.length()==0)
            return false;
        else if(lbegin>lend)
            return false;
        else
            return true;
    }

    private void sendADialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        if(stitle.length()==0){
            builder.setTitle("Title为空");
            builder.setMessage("为什么没有标题？");
        }
        else if(sadress.length()==0){
            builder.setTitle("Destination为空");
            builder.setMessage("为什么没有地点？");
        }
        else if(lbegin>lend){
            builder.setTitle("时间出错");
            builder.setMessage("开始时间迟于结束时间！");
        }
        else if(sbody.length()==0){
            builder.setTitle("内容为空");
            builder.setMessage("什么也不做吗？");
        }

        builder.setPositiveButton("再改改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("setPosition", "sure" );
            }
        });

        builder.show();
    }
}
