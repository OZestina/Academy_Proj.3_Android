package com.app.gitsin;

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

public class findAdapter extends BaseAdapter {
        ArrayList<User> userList;
        User user, my;
        String key;

        public findAdapter(ArrayList<User> userList, User user) {
            this.userList = userList;
            my = user;
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
                        key = snapshot1.getKey();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            findBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String star = my.getStar();
                    star = star + user.getUserId();
                    database.child(key).child("star").setValue(star).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("ssss","3번");
                        }
                    });
                }
            });
            return find;
        }
}
