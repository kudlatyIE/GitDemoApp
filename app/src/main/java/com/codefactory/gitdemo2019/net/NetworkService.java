package com.codefactory.gitdemo2019.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static final String BASE_URL = "https://api.github.com/";
    public static final String API_TYPE_ORGS = "orgs";
    public static final String API_REQUEST_REPOS = "repos";
    public static final String ARG_ORGANISATION_NAME = "ApplauseOSS";
    public static final String FULL_REQUEST = "/orgs/ApplauseOSS/repos/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
