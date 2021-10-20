package com.app.gitsin;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class HoFAdapter extends BaseAdapter {

    ArrayList<HoFListItem> thisList;

    public HoFAdapter(ArrayList<HoFListItem> list) {
        thisList = list;
    }

    @Override
    public int getCount() {
        return thisList.size();
    }

    @Override
    public Object getItem(int position) {
        return thisList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "item_hof" layout을 infalte하여 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_hof, parent, false);
        }

        // 화면에 표시될 (layout이 inflate된) view로부터 위젯에 대한 참조 획득
        ImageView hofImage = (ImageView)convertView.findViewById(R.id.hofAchImage);
        TextView hofTitle = (TextView) convertView.findViewById(R.id.hofAchTitle);
        TextView hofDetail = (TextView) convertView.findViewById(R.id.hofAchDetail);
        ProgressBar hofBar = (ProgressBar)convertView.findViewById(R.id.hofAchBar);
        TextView hofCount = (TextView)convertView.findViewById(R.id.hofAchCount);

        // Data Set(list) 에서 position에 위치한 데이터 참조 획득
        HoFListItem listItem = thisList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        hofImage.setImageResource(listItem.getAchImage());
        hofTitle.setText(listItem.getAchTitle());
        hofDetail.setText(listItem.getAchDetail());
        hofBar.setProgress(listItem.getAchProgress());
        hofBar.setMax(listItem.getAchMax());
        String count = listItem.getAchProgress() + "/" + listItem.getAchMax();
        hofCount.setText(count);
        if (listItem.getAchProgress() >= listItem.getAchMax()) {
            hofCount.setText("완료!");
        }

        return convertView;
    }
}
