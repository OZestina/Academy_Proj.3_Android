package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HoFActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    SqliteHelper sqliteHelper;
    User user;
    HoFListItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String TAG = "FIREBASE";

        sqliteHelper = new SqliteHelper(this);

        Intent intentPre = getIntent();
        user = (User) intentPre.getSerializableExtra("info");

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

        ArrayList<HoFAchievement> list = hofReadSqlite();

        final int[] streakBASED = {0, 3};
        final int[] starBASED = {3, 6};
        final int[] chaSingleBASED = {6, 8};
        final int[] chaTeamBASED = {8, 10};

        ArrayList<HoFListItem> data = new ArrayList<>();
        int stars = user.getStar().split(",").length;

        for (int i = 0; i < list.size(); i++) {
            int drawableId = getApplicationContext().getResources().getIdentifier(list.get(i).getAchDrawable(),
                    "drawable", getApplicationContext().getPackageName());
            if (streakBASED[0] <= i && i < streakBASED[1]) {
                item = new HoFListItem(
                    drawableId,
                    list.get(i).getAchTitle(),
                    list.get(i).getAchDetail(),
                    user.getMaxStreak(),
                    list.get(i).getAchMax());
            } else if (starBASED[0] <= i && i < starBASED[1]) {
                item = new HoFListItem(
                        drawableId,
                        list.get(i).getAchTitle(),
                        list.get(i).getAchDetail(),
                        stars,
                        list.get(i).getAchMax());
            } else if (chaSingleBASED[0] <= i && i < chaSingleBASED[1]) {
                item = new HoFListItem(
                        drawableId,
                        list.get(i).getAchTitle(),
                        list.get(i).getAchDetail(),
                        user.getChaPersonDone(),
                        list.get(i).getAchMax());
            } else {
                item = new HoFListItem(
                        drawableId,
                        list.get(i).getAchTitle(),
                        list.get(i).getAchDetail(),
                        user.getChaGroupDone(),
                        list.get(i).getAchMax());
            }
            data.add(item);
        }

        // 완료, 진행 중 카운터
        int countComplete = 0;
        int countIng = 0;
        for (int i = 0; i < data.size(); i++) {
            // DTO에서 값을 가져와 하나하나 더해준다.
            countComplete += data.get(i).getAchComplete();
            countIng += data.get(i).getAchIng();
        }

        // 카운터를 setText
        TextView completeT = findViewById(R.id.hofCompleteT);
        TextView ingT = findViewById(R.id.hofIngT);
        completeT.setText(String.valueOf(countComplete));
        ingT.setText(String.valueOf(countIng));

        ListView listView = findViewById(R.id.hofListView);
        HoFAdapter adapter = new HoFAdapter(data);
        listView.setAdapter(adapter);

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
            case R.id.menu2Guild:
                intent = new Intent(HoFActivity.this, GuildActivity.class);
                break;
            case R.id.menu2Friends:
                intent = new Intent(HoFActivity.this, FriendsActivity.class);
                break;
            default:
                intent = new Intent(HoFActivity.this, HoFActivity.class);
                break;
        }
        intent.putExtra("info", user);
        startActivity(intent);
    }

}

