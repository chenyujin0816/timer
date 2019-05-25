package com.example.timer;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ImageView calenderImageView;
    private ImageView moreImageView;
    private TextView dateTextView;
    private RatingBar ratingBar;

    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private FloatingActionButton fab;

    private LinkedList<Record> records=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalUtil.getInstance().setContext(getApplicationContext());


        initView();
        initData();

        listViewAdapter=new ListViewAdapter(this);
        String pattern="yyyy-MM-dd";
        records=GlobalUtil.getInstance().recordDatabaseHelper.readRecords(DateUtil.getCurTime(pattern));
        listViewAdapter.setDate(records);
        listView.setAdapter(listViewAdapter);
    }

    private void initData() {
        Record record=new Record();
        record.setTitle("hhhhh");
        String pattern="yyyy-MM-dd";
        record.setDate(DateUtil.getCurTime(pattern));
        Log.d("MainActivity",record.getDate());
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
    }

    private void initView() {
        calenderImageView=findViewById(R.id.calender_image_view);
        moreImageView=findViewById(R.id.more_image_view);
        dateTextView=findViewById(R.id.date_text);
        ratingBar=findViewById(R.id.rating_bar);
        listView=findViewById(R.id.list_view);
        fab=findViewById(R.id.floatingActionButton);
        listView.setOnItemClickListener(this);
        calenderImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DateActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Record selectedRecord=records.get(position);
        Intent intent=new Intent(MainActivity.this,ScanActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("record",selectedRecord);
        intent.putExtras(bundle);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String date=getIntent().getStringExtra("date");
        if(date!=null){
            Log.d("MainActivity",date);
            records=GlobalUtil.getInstance().recordDatabaseHelper.readRecords(date);
            listViewAdapter.setDate(records);
        }
    }
}
