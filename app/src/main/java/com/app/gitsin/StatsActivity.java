package com.app.gitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;

public class StatsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    DatabaseReference database;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

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

    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu4Pro :
                intent = new Intent(StatsActivity.this, MainActivity.class);
                break;
            case R.id.menu4Hof :
                intent = new Intent(StatsActivity.this, HoFActivity.class);
                break;
            case R.id.menu4Challenge :
                intent = new Intent(StatsActivity.this, ChaActivity.class);
                break;
            case R.id.menu4Friends :
                intent = new Intent(StatsActivity.this, FriendsActivity.class);
                break;
            default :
                intent = new Intent(StatsActivity.this, StatsActivity.class);
                break;
        }
        intent.putExtra("id", id);
        startActivity(intent);
    }

}

