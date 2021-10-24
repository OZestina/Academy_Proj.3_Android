package com.app.gitsin;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HoFAdapter extends BaseAdapter {

    ArrayList<HoFListItem> thisList;

    public HoFAdapter(ArrayList<HoFListItem> list) {
        thisList = list;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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

        int drawable = listItem.getAchImage();
        String title = listItem.getAchTitle();
        String detail = listItem.getAchDetail();
        int progress = listItem.getAchProgress();
        int max = listItem.getAchMax();
        String count = (progress >= max) ? "완료" : progress + "/" + max;

        // 아이템 내 각 위젯에 데이터 반영
        hofImage.setImageResource(drawable);
        hofTitle.setText(title);
        hofDetail.setText(detail);
        hofBar.setMax(max);
        hofBar.setProgress(progress);
        if (progress >= max) {
            hofBar.setVisibility(View.INVISIBLE);
            hofCount.setTextSize(15);
            hofCount.setTextColor(Color.parseColor("#FF0000"));
        }
        hofCount.setText(count);

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }});

        return convertView;
    }
}
