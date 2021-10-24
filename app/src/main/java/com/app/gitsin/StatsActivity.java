package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StatsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    DatabaseReference database;
    String key;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("info");
        key = intent.getStringExtra("key");

        final String TAG = "FIREBASE";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        b1 = findViewById(R.id.menu4Pro);
        b2 = findViewById(R.id.menu4Hof);
        b3 = findViewById(R.id.menu4Challenge);
        b4 = findViewById(R.id.menu4Stats);
        b5 = findViewById(R.id.menu4Friends);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        database = FirebaseDatabase.getInstance().getReference("users");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                int count = 0;
                Stats[] stats = new Stats[(int) snapshots.getChildrenCount()];
                int stars = 0;
                int streaks = 0;
                for (DataSnapshot snapshot : snapshots.getChildren()) {
                    Stats user = snapshot.getValue(Stats.class);
                    stars += user.getStar();
                    streaks += user.getMaxStreak();
                    stats[count] = user;
                    count++;
                }
                Log.d(TAG, "총 유저수 : " + snapshots.getChildrenCount());
                Log.d(TAG, "유저별 평균 친구수 : " + (stars / snapshots.getChildrenCount()));
                Log.d(TAG, "유저별 평균 연속 모내기수 : " + (streaks / snapshots.getChildrenCount()));

                ArrayList<Map> streakRanked = getRank(stats, 1);
                ArrayList<Map> starRanked = getRank(stats, 2);
                ArrayList<Map> singleRanked = getRank(stats, 3);
                ArrayList<Map> groupRanked = getRank(stats, 4);
                for (int i = 0; i < streakRanked.size(); i++) {
                    Log.d(TAG, "모내기 " + (i + 1) + "등 >> " + streakRanked.get(i));
                    Log.d(TAG, "친구수 " + (i + 1) + "등 >> " + starRanked.get(i));
                    Log.d(TAG, "싱글전 " + (i + 1) + "등 >> " + singleRanked.get(i));
                    Log.d(TAG, "팀전 " + (i + 1) + "등 >> " + groupRanked.get(i));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public ArrayList<Map> getRank(Stats[] stats, int type) {
        ArrayList<Map> array = new ArrayList<>();
        if (type == 1) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getMaxStreak(), t1.getMaxStreak());
                }
            });
            for (int i = 1; i < 4; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("rank", String.valueOf(index));
                map.put("userId", stats[index].getUserId());
                map.put("what", String.valueOf(stats[index].getMaxStreak()));
                array.add(map);
            }
        } else if (type == 2) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getStar(), t1.getStar());
                }
            });
            for (int i = 1; i < 4; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("rank", String.valueOf(index));
                map.put("userId", stats[index].getUserId());
                map.put("what", String.valueOf(stats[index].getStar()));
                array.add(map);
            }
        } else if (type == 3) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getChaPersonDone(), t1.getChaPersonDone());
                }
            });
            for (int i = 1; i < 4; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("rank", String.valueOf(index));
                map.put("userId", stats[index].getUserId());
                map.put("what", String.valueOf(stats[index].getChaPersonDone()));
                array.add(map);
            }
        } else if (type == 4) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getChaGroupDone(), t1.getChaGroupDone());
                }
            });
            for (int i = 1; i < 4; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("rank", String.valueOf(index));
                map.put("userId", stats[index].getUserId());
                map.put("what", String.valueOf(stats[index].getChaGroupDone()));
                array.add(map);
            }
        }
        return array;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu4Pro:
                intent = new Intent(StatsActivity.this, MainActivity.class);
                break;
            case R.id.menu4Hof:
                intent = new Intent(StatsActivity.this, HoFActivity.class);
                break;
            case R.id.menu4Challenge:
                intent = new Intent(StatsActivity.this, ChaActivity.class);
                break;
            case R.id.menu4Friends:
                intent = new Intent(StatsActivity.this, FriendsActivity.class);
                break;
            default:
                intent = new Intent(StatsActivity.this, StatsActivity.class);
                break;
        }
        intent.putExtra("info", user);
        intent.putExtra("key", key);
        startActivity(intent);
    }


}

