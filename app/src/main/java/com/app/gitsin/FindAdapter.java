package com.app.gitsin;

import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FindAdapter extends BaseAdapter {
    ArrayList<User> userList;
    User user, my;
    String key;
    String star;
    ArrayList<String> idList = new ArrayList<String>();

    public FindAdapter(ArrayList<User> userList, User user, String key) {
        this.userList = userList;
        my = user;
        this.key = key;
        star = user.getStar();
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        View find = View.inflate(context, R.layout.item_find, null);
        TextView findUser = find.findViewById(R.id.findUser);
        TextView findGit = find.findViewById(R.id.findGit);
        Button findBtn = find.findViewById(R.id.findBtn);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        database.orderByChild("userId").equalTo(userList.get(i).getUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    user = snapshot1.getValue(User.class);
                    findUser.setText(user.getUserId());
                    findGit.setText(user.getGithubId());
                    idList.add(user.getUserId());
                    if (my.getStar().contains(user.getUserId())){
                        findBtn.setVisibility(View.INVISIBLE);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = star + idList.get(i) + ",";  //user가 최종값으로 저장되서 버튼 3개다 cc로 추가됨. 수정해야함
                my.setStar(star);
                database.child(key).setValue(my).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                            findBtn.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        return find;
    }
}