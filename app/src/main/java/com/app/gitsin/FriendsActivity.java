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

public class FriendsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    DatabaseReference database;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        b1 = findViewById(R.id.menu5Pro);
        b2 = findViewById(R.id.menu5Hof);
        b3 = findViewById(R.id.menu5Challenge);
        b4 = findViewById(R.id.menu5Guild);
        b5 = findViewById(R.id.menu5Friends);
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
            case R.id.menu5Pro :
                intent = new Intent(FriendsActivity.this, MainActivity.class);
                break;
            case R.id.menu5Hof :
                intent = new Intent(FriendsActivity.this, HoFActivity.class);
                break;
            case R.id.menu5Challenge :
                intent = new Intent(FriendsActivity.this, ChaActivity.class);
                break;
            case R.id.menu5Guild :
                intent = new Intent(FriendsActivity.this, GuildActivity.class);
                break;
            default :
                intent = new Intent(FriendsActivity.this, FriendsActivity.class);
                break;
        }
        intent.putExtra("id", id);
        startActivity(intent);
    }

}

