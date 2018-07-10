package com.chetana.ramesh.chetana.api;

import com.chetana.ramesh.chetana.model.FaqsResponse;
import com.chetana.ramesh.chetana.model.InfoResponse;
import com.chetana.ramesh.chetana.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET(ApiConstant.GET_NEWS)
    Call<NewsResponse> getNews();

    @GET(ApiConstant.GET_FAQS)
    Call<FaqsResponse> getFaqs();

    @GET(ApiConstant.GET_INFO)
    Call<InfoResponse> getInfos();

}
