package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Find extends AppCompatActivity implements View.OnClickListener {

    ImageButton b1, b2, b3, b4, b5;
    DatabaseReference database;
    User user;
    ArrayList<User> arrayList;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("info");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        b1 = findViewById(R.id.menu6Pro);
        b2 = findViewById(R.id.menu6Hof);
        b3 = findViewById(R.id.menu6Challenge);
        b4 = findViewById(R.id.menu6Guild);
        b5 = findViewById(R.id.menu6Friends);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        arrayList = new ArrayList<>();

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    User user = snapshot1.getValue(User.class);
                    arrayList.add(user);
                }
                i = arrayList.size();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Log.d("aaa", arrayList.toString());
        ListView listView = findViewById(R.id.findListView);
        findAdapter adapter = new findAdapter(arrayList, user);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu6Pro :
                intent = new Intent(Find.this, MainActivity.class);
                break;
            case R.id.menu6Hof :
                intent = new Intent(Find.this, HoFActivity.class);
                break;
            case R.id.menu6Challenge :
                intent = new Intent(Find.this, ChaActivity.class);
                break;
            case R.id.menu6Guild :
                intent = new Intent(Find.this, GuildActivity.class);
                break;
            default :
                intent = new Intent(Find.this, FriendsActivity.class);
                break;
        }
        intent.putExtra("info", user);
        startActivity(intent);
    }

}
