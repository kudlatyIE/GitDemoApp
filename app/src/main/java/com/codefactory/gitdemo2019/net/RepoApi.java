package com.codefactory.gitdemo2019.net;

import com.codefactory.gitdemo2019.model.Repo;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.codefactory.gitdemo2019.net.NetworkService.API_REQUEST_REPOS;
import static com.codefactory.gitdemo2019.net.NetworkService.API_TYPE_ORGS;

public interface RepoApi {

    @GET(API_TYPE_ORGS+"{orgName}"+API_REQUEST_REPOS)
    Call<Repo> getRepositoriesByOrg(String orgName);
}

/*
https://square.github.io/retrofit/2.x/retrofit/index.html?retrofit2/http/Path.html
 */
