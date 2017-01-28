package com.example.user.chendemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;
import com.example.user.chendemo.R;
/**
 * Created by user on 2017/1/25.
 */

public class ListViewAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private Context mContext;
    public ListViewAdapter(Context context){
        mContext=context;
        mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    //how many items
    public int getCount() {
        return 100;
    }

    @Override

    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    //each item will call this function until all items are created
    public View getView(int position, View convertView, ViewGroup parent) {
        //TextView view = new TextView(mContext);
       //// View view = new View(mContext);
        //view.setText(String.valueOf(position));
        //return view;
        View rowView = mInflater.inflate(R.layout.list_item,parent,false);
        TextView textView = (TextView)rowView.findViewById(R.id.list_view_tv);
        textView.setText(String.valueOf(position));
        return textView;
    }
}
