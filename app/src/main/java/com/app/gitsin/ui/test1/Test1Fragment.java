package com.app.gitsin.ui.test1;

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

public class Test1Fragment extends Fragment {

    private Test1ViewModel test1ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        test1ViewModel =
                new ViewModelProvider(this).get(Test1ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_test1, container, false);
        final TextView textView = root.findViewById(R.id.text_test1);
        test1ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}