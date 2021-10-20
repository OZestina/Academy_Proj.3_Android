package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HoFActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 파이어베이스 연동 테스트
        testDBConnect();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_f);

        b1 = findViewById(R.id.menu2Pro);
        b2 = findViewById(R.id.menu2Hof);
        b3 = findViewById(R.id.menu2Challenge);
        b4 = findViewById(R.id.menu2Guild);
        b5 = findViewById(R.id.menu2Friends);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        // (일단) 데이터 정적 추가 -> DB에서 읽어와야 함
        HoFListItem achieve1 = new HoFListItem(R.drawable.hof_01, "귀농", "모내기 1회", 76, 1);
        HoFListItem achieve2 = new HoFListItem(R.drawable.hof_02, "초보 농사꾼", "10일 연속 모내기 달성", 76, 10);
        HoFListItem achieve3 = new HoFListItem(R.drawable.hof_03, "프로 농장주", "100일 연속 모내기 달성", 76, 100);
        HoFListItem achieve4 = new HoFListItem(R.drawable.hof_04, "친구칭긔", "친구 1명 등록하기", 0, 1);
        HoFListItem achieve5 = new HoFListItem(R.drawable.hof_05, "팀 프로젝트", "친구 5명 등록하기", 0, 5);
        HoFListItem achieve6 = new HoFListItem(R.drawable.hof_06, "깃신 인싸", "친구 20명 등록하기", 0, 20);

        ArrayList<HoFListItem> data = new ArrayList<>();
        data.add(achieve1);
        data.add(achieve2);
        data.add(achieve3);
        data.add(achieve4);
        data.add(achieve5);
        data.add(achieve6);

        ListView listView = findViewById(R.id.hofListView);
        HoFAdapter adapter = new HoFAdapter(data);
        listView.setAdapter(adapter);
    }

    public void testDBConnect() {
        final String TAG = "FireBase>>";
        database = FirebaseDatabase.getInstance().getReference();
        Log.d(TAG, database + " ");
        database.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "user 아래의 자식들의 개수: " + snapshot.getChildrenCount());
                Log.d(TAG, "전체 json 목록 가지고 온 것:" + snapshot.getChildren());
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Log.d(TAG, "하나의 snapshot:" + snapshot1);
                    Log.d(TAG, "하나의 snapshot value:" + snapshot1.getValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu2Pro :
                intent = new Intent(HoFActivity.this, MainActivity.class);
                break;
            case R.id.menu2Challenge :
                intent = new Intent(HoFActivity.this, ChaActivity.class);
                break;
            case R.id.menu2Guild :
                intent = new Intent(HoFActivity.this, GuildActivity.class);
                break;
            case R.id.menu2Friends :
                intent = new Intent(HoFActivity.this, FriendsActivity.class);
                break;
            default :
                intent = new Intent(HoFActivity.this, HoFActivity.class);
                break;
        }
        startActivity(intent);
    }

}

