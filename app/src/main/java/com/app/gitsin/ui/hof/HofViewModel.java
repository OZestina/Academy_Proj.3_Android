package com.app.gitsin.ui.hof;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HofViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HofViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is hof fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}