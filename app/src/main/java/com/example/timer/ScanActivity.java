package com.example.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    Record record;
    TextView date;
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


        initView();
        intent=getIntent();
        record=(Record) intent.getSerializableExtra("record");
        initPage();
        setListener();


    }

    private void initView(){

        date=(TextView)findViewById(R.id.date);
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
        date.setText(record.getDate());
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

    private void sharePicture(){
        String url=new String();
        url=getPicture();
    }

    private String getPicture(){

//        View dView = getWindow().getDecorView();
//        dView.setDrawingCacheEnabled(true);
//        dView.buildDrawingCache();
//        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
//        if (bitmap != null) {
//            try {
//                // 获取内置SD卡路径
//                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
//                // 图片文件路径
//                String filePath = sdCardPath + File.separator + "screenshot.png";
//                File file = new File(filePath);
//                FileOutputStream os = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
//                os.flush();
//                os.close();
//                DebugLog.d("a7888", "存储完成");
//            } catch (Exception e) {
//            }
//        }

        return null;
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

    @Override
    protected void onRestart() {
        super.onRestart();

        record=GlobalUtil.getInstance().recordDatabaseHelper.readRecord(record.getUuid());
        initPage();
    }
}
