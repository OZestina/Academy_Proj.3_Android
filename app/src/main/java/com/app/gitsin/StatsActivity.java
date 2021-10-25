package com.app.gitsin;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    TextView total1, total2, total3;
    TabHost tabHost;
    ListView streakLV, starLV, singleLV, groupLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("info");
        key = intent.getStringExtra("key");

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

        total1 = findViewById(R.id.stats_total1);
        total2 = findViewById(R.id.stats_total2);
        total3 = findViewById(R.id.stats_total3);

        // tabHost 객체화 & 초기화
        tabHost = findViewById(R.id.stats_tabHost);
        tabHost.setup();

        // tab 생성
        TabHost.TabSpec streakTab = tabHost.newTabSpec("tab1");
        TabHost.TabSpec starTab = tabHost.newTabSpec("tab2");
        TabHost.TabSpec singleTab = tabHost.newTabSpec("tab3");
        TabHost.TabSpec groupTab = tabHost.newTabSpec("tab4");

        // tab 아이콘 설정
        streakTab.setIndicator("", getResources().getDrawable(R.drawable.stats_01));
        starTab.setIndicator("", getResources().getDrawable(R.drawable.stats_02));
        singleTab.setIndicator("", getResources().getDrawable(R.drawable.stats_03));
        groupTab.setIndicator("", getResources().getDrawable(R.drawable.stats_04));

        // tab별로 페이지 설정 (각 탭마다 해당 ListView 설정)
        streakTab.setContent(R.id.stats_view1);
        starTab.setContent(R.id.stats_view2);
        singleTab.setContent(R.id.stats_view3);
        groupTab.setContent(R.id.stats_view4);

        // listView 객체화
        streakLV = findViewById(R.id.stats_list1);
        starLV = findViewById(R.id.stats_list2);
        singleLV = findViewById(R.id.stats_list3);
        groupLV = findViewById(R.id.stats_list4);

        // tabHost에 tab을 add
        tabHost.addTab(streakTab);
        tabHost.addTab(starTab);
        tabHost.addTab(singleTab);
        tabHost.addTab(groupTab);

        // 첫 시작 tab
        tabHost.setCurrentTabByTag("tab1");

        fetchData();
    }

    // 위로 스와이프 리프레시
    private void refresh(SwipeRefreshLayout layout) {
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
                // false로 호출 안하면 새로고침 아이콘이 사라지지 않는다.
                layout.setRefreshing(false);
            }
        });
    }

    private void fetchData() {
        database = FirebaseDatabase.getInstance().getReference("users");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                // forEach에서 사용될 count 변수
                int count = 0;
                // children 개수만큼의 Stats 클래스 배열
                Stats[] stats = new Stats[(int) snapshots.getChildrenCount()];
                // 누적용 stars, streaks 변수 초기화
                int stars = 0;
                int streaks = 0;
                for (DataSnapshot snapshot : snapshots.getChildren()) {
                    // snapshot 하나씩 Stats 클래스에 담기
                    Stats user = snapshot.getValue(Stats.class);
                    // 친구수, 연속 모내기수 ++
                    stars += user.getStar();
                    streaks += user.getMaxStreak();
                    // stats 객체 배열에 담기
                    stats[count] = user;
                    count++;
                }

                // 숫자 애니메이션 (0부터 지정값까지)
                ValueAnimator animator1 = ValueAnimator.ofInt(0, (int)snapshots.getChildrenCount());
                ValueAnimator animator2 = ValueAnimator.ofInt(0, streaks / (int)snapshots.getChildrenCount());
                ValueAnimator animator3 = ValueAnimator.ofInt(0, stars / (int)snapshots.getChildrenCount());

                // 지속시간
                animator1.setDuration(1000);
                animator2.setDuration(1000);
                animator3.setDuration(1000);

                // 애니메이션 리스너
                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        total1.setText(animation.getAnimatedValue().toString() + "명");
                    }
                });
                animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        total2.setText(animation.getAnimatedValue().toString() + "일");
                    }
                });
                animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        total3.setText(animation.getAnimatedValue().toString() + "명");
                    }
                });

                // 애니메이션 활성화
                animator1.start();
                animator2.start();
                animator3.start();

                // getRank() 메서드 : 각각 항목별 map이 순위별로 정렬된 arraylist
                ArrayList<Map> streakRanked = getRank(stats, 1);
                ArrayList<Map> starRanked = getRank(stats, 2);
                ArrayList<Map> singleRanked = getRank(stats, 3);
                ArrayList<Map> groupRanked = getRank(stats, 4);

                // arrayList를 입력하여 어댑터 호출
                StatsAdapter adapter1 = new StatsAdapter(streakRanked, user.getUserId());
                StatsAdapter adapter2 = new StatsAdapter(starRanked, user.getUserId());
                StatsAdapter adapter3 = new StatsAdapter(singleRanked, user.getUserId());
                StatsAdapter adapter4 = new StatsAdapter(groupRanked, user.getUserId());

                // 존재하는 ListView에 어댑터를 껴준다.
                streakLV.setAdapter(adapter1);
                starLV.setAdapter(adapter2);
                singleLV.setAdapter(adapter3);
                groupLV.setAdapter(adapter4);

                SwipeRefreshLayout swipe1 = findViewById(R.id.stats_swipe1);
                SwipeRefreshLayout swipe2 = findViewById(R.id.stats_swipe2);
                SwipeRefreshLayout swipe3 = findViewById(R.id.stats_swipe3);
                SwipeRefreshLayout swipe4 = findViewById(R.id.stats_swipe4);

                refresh(swipe1);
                refresh(swipe2);
                refresh(swipe3);
                refresh(swipe4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("FIREBASE", error.getMessage());
            }
        });
    }

    public ArrayList<Map> getRank(Stats[] stats, int type) {
        ArrayList<Map> array = new ArrayList<>();
        final int rankSize = stats.length;
        if (type == 1) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getMaxStreak(), t1.getMaxStreak());
                }
            });
            for (int i = 1; i <= rankSize; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("userId", stats[index].getUserId());
                map.put("what", stats[index].getMaxStreak() + "일");
                array.add(map);
            }
        } else if (type == 2) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getStar(), t1.getStar());
                }
            });
            for (int i = 1; i <= rankSize; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("userId", stats[index].getUserId());
                map.put("what", stats[index].getStar() + "명");
                array.add(map);
            }
        } else if (type == 3) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getChaPersonDone(), t1.getChaPersonDone());
                }
            });
            for (int i = 1; i <= rankSize; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("userId", stats[index].getUserId());
                map.put("what", stats[index].getChaPersonDone() + "개");
                array.add(map);
            }
        } else if (type == 4) {
            Arrays.sort(stats, new Comparator<Stats>() {
                @Override
                public int compare(Stats t, Stats t1) {
                    return Integer.compare(t.getChaGroupDone(), t1.getChaGroupDone());
                }
            });
            for (int i = 1; i <= rankSize; i++) {
                int index = stats.length - i;
                Map<String, String> map = new HashMap<>();
                map.put("userId", stats[index].getUserId());
                map.put("what", stats[index].getChaGroupDone() + "개");
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

