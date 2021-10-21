package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChaActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    DatabaseReference database;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        // 파이어베이스 연동 테스트
        //testDBConnect();

        //크롤링 테스트
        //StreakCrawling sc = new StreakCrawling("ozestina");
        //버튼 없앴음 주의
//        Button crawling = findViewById(R.id.chaBtnCrawl);
//        crawling.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("크롤", sc.getStreakCheckStart());
//                Log.d("크롤", Integer.toString(sc.getMaxStreak()));
//                Log.d("크롤", Integer.toString(sc.getStreakToday()));
//                Log.d("크롤", Integer.toString(sc.getTodayCount()));
//            }
//        });

        // (일단) 데이터 정적 추가 -> DB에서 읽어와야 함
        ChaListItem cha1 = new ChaListItem("호드", "단체전", "2021-10-24", "8/10");
        ChaListItem cha2 = new ChaListItem("얼라이언스","단체전", "2021-10-25", "2/10");
        ChaListItem cha3 = new ChaListItem("와우", "개인전", "2021-10-23", "1/2");

        ArrayList<ChaListItem> data = new ArrayList<>();
        data.add(cha1);
        data.add(cha2);
        data.add(cha3);

        ListView listView = findViewById(R.id.chaListView);
        ChaAdapter adapter = new ChaAdapter(data);
        listView.setAdapter(adapter);


        //하단 메뉴
        b1 = findViewById(R.id.menu3Pro);
        b2 = findViewById(R.id.menu3Hof);
        b3 = findViewById(R.id.menu3Challenge);
        b4 = findViewById(R.id.menu3Guild);
        b5 = findViewById(R.id.menu3Friends);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
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
            case R.id.menu3Pro :
                intent = new Intent(ChaActivity.this, MainActivity.class);
                break;
            case R.id.menu3Hof :
                intent = new Intent(ChaActivity.this, HoFActivity.class);
                break;
            case R.id.menu3Guild :
                intent = new Intent(ChaActivity.this, GuildActivity.class);
                break;
            case R.id.menu3Friends :
                intent = new Intent(ChaActivity.this, FriendsActivity.class);
                break;
            default :
                intent = new Intent(ChaActivity.this, ChaActivity.class);
                break;
        }
        intent.putExtra("id", id);
        startActivity(intent);
    }

}

