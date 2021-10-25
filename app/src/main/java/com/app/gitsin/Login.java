package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends AppCompatActivity {

    SqliteHelper sqliteHelper;
    DatabaseReference database;
    Button loginBtn, signBtn;
    EditText id, pw;
    TextView loginResult;
    Intent intent;

    String userKey;
    User user;
    AtomicBoolean done = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // SQLite3 추가 부분 (10.20 19:15)
        sqliteHelper = new SqliteHelper(this);

        loginBtn = findViewById(R.id.loginBtn);
        signBtn = findViewById(R.id.goSignPageBtn);
        id = findViewById(R.id.inId);
        pw = findViewById(R.id.inPw);
        loginResult = findViewById(R.id.loginResult);



        database = FirebaseDatabase.getInstance().getReference("users");
        Log.d("파이어베이스>> ", database + " ");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userId = id.getText().toString();
                final String userPw = pw.getText().toString();

                database.orderByChild("userId").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                user = snapshot1.getValue(User.class);
                                userKey = snapshot1.getKey();
                            }
                            if(userPw.equals(user.getUserPw())){

                                //Crawling 내용 FireBase 저장
                                StreakCrawling sc = new StreakCrawling(user);
                                sc.start();

                                try {
                                    sc.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                user = sc.crawlingResult();
                                //                                Log.d("crawling값3",user.toString());

                                database.child(userKey).setValue(user);
                                done.set(true);
                                if (done.get()){
                                    Log.d("=================", "done이 true값");
                                }

                                // SQLite3 추가 부분 (10.20 19:48)
                                SQLiteDatabase sqlDB = sqliteHelper.getWritableDatabase();
                                sqliteHelper.onCreate(sqlDB);
                                sqlDB.close();

                                intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("info", user);
                                intent.putExtra("key", userKey);

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        while (done.get() != true) { Log.d("","DONE값 없음");}
                                        try {
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 1000);
                            }else {
                                loginResult.setText("비밀번호가 틀렸습니다.");
                            }
                        } catch (Exception e) {
                            loginResult.setText("가입되지 않은 아이디입니다.");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        loginResult.setText("가입되지 않은 아이디입니다.");
                    }
                });//end of addValueEventListener
            }
        });
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Sign.class);
                startActivity(intent);
            }
        });
    }
}