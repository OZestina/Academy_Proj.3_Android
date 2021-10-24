package com.app.gitsin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class HoFActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    SqliteHelper sqliteHelper;
    User user;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 로그용 태그
        final String TAG = "FIREBASE";

        // sqlite3 연결
        sqliteHelper = new SqliteHelper(this);

        // 이전 액티비티에서 값 넘겨 받기
        Intent intentPre = getIntent();
        user = (User) intentPre.getSerializableExtra("info");
        key = intentPre.getStringExtra("key");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_f);

        // 액티비티 이동 버튼
        b1 = findViewById(R.id.menu2Pro);
        b2 = findViewById(R.id.menu2Hof);
        b3 = findViewById(R.id.menu2Challenge);
        b4 = findViewById(R.id.menu2Stats);
        b5 = findViewById(R.id.menu2Friends);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        // 업적 관련 반복문에 사용될 i 최대값
        final int streakBASED = 3;
        final int starBASED = 6;
        final int chaSingleBASED = 8;
        final int chaTeamBASED = 10;

        // sqlite에서 읽어온 업적 정보 list
        ArrayList<HoFAchievement> list = hofReadSqlite();

        // 어댑터에 끼울 어레이리스트 data
        ArrayList<HoFListItem> data = new ArrayList<>();
        // 계정별 친구목록 aa,bb,cc, 형식 string "," split 후 갯수 = stars
        int stars = (user.getStar().length() == 0) ? 0 : user.getStar().split(",").length;
        Log.d(TAG, "친구 : " + user.getStar() + "       친구수 : " + stars);

        // data 어레이리스트에 HoFListItem 객체 item을 list 길이만큼 반복하여 add
        for (int i = 0; i < list.size(); i++) {
            int drawableId = getApplicationContext().getResources().getIdentifier(list.get(i).getAchDrawable(),
                    "drawable", getApplicationContext().getPackageName());
            // 업적별 이미지 소스, 업적 타이틀, 업적 정보
            HoFListItem item = new HoFListItem(drawableId,
                    list.get(i).getAchTitle(), list.get(i).getAchDetail());
            // 업적 분야별로 현재값/최대값 set
            if (i < streakBASED) {
                item.setNowMax(user.getMaxStreak(), list.get(i).getAchMax());
            } else if (i < starBASED) {
                item.setNowMax(stars, list.get(i).getAchMax());
            } else if (i < chaSingleBASED) {
                item.setNowMax(user.getChaPersonDone(), list.get(i).getAchMax());
            } else if (i < chaTeamBASED) {
                item.setNowMax(user.getChaGroupDone(), list.get(i).getAchMax());
            }
            // data 어레이리스트에 item 추가
            data.add(item);
        }

        // 완료, 진행 중 카운터
        int countComplete = 0;
        int countIng = 0;
        for (int i = 0; i < data.size(); i++) {
            // data에 들어있는 item 하나씩 값을 가져와 각각 더해준다.
            countComplete += data.get(i).getAchComplete();
            countIng += data.get(i).getAchIng();
        }

        // 카운터를 setText
        TextView completeT = findViewById(R.id.hofCompleteT);
        TextView ingT = findViewById(R.id.hofIngT);
        completeT.setText(String.valueOf(countComplete));
        ingT.setText(String.valueOf(countIng));

        // 현재 activity layout에 존재하는 ListView 객체화
        ListView listView = findViewById(R.id.hofListView);
        HoFAdapter adapter = new HoFAdapter(data);
        // 어댑터를 껴준다.
        listView.setAdapter(adapter);

        // 위로 스와이프 리프레시
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 어댑터 새로고침
                adapter.notifyDataSetChanged();
                // false로 호출 안하면 새로고침 아이콘이 사라지지 않는다.
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private ArrayList<HoFAchievement> hofReadSqlite() {
        SQLiteDatabase sqlDB = sqliteHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM achievement;", null);
        ArrayList<HoFAchievement> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            HoFAchievement dto = new HoFAchievement();
            dto.setAchDrawable(cursor.getString(0));
            dto.setAchTitle(cursor.getString(1));
            dto.setAchDetail(cursor.getString(2));
            dto.setAchMax(cursor.getInt(3));
            list.add(dto);
        }
        return list;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu2Pro:
                intent = new Intent(HoFActivity.this, MainActivity.class);
                break;
            case R.id.menu2Challenge:
                intent = new Intent(HoFActivity.this, ChaActivity.class);
                break;
            case R.id.menu2Stats:
                intent = new Intent(HoFActivity.this, StatsActivity.class);
                break;
            case R.id.menu2Friends:
                intent = new Intent(HoFActivity.this, FriendsActivity.class);
                break;
            default:
                intent = new Intent(HoFActivity.this, HoFActivity.class);
                break;
        }
        intent.putExtra("info", user);
        intent.putExtra("key", key);
        startActivity(intent);
    }

}

