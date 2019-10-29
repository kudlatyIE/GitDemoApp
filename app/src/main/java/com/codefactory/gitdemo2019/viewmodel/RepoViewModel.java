package com.codefactory.gitdemo2019.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.codefactory.gitdemo2019.GitDemoApp;
import com.codefactory.gitdemo2019.data.AppDataBase;
import com.codefactory.gitdemo2019.model.Repo;
import com.codefactory.gitdemo2019.net.NetworkService;
import com.codefactory.gitdemo2019.net.RepoApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.codefactory.gitdemo2019.net.NetworkService.ARG_ORGANISATION_NAME;

public class RepoViewModel extends AndroidViewModel {

    private Application application;
    private AppDataBase appDataBase;
    private final GitDatabaseRepository repositoryInstance;
    private MediatorLiveData<List<Repo>> observableLiveData;

    public RepoViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        this.repositoryInstance = ((GitDemoApp) application).getRepository();

    }

    public LiveData<List<Repo>> getReposFromDb(NetworkService networkService){
        //TODO: try get data from DB if fail >> call retrofit request and insert to DB
        if (repositoryInstance.isDataBaseEmpty().getValue()){
            downloadRepos(networkService, ARG_ORGANISATION_NAME);
        }else {
            observableLiveData =new MediatorLiveData<>();
            observableLiveData.setValue(null);
            LiveData<List<Repo>> repos = repositoryInstance.getRepoLiveData();
            observableLiveData.addSource(repos, observableLiveData::setValue);
        }

        return observableLiveData;
    }


    private void downloadRepos(NetworkService networkService, String orgName){

        Call<List<Repo>> repoCall = NetworkService.createService(RepoApi.class).getRepositoriesByOrg(orgName);
        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                observableLiveData.setValue(response.body());
                //TODO: insert into DB
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}

/*
TODO: insert retrofit response into DB:
                    executors.diskIO().execute(() -> {
                            AppDataBase database = AppDataBase.getAppDataBase(context, executors);
                            List<ProductEntity> products = DataGenerator.generateProducts();
                            List<CommentEntity> comments =
                                    DataGenerator.generateCommentsForProducts(products);
                            insertData(database, products, comments);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });

        private static void insertData(final AppDatabase database, final List<Repo_Entity> repos {
            database.runInTransaction(() -> {
                database.repoDao().insertAll(repos);

            });
        }
 */