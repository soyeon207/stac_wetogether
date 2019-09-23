package com.example.we_together;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter2 extends BaseAdapter {

    private ArrayList<ListViewItem2> listViewItemList = new ArrayList<ListViewItem2>() ;

    public ListViewAdapter2() {

    }

    public void addItem(String name,String housework){
        ListViewItem2 item = new ListViewItem2();

        item.setHousework(housework);
        item.setName(name);

        listViewItemList.add(item);
    }

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public Object getItem(int i) {
        return listViewItemList.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return i ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titleTextView = (TextView) convertView.findViewById(R.id.i_name) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.i_housework) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem2 listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(listViewItem.getName());
        descTextView.setText(listViewItem.getHousework());

        return convertView;
    }
}
