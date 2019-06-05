package com.example.timer;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.timer.data.TimerContract;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";
    private ImageView calendarImageView;
    private TextView dateTextView;
    private RatingBar ratingBar;
    private TextView ratingText;

    private RecordCursorAdapter recordCursorAdapter;
    private ListView listView;
    private FloatingActionButton fab;

    private String currentDate;

    private LinkedList<Record> records = new LinkedList<>();

    private static final int MAIN_LOADER=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalUtil.getInstance().setContext(getApplicationContext());

        initView();

        recordCursorAdapter=new RecordCursorAdapter(this,null);
        listView.setAdapter(recordCursorAdapter);
        getSupportLoaderManager().initLoader(MAIN_LOADER,null,this);



        reloadPage();
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
                ratingText.setText("长按此处修改评分！");
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
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                Uri uri= ContentUris.withAppendedId(TimerContract.RecordEntry.CONTENT_URI,id);
                intent.setData(uri);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        reloadPage();
    }

    private void reloadPage(){
        long dateLong = DateUtil.getStringToDate(currentDate, DateUtil.stdDatePattern);
        String pattern = "MM/dd";
        dateTextView.setText(DateUtil.getDateToString(dateLong, pattern) + " " + DateUtil.getWeek(dateLong));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==1)
            currentDate = data.getStringExtra("date");
}

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader=new CursorLoader(
                this,
                TimerContract.RecordEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        recordCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        recordCursorAdapter.swapCursor(null);
    }
}
