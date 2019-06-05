package com.example.timer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timer.data.TimerContract;

import java.io.File;
import java.io.FileOutputStream;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    TextView date;
    TextView title;
    TextView adress;
    TextView begin;
    TextView end;
    TextView body;
    ImageView edit;
    ImageView share;
    ImageView delete;
    ImageView back;
    Intent intent;

    private Uri currentUri;
    private static final int SCAN_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        currentUri = getIntent().getData();
        getSupportLoaderManager().initLoader(SCAN_LOADER, null, this);
        initView();
        setListener();


    }

    private void initView() {

        date = (TextView) findViewById(R.id.date);
        title = (TextView) findViewById(R.id.title);
        adress = (TextView) findViewById(R.id.address);
        begin = (TextView) findViewById(R.id.begin);
        end = (TextView) findViewById(R.id.end);
        body = (TextView) findViewById(R.id.body);
        edit = (ImageView) findViewById(R.id.edit);
        share = (ImageView) findViewById(R.id.share);
        delete = (ImageView) findViewById(R.id.delete);
        back = (ImageView) findViewById(R.id.cancle_scan);
    }

    private void setListener() {
        edit.setOnClickListener(this);
        share.setOnClickListener(this);
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void sharePicture() {
        String url = new String();
        url = getPicture();
    }

    private String getPicture() {

        String filePath = new String();

        View dView = getWindow().getDecorView();
//        dView.setDrawingCacheEnabled(true);
//        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                filePath = sdCardPath + File.separator + "screenshot.png";
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                Log.e("a7888", "存储完成");
            } catch (Exception e) {
            }
        }
        return filePath;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancle_scan:
                finish();
                break;
            case R.id.edit:
                Intent intent = new Intent(ScanActivity.this, AddActivity.class);
                intent.setData(currentUri);
                startActivity(intent);
                break;
            case R.id.share:
                sharePicture();
                break;
            case R.id.delete:
                getContentResolver().delete(currentUri, null, null);
                finish();
                break;
        }
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(
                this,
                currentUri,
                null,
                null,
                null,
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();

        String date=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_DATE));
        String title=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_TITLE));
        String address=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_ADDRESS));
        String detail=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_DETAIL));
        long beginTime=data.getLong(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_BEGIN_TIME));
        long endTime=data.getLong(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_END_TIME));

        this.date.setText(date);
        this.title.setText(title);
        this.adress.setText(address);
        this.body.setText(detail);
        this.begin.setText(DateUtil.getDateToString(beginTime, DateUtil.stdTimePattern));
        this.end.setText(DateUtil.getDateToString(endTime, DateUtil.stdTimePattern));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
