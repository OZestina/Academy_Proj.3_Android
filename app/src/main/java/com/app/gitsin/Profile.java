package com.app.gitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    TextView idView, signDateView, gitIdView, todayCheckMsg, streakCheckMsg;
    User user, user2;
    String key;
    ImageView todayCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ThemeUtil.applyTheme(ThemeUtil.darkLoad(getApplicationContext()));

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("info");       //로그인된 User
        key = intent.getStringExtra("key");                     //로그인된 key
        user2 = (User)intent.getSerializableExtra("info2");     //프로필 주인 User

        b1 = findViewById(R.id.menu7Pro);
        b2 = findViewById(R.id.menu7Hof);
        b3 = findViewById(R.id.menu7Challenge);
        b4 = findViewById(R.id.menu7Stats);
        b5 = findViewById(R.id.menu7Friends);
        idView = findViewById(R.id.idView2);
        gitIdView = findViewById(R.id.gitIdView2);
        signDateView = findViewById(R.id.signDateView2);
        todayCheck = findViewById(R.id.todayCheck2);
        todayCheckMsg = findViewById(R.id.todayCheckMsg2);
        streakCheckMsg = findViewById(R.id.streakCheckMsg2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        idView.setText(user2.getUserId());
        gitIdView.setText(user2.getGithubId());
        signDateView.setText(user2.getSignDate() + "일에 가입");
        if (user2.getTodayCount()==1){
            todayCheck.setImageResource(android.R.drawable.presence_online);
            todayCheckMsg.setVisibility(View.INVISIBLE);
        }
        streakCheckMsg.setText(user2.getStreakToday()+"일 연속 업로드중입니다.");


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu7Pro :
                intent = new Intent(Profile.this, MainActivity.class);
                break;
            case R.id.menu7Hof :
                intent = new Intent(Profile.this, HoFActivity.class);
                break;
            case R.id.menu7Stats :
                intent = new Intent(Profile.this, StatsActivity.class);
                break;
            case R.id.menu7Friends :
                intent = new Intent(Profile.this, FriendsActivity.class);
                break;
            default :
                intent = new Intent(Profile.this, ChaActivity.class);
                break;
        }
        intent.putExtra("info", user);
        intent.putExtra("key", key);
        startActivity(intent);
    }

}

