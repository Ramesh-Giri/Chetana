package com.chetana.ramesh.chetana.api;

import com.chetana.ramesh.chetana.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET(ApiConstant.GET_NEWS)
    Call<NewsResponse> getNews();

}
