package com.app.gitsin;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StarAdapter extends BaseAdapter {
    ArrayList<String> idList;
    User user;
    String key;

    public StarAdapter(ArrayList<String> idList) {
        this.idList = idList;
    }

    @Override
    public int getCount() {
        return idList.size();
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
        View star = View.inflate(context, R.layout.item_star, null);
        TextView starUser = star.findViewById(R.id.starUser);
        TextView starGit = star.findViewById(R.id.starGit);
        Button starBtn = star.findViewById(R.id.starBtn);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        database.orderByChild("userId").equalTo(idList.get(i)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    user = snapshot1.getValue(User.class);
                    starUser.setText(user.getUserId());
                    starGit.setText(user.getGithubId());
                    key = snapshot1.getKey();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        starBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return star;
    }
}