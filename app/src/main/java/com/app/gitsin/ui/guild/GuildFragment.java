package com.app.gitsin.ui.guild;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.gitsin.R;

public class GuildFragment extends Fragment {

    private GuildViewModel guildViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        guildViewModel =
                new ViewModelProvider(this).get(GuildViewModel.class);
        View root = inflater.inflate(R.layout.fragment_guild, container, false);
        final TextView textView = root.findViewById(R.id.text_guild);
        guildViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}