package com.example.sm.problem3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sm on 12/12/16.
 */

public class MyBaseAdapter extends BaseAdapter{

    Context mContetxt = null;
    ArrayList<Customer> mData = null;
    LayoutInflater mLayoutInflater = null;

    MyBaseAdapter(  Context context, ArrayList<Customer> data){
        mContetxt = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContetxt);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item_layout, parent, false);
        }
        TextView nameText    = (TextView)convertView.findViewById(R.id.text_name);
        TextView moneyText = (TextView)convertView.findViewById(R.id.text_money);


        Customer listViewItem = mData.get(position);

        nameText.setText(listViewItem.name);
        moneyText.setText(listViewItem.getMoney()+"");

        return convertView;

    }
}







