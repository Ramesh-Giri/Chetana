package com.chetana.ramesh.chetana.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class API {
    private static API ourInstance;
    private static Retrofit retrofit;

    private ApiService apiService;

    public API() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.readTimeout(300, TimeUnit.SECONDS);
        builder.connectTimeout(300, TimeUnit.SECONDS);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request;
                request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Cache-Control", "no-cache")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        }).addInterceptor(loggingInterceptor);

        OkHttpClient client = builder.build();
        Retrofit.Builder adapterBuilder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .baseUrl(ApiConstant.API_BASE_URL);
        retrofit = adapterBuilder.build();
        this.apiService = retrofit.create(ApiService.class);
    }

    public static Retrofit retrofit() {
        if (retrofit == null) {
            ourInstance = new API();
            return retrofit;
        }
        return retrofit;

    }

    public static ApiService getService() {
        ourInstance = new API();
        return ourInstance.apiService;
    }
}
