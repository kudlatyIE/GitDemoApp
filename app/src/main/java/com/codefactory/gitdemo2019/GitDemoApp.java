package com.codefactory.gitdemo2019;

import android.app.Application;

import com.codefactory.gitdemo2019.data.AppDataBase;
import com.codefactory.gitdemo2019.viewmodel.GitDatabaseRepository;

public class GitDemoApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public AppDataBase getDatabase() {
        return AppDataBase.getAppDataBase(this, mAppExecutors);
    }

    public GitDatabaseRepository getRepository() {
        return GitDatabaseRepository.getInstance(getDatabase());
    }
}
