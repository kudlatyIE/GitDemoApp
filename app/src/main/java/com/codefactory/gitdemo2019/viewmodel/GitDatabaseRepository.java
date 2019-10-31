package com.codefactory.gitdemo2019.viewmodel;

import android.content.Context;
import android.util.Log;

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

    private GitDatabaseRepository(final AppDataBase appDataBase){
        this.appDataBase=appDataBase;
        this.observableLiveData =new MediatorLiveData<>();


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

    public LiveData<List<Repo>> search(String pattern){
        if (pattern!=null && pattern.length()>0){
            LiveData<List<Repo>>  dataSearch = appDataBase.repoDao().findRepo("%".concat(pattern).concat("%"));
            Log.d("REPO", "search >> pattern: "+pattern);
            Log.d("REPO", "search >> is appDataBase NULL: "+(appDataBase==null));
            if (dataSearch.getValue()!=null)Log.d("REPO", "search >> data size: "+dataSearch.getValue().size());
//            observableLiveData.postValue(dataSearch);
            return dataSearch;
        }
        return appDataBase.repoDao().getAllRepos();
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
