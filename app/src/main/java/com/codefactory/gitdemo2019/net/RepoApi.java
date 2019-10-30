package com.codefactory.gitdemo2019.net;

import com.codefactory.gitdemo2019.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.codefactory.gitdemo2019.net.NetworkService.API_REQUEST_REPOS;
import static com.codefactory.gitdemo2019.net.NetworkService.API_TYPE_ORGS;

public interface RepoApi {

    @GET("orgs/{orgName}/repos")
    Call<List<Repo>> getRepositoriesByOrg(@Path("typeOrg") String typeOrg,
                                          @Path("orgName") String orgName,
                                          @Path("request") String request);

    @GET("orgs/{request}/repos")
    Call<List<Repo>> getRepositoriesByOrg(@Path("request") String request,
                                          @Query("per_page") int limit);
}

/*
https://square.github.io/retrofit/2.x/retrofit/index.html?retrofit2/http/Path.html
 */
