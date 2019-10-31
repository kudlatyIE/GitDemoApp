package com.codefactory.gitdemo2019.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.codefactory.gitdemo2019.AppExecutors;
import com.codefactory.gitdemo2019.GitDemoApp;
import com.codefactory.gitdemo2019.data.AppDataBase;
import com.codefactory.gitdemo2019.model.Repo;
import com.codefactory.gitdemo2019.net.NetworkService;
import com.codefactory.gitdemo2019.net.RepoApi;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.codefactory.gitdemo2019.net.NetworkService.API_REQUEST_REPOS;
import static com.codefactory.gitdemo2019.net.NetworkService.API_TYPE_ORGS;
import static com.codefactory.gitdemo2019.net.NetworkService.ARG_ORGANISATION_NAME;
import static com.codefactory.gitdemo2019.net.NetworkService.FULL_REQUEST;

public class RepoViewModel extends AndroidViewModel {
    private static final String TAG = RepoViewModel.class.getSimpleName();

    private Application application;
//    private AppDataBase appDataBase;
    private final GitDatabaseRepository repositoryInstance;
    private MediatorLiveData<List<Repo>> observableLiveData;
    private LiveData<List<Repo>> searchByLiveData;
    private LiveData<String> filterLiveData = new MutableLiveData<String>();
    private MutableLiveData<String> mutablePattern = new MutableLiveData<>();

    public RepoViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        this.repositoryInstance = ((GitDemoApp) application).getRepository();
        observableLiveData =new MediatorLiveData<>();
        observableLiveData.setValue(null);
        LiveData<List<Repo>> repos = repositoryInstance.getRepoLiveData();
        observableLiveData.addSource(repos, observableLiveData::setValue);

        if (repos.getValue()!=null){
            Log.d(TAG, "repositoryInstance.getRepoLiveData() size: "+repos.getValue().size());
        }else {
            Log.d(TAG, "repositoryInstance.getRepoLiveData() NULL ");
        }
    }



    public void searchUpdate(String pattern){
        mutablePattern.postValue(pattern);
        LiveData<List<Repo>> repos = repositoryInstance.search(pattern);
        Log.d(TAG, "searchUpdate >> pattern: "+pattern);
//        Log.d(TAG, "search >> repos size: "+repos.getValue().size());
        observableLiveData.postValue(repos.getValue());
    }

    public LiveData<List<Repo>> getReposFromDb(String orgName, int limit){
        //TODO: try get data from DB if fail >> call retrofit request and insert to DB
        if (observableLiveData.getValue()==null || observableLiveData.getValue().size()==0)
            downloadRepos(orgName, limit);

        return observableLiveData;
    }


    private void downloadRepos(String orgName, int dataLimit){

        Call<List<Repo>> repoCall = NetworkService.createService(RepoApi.class).getRepositoriesByOrg(orgName, dataLimit);//.getRepositoriesByOrg(API_TYPE_ORGS, orgName, API_REQUEST_REPOS);
        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d(TAG, "response code: "+response.code());
                Log.d(TAG, "response message: "+response.message());
                Log.d(TAG, "response STRING: "+response.toString());

                if (response.body()!=null){
                    observableLiveData.setValue(response.body());
                    //TODO: insert into DB
                    insertData(((GitDemoApp) application).getDatabase(), ((GitDemoApp) application).getAppExecutors(), response.body());
                }else {
                    Log.d(TAG, "response data: NULL....");
                }

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void insertData(AppDataBase dataBase, AppExecutors executors, List<Repo> data){
        if (dataBase!=null){
            executors.diskIO().execute(
                    () -> dataBase.runInTransaction(
                            ()-> dataBase.repoDao().insertAllRepos(data)
                    )
            );
        }
    }


}

