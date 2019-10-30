package com.codefactory.gitdemo2019.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RepoViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
//    private String mArg;

    public RepoViewModelFactory(Application application) {
        this.mApplication = application;
//        this.mArg = arg;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }
}
