package com.app.gitsin;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Map;

public class StatsAdapter extends BaseAdapter {

    ArrayList<Map> thisList;
    String id;
    int[] drawables = {R.drawable.stats_rank1, R.drawable.stats_rank2, R.drawable.stats_rank3};
    int max;

    public StatsAdapter(ArrayList<Map> list, String userId) {
        thisList = list;
        id = userId;
        Log.d("FIREBASE", "userId = " + id);
        String subStr = (String) list.get(0).get("what");
        this.max = Integer.parseInt(subStr.substring(0, subStr.length() - 1));
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

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "item_stats" layout을 inflate하여 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_stats, parent, false);
        }

        // 화면에 표시될 (layout이 inflate된) view로부터 위젯에 대한 참조 획득
        ImageView statsImage = (ImageView) convertView.findViewById(R.id.stats_image);
        TextView statsId = (TextView) convertView.findViewById(R.id.stats_text1);
        ProgressBar statsBar = (ProgressBar) convertView.findViewById(R.id.stats_bar);
        TextView statsCount = (TextView) convertView.findViewById(R.id.stats_text2);

        // Data set(list) 에서 position에 위치한 데이터 참조 획득
        Map<String, String> map = thisList.get(pos);

        String userId = map.get("userId");
        String what = map.get("what");
        float tempSize = (Float.parseFloat(what.substring(0, what.length() - 1)) / max) * 800;
        int size = (tempSize < 10) ? 10 : (int) tempSize;

        // 아이템 내 각 위젯에 데이터 반영
        statsImage.setImageResource(0);
        if (position < 3) {
            statsImage.setImageResource(drawables[pos]);
        }
        if (userId.equals(id)) {
            statsId.setText(userId + " (나)");
        } else {
            statsId.setText(userId);
        }
        ValueAnimator animator = ValueAnimator.ofInt(0, size);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Integer.parseInt(animation.getAnimatedValue().toString()), 50);
                statsBar.setLayoutParams(params);
            }
        });
        animator.start();
        statsCount.setText(what);

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        return convertView;
    }
}
