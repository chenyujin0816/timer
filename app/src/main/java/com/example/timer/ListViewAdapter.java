package com.example.timer;

import android.content.ContentProvider;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class ListViewAdapter extends BaseAdapter {
    private LinkedList<Record> records;

    private LayoutInflater inflater;
    private Context context;

    public ListViewAdapter(Context context){
        this.context=context;
        inflater=LayoutInflater.from(this.context);
    }

    public void setDate(LinkedList<Record> records){
        this.records=records;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            if(position%2==0)
                convertView= inflater.inflate(R.layout.item_layout_left,null);
            else
                convertView=inflater.inflate(R.layout.item_layout_right,null);
            Record record= (Record) getItem(position);
            viewHolder=new ViewHolder(convertView,record);

            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }
}

class ViewHolder{
    TextView title;
    TextView time;

    public ViewHolder(View itemView,Record record){
        title=itemView.findViewById(R.id.item_title_text);
        time=itemView.findViewById(R.id.item_time_text);

        title.setText(record.getTitle());
        time.setText("6.30");
    }
}