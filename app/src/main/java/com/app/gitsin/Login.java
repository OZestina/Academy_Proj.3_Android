package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {

    SqliteHelper sqliteHelper;
    DatabaseReference database;
    Button loginBtn, signBtn;
    EditText id, pw;
    TextView loginResult;
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

                database.orderByChild("userId").equalTo(userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            User user = snapshot1.getValue(User.class);
                            if(userPw.equals(user.getUserPw())){

                                // SQLite3 추가 부분 (10.20 19:48)
                                SQLiteDatabase sqlDB = sqliteHelper.getWritableDatabase();
                                sqliteHelper.onCreate(sqlDB);
                                sqlDB.close();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("id", userId);
                                startActivity(intent);
                            }else {
                                loginResult.setText("비밀번호가 틀렸습니다.");
                            }
                        }
//                        Log.d("파이어베이스>> ", userId + ": userId 상세정보: " + snapshot.getValue().toString().replace("[null, {userPw=", "").split(",")[0]);
//                        if(userPw.equals(snapshot.getValue().toString().replace("[null, {userPw=", "").split(",")[0])){
//                            Intent intent = new Intent(Login.this, MainActivity.class);
//                            intent.putExtra("id", userId);
//                            startActivity(intent);
//                        }else {
//                            loginResult.setText("비밀번호가 틀렸습니다.");
//                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("파이어베이스>> ", userId + ": userId 없음");
                    }
                });
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