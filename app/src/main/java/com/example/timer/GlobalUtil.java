package com.example.timer;

import android.content.Context;

public class GlobalUtil {

    private static GlobalUtil instance;

    public RecordDatabaseHelper recordDatabaseHelper;

    private Context context;

    public void setContext(Context context){
        this.context=context;
        recordDatabaseHelper=new RecordDatabaseHelper(context);
    }

    public static GlobalUtil getInstance(){
        if(instance==null)
            instance=new GlobalUtil();
        return instance;
    }
}
