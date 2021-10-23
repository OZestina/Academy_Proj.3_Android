package com.app.gitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    ImageView findBtn;
    DatabaseReference database;
    User user;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("info");
        key = intent.getStringExtra("key");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        b1 = findViewById(R.id.menu5Pro);
        b2 = findViewById(R.id.menu5Hof);
        b3 = findViewById(R.id.menu5Challenge);
        b4 = findViewById(R.id.menu5Stats);
        b5 = findViewById(R.id.menu5Friends);
        findBtn = findViewById(R.id.findBtn);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        findBtn.setOnClickListener(this);

        ArrayList<String> idList = new ArrayList<String>();
        String[] starList = user.getStar().split(",");
        for (String i: starList) {
            idList.add(i);
        }
        ListView listView = findViewById(R.id.starListView);
        StarAdapter adapter = new StarAdapter(idList, user, key);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu5Pro :
                intent = new Intent(FriendsActivity.this, MainActivity.class);
                break;
            case R.id.menu5Hof :
                intent = new Intent(FriendsActivity.this, HoFActivity.class);
                break;
            case R.id.menu5Challenge :
                intent = new Intent(FriendsActivity.this, ChaActivity.class);
                break;
            case R.id.menu5Stats :
                intent = new Intent(FriendsActivity.this, StatsActivity.class);
                break;
            case R.id.findBtn :
                intent = new Intent(FriendsActivity.this, Find.class);
                break;
            default :
                intent = new Intent(FriendsActivity.this, FriendsActivity.class);
                break;
        }
        intent.putExtra("info", user);
        intent.putExtra("key", key);
        startActivity(intent);
    }

}

