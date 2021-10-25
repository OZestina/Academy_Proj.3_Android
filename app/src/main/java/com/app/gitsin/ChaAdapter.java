package com.app.gitsin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;


public class ChaAdapter extends BaseAdapter {

    ArrayList<ChaListItem> chaList;
    ArrayList<ChaDTO> chaDTOs;
    User user;
    String key;
    String chaId;

    public ChaAdapter(ArrayList<ChaListItem> list, ArrayList<ChaDTO> dtos, User user, String key) {
        chaDTOs = dtos; chaList = list; this.user = user; this.key = key;
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
    public long getItemId(int position) { return 0; }

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

        //현재 참여 챌린지가 있는 경우 추가 참여 불가
        if (user.getChaPerson() == 1) {
            if (chaDTOs.get(POS).getCategory().equals("개인전")){ chaBtn.setEnabled(false); } }
        if (user.getChaGroup() == 1) {
            if (chaDTOs.get(POS).getCategory().equals("단체전")){ chaBtn.setEnabled(false); } }

        //참여 인원이 다 채워졌을 경우 or 내가 참여한 챌린지는 참여 불가
        String[] participant = {};
        int participants = 0;
        if (chaDTOs.get(POS).getParticipants() != null){
            participant = chaDTOs.get(POS).getParticipants().split(",");
            participants = participant.length;
        }
        if (participants >= chaDTOs.get(POS).getLimit() || Arrays.asList(participant).contains(user.getUserId())){
            chaBtn.setEnabled(false);
        }

        chaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtomicBoolean done = new AtomicBoolean(false);
                //Toast.makeText(CONTEXT, POS+"번 버튼 클릭", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(CONTEXT);
                dialog.setTitle(chaList.get(POS).getChaName()+"에 참여하시겠어요?");
                dialog.setPositiveButton("참여", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference database, database2;
                        //챌린지 & 유저 업데이트
                        database = FirebaseDatabase.getInstance().getReference("challenge");
                        database2 = FirebaseDatabase.getInstance().getReference("users");
                        database.orderByChild("name").equalTo(chaList.get(POS).getChaName()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                    //챌린지 ID 추출
                                    chaId = snapshot1.getKey();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) { }
                        });

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //파이어베이스에 보낼 챌린지 DTO 업데이트
                                String newPart = "";
                                if (chaDTOs.get(POS).getParticipants() == null) {
                                    newPart = user.getUserId()+",";
                                } else {
                                    newPart = chaDTOs.get(POS).getParticipants()+user.getUserId()+",";
                                }
                                chaDTOs.get(POS).setParticipants(newPart);

//                                Log.d("=================", chaDTOs.get(POS).toString());

                                //챌린지 DTO 파이어베이스로 업데이트
                                ChaDTO challenge = chaDTOs.get(POS);
                                database.child(chaId).setValue(challenge);

                                //유저 정보 업데이트
                                if (chaDTOs.get(POS).getCategory().equals("개인전")){
                                    user.setChaPerson(1);
                                    user.setChaPersonDone(user.getChaPersonDone()+1);
                                } else {
                                    user.setChaGroup(1);
                                    user.setChaGroupDone(user.getChaGroupDone()+1);
                                }
                                database2.child(key).setValue(user);
//                                Log.d("=================", user.toString());
                                done.set(true);
                            }
                        }, 1000);  //end of handler
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                dialog.show();
            }
        });
        return convertView;
    }
}
