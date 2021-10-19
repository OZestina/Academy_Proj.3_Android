package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

