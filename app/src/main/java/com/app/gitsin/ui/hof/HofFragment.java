package com.app.gitsin.ui.hof;

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

public class HofFragment extends Fragment {

    private HofViewModel hofViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hofViewModel =
                new ViewModelProvider(this).get(HofViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hof, container, false);
        final TextView textView = root.findViewById(R.id.text_hof);
        hofViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}