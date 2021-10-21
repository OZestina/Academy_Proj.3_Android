package com.app.gitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    DatabaseReference database;
    TextView idView, signDateView;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("info");

        b1 = findViewById(R.id.menu1Pro);
        b2 = findViewById(R.id.menu1Hof);
        b3 = findViewById(R.id.menu1Challenge);
        b4 = findViewById(R.id.menu1Stats);
        b5 = findViewById(R.id.menu1Friends);
        idView = findViewById(R.id.idView);
        signDateView = findViewById(R.id.signDateView);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        database = FirebaseDatabase.getInstance().getReference("users");
        idView.setText(user.getUserId());
        signDateView.setText(user.getSignDate() + "일에 가입");
    }

    @Override
    public void onClick(View view) {
        Class a = null;
        switch (view.getId()) {
            case R.id.menu1Hof :
                a = HoFActivity.class;
                break;
            case R.id.menu1Challenge :
                a = ChaActivity.class;
                break;
            case R.id.menu1Stats :
                a = StatsActivity.class;
                break;
            case R.id.menu1Friends :
                a = FriendsActivity.class;
                break;
            default :
                a = MainActivity.class;
                break;
        }
        Intent intent = new Intent(MainActivity.this, a);
        intent.putExtra("info", user);
        startActivity(intent);
    }

}

