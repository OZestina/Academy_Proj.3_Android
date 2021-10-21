package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

public class ChaActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    ImageView chaIVPerson, chaIVGroup;
    DatabaseReference database;
    User user;
    ArrayList<ChaDTO> chaDTOs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("info");
        final String TAG = "FIREBASE";

        //현재 챌린지 참여 여부 확인해서 참여중인 경우 이미지 변경
        if (user.getChaGroup() == 1){ chaIVGroup.setImageResource(R.drawable.cha_group); }
        if (user.getChaPerson() == 1){ chaIVPerson.setImageResource(R.drawable.cha_personal); }

        database = FirebaseDatabase.getInstance().getReference("challenge");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //리스트 초기화
                chaDTOs = new ArrayList<>();
                //리스트 저장
                for (DataSnapshot data : snapshot.getChildren()) {
                    ChaDTO a = data.getValue(ChaDTO.class);
                    Log.d("파베 DB",a.toString());

                    ChaDTO b = new ChaDTO(a.getCategory(), a.getLimit(), a.getName(), a.getParticipants(),a.getStartDate());
                    Log.d("파베 DB",b.toString());

                    boolean c = chaDTOs.add(b);
                    Log.d("파베 DB2",chaDTOs.get(0).getCategory());
                }

                //DTO -> ChaListItem 데이터 가공
                ArrayList<ChaListItem> chaListItems = new ArrayList<>();
                Log.d("파베 어댑터", "데이터 가공 시작");
                for (ChaDTO dto : chaDTOs) {
                    //chaPart format: (String) 1/10
                    int participants = (dto.getParticipants() != null) ? dto.getParticipants().split(",").length : 0;
                    String chaPart = participants + "/" + dto.getLimit();

                    chaListItems.add(new ChaListItem(dto.getName(),dto.getCategory(),dto.getStartDate(),chaPart));
                }
                Log.d("파베 어댑터", "데이터 가공 끝");

                ListView listView = findViewById(R.id.chaListView);
                ChaAdapter adapter = new ChaAdapter(chaListItems);
                listView.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        //하단 메뉴
        b1 = findViewById(R.id.menu3Pro);
        b2 = findViewById(R.id.menu3Hof);
        b3 = findViewById(R.id.menu3Challenge);
        b4 = findViewById(R.id.menu3Stats);
        b5 = findViewById(R.id.menu3Friends);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu3Pro :
                intent = new Intent(ChaActivity.this, MainActivity.class);
                break;
            case R.id.menu3Hof :
                intent = new Intent(ChaActivity.this, HoFActivity.class);
                break;
            case R.id.menu3Stats :
                intent = new Intent(ChaActivity.this, StatsActivity.class);
                break;
            case R.id.menu3Friends :
                intent = new Intent(ChaActivity.this, FriendsActivity.class);
                break;
            default :
                intent = new Intent(ChaActivity.this, ChaActivity.class);
                break;
        }
        intent.putExtra("info", user);
        startActivity(intent);
    }

}

