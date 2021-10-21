package com.app.gitsin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Sign extends AppCompatActivity {
    DatabaseReference database;
    EditText id, pw, pw2, gitId;
    Button checkBtn, signBtn;
    TextView pwCheckMsg, idCheckMsg;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        id = findViewById(R.id.inId2);
        pw = findViewById(R.id.inPw2);
        pw2 = findViewById(R.id.inPw3);
        gitId = findViewById(R.id.inGitId);
        checkBtn = findViewById(R.id.idCheckBtn);
        signBtn = findViewById(R.id.signBtn);
        pwCheckMsg = findViewById(R.id.pwCheckMsg);
        idCheckMsg = findViewById(R.id.idCheckMsg);


        database = FirebaseDatabase.getInstance().getReference("users");
        Log.d("파이어베이스>> ", database + " ");


        //아이디 중복확인
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userId = id.getText().toString();
                database.orderByChild("userId").equalTo(userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("파이어베이스>> ", userId + ": userId 상세정보: " + snapshot.getValue());
                        if(!snapshot.getValue().equals(null)){
                            idCheckMsg.setText("이미 사용중인 아이디입니다.");
                        }else {
                            idCheckMsg.setText("");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("파이어베이스>> ", userId + ": userId 없음");
                    }
                });
            }
        });
        //비밀번호 일치 확인
        pw2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if (pw.getText().toString().equals(pw2.getText().toString())){
                        pwCheckMsg.setText("");
                    }else {
                        pwCheckMsg.setText("비밀번호가 일치하지 않습니다.");
                    }
                }
            }
        });

        database.orderByChild("userId").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                i = (int) snapshot.getChildrenCount() + 1;
                Log.d("파이어베이스>> ", i + "");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inId = id.getText().toString();
                String inPw = pw.getText().toString();
                String inGitId = gitId.getText().toString();
                TimeZone tz;                                        // 객체 생성
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
                tz = TimeZone.getTimeZone("Asia/Seoul");  // TimeZone에 표준시 설정
                dateFormat.setTimeZone(tz);                    //DateFormat에 TimeZone 설정

                Date date = new Date();                        // 현재 날짜가 담긴 Date 객체 생성
                Log.d("DATE", dateFormat.format(date));     //  출력
                if(idCheckMsg.getText().toString().equals("")&&pwCheckMsg.getText().toString().equals("")){
                    User user = new User(inId, inPw, dateFormat.format(date), inGitId, "", "", 0,0,0,0,0,0,0);
                    database.child(String.valueOf(i)).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Sign.this, "가입을 완료했습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Sign.this, Login.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Sign.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"가입 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}