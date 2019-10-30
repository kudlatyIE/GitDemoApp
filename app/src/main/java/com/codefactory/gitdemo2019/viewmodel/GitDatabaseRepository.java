package com.codefactory.gitdemo2019.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.codefactory.gitdemo2019.data.AppDataBase;
import com.codefactory.gitdemo2019.model.Repo;

import java.util.List;

public class GitDatabaseRepository {

    private AppDataBase appDataBase;
    private static GitDatabaseRepository repositoryInstance;
    private MediatorLiveData<List<Repo>> observableLiveData;
    private final MutableLiveData<Boolean> isDatabaseEmpty = new MutableLiveData<>();
    private boolean isDBempty = true;

    private GitDatabaseRepository(final AppDataBase appDataBase){
        this.appDataBase=appDataBase;
        this.observableLiveData =new MediatorLiveData<>();

//        repositoryInstance =getInstance(appDataBase);

        this.observableLiveData.addSource(appDataBase.repoDao().getAllRepos(),
                repoEntitiess ->{
                    if (appDataBase.getDatabaseCreated().getValue()!=null){
                        observableLiveData.postValue(repoEntitiess);
                    }
                });
    }

    public static GitDatabaseRepository getInstance(final AppDataBase database) {
        if (repositoryInstance == null) {
            synchronized (GitDatabaseRepository.class) {
                if (repositoryInstance == null) {
                    repositoryInstance = new GitDatabaseRepository(database);
                }
            }
        }
        return repositoryInstance;
    }



    public LiveData<List<Repo>> getRepoLiveData(){
        return observableLiveData;
    }

    /**
     * if DB is empty then call retrofit request getAllRepos();
     * @return boolean
     */
    public LiveData<Boolean> isDataBaseEmpty(){
        isDatabaseEmpty.postValue(appDataBase.repoDao().getAnyRepo().getValue()==null);
        return isDatabaseEmpty;
    }
}
