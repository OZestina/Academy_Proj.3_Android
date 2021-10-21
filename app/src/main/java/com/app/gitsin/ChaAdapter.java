package com.app.gitsin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChaAdapter extends BaseAdapter {

    ArrayList<ChaListItem> chaList;

    public ChaAdapter(ArrayList<ChaListItem> list) {
        chaList = list;
    }

    @Override
    public int getCount() {
        return chaList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int POS = position;
        final Context CONTEXT = parent.getContext();

        // "item_cha" layout을 infalte하여 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cha, parent, false);
        }

        // 화면에 표시될 (layout이 inflate된) view로부터 위젯에 대한 참조 획득
        TextView chaName = (TextView)convertView.findViewById(R.id.chaName);
        TextView chaCategory = (TextView) convertView.findViewById(R.id.chaCategory);
        TextView chaStartDate = (TextView) convertView.findViewById(R.id.chaStartDate);
        TextView chaPart = (TextView)convertView.findViewById(R.id.chaPart);
        Button chaBtn = (Button) convertView.findViewById(R.id.chaBtn);

        // Data Set(list) 에서 position에 위치한 데이터 참조 획득
        ChaListItem listItem = chaList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        chaName.setText(listItem.getChaName());
        chaCategory.setText(listItem.getChaCategory());
        chaStartDate.setText(listItem.getChaStartDate());
        chaPart.setText(listItem.getChaPart());

        chaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CONTEXT, POS+"번 버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
