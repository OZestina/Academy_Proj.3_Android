package com.app.gitsin.ui.test1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Test1ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Test1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is test1 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}