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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageView calendarImageView;
    private TextView dateTextView;
    private RatingBar ratingBar;
    private TextView ratingText;

    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private FloatingActionButton fab;

    private String currentDate;

    private LinkedList<Record> records = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalUtil.getInstance().setContext(getApplicationContext());


        initView();
        initData();
        listViewAdapter = new ListViewAdapter(this);
        String pattern = "yyyy-MM-dd";
        records = GlobalUtil.getInstance().recordDatabaseHelper.readRecords(DateUtil.getCurTime(pattern));
        listViewAdapter.setDate(records);
        listView.setAdapter(listViewAdapter);
    }

    private void initData() {
        Record record = new Record();
        record.setTitle("hhhhh");
        String pattern = "yyyy-MM-dd";
        record.setDate(DateUtil.getCurTime(pattern));
        Log.d("MainActivity", record.getDate());
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
    }

    private void initView() {
        calendarImageView = findViewById(R.id.calendar_image_view);
        dateTextView = findViewById(R.id.date_text);
        ratingBar = findViewById(R.id.rating_bar);
        ratingText = findViewById(R.id.rating_text);
        listView = findViewById(R.id.list_view);
        fab = findViewById(R.id.floatingActionButton);

        calendarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DateActivity.class);
                startActivityForResult(intent,1);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setIsIndicator(true);
                ratingText.setText("长安此处修改评分！");
            }
        });

        ratingText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ratingBar.setIsIndicator(false);
                ratingText.setText("给今天的自己打个分吧！");
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("date", currentDate);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Record record = (Record) listViewAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("record", record);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        currentDate = DateUtil.getDateToString(DateUtil.getCurTimeLong(), DateUtil.stdDatePattern);
        String pattern = "MM/dd";
        dateTextView.setText(DateUtil.getCurTime(pattern) + " " + DateUtil.getWeek(DateUtil.getCurTimeLong()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        reloadPage();
    }

    private void reloadPage(){
        records = GlobalUtil.getInstance().recordDatabaseHelper.readRecords(currentDate);
        listViewAdapter.setDate(records);
        long dateLong = DateUtil.getStringToDate(currentDate, DateUtil.stdDatePattern);
        String pattern = "MM/dd";
        dateTextView.setText(DateUtil.getDateToString(dateLong, pattern) + " " + DateUtil.getWeek(dateLong));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        currentDate = data.getStringExtra("date");
    }
}
