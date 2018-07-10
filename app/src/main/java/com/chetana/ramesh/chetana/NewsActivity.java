package com.chetana.ramesh.chetana;

import android.app.ProgressDialog;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.chetana.ramesh.chetana.Utils.NetworkStatus;
import com.chetana.ramesh.chetana.api.API;
import com.chetana.ramesh.chetana.model.News;
import com.chetana.ramesh.chetana.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_news_notice)
    RecyclerView rv_news_notice;

    ProgressDialog dialog;
    List<News> newsList;
    NewsAdapter newsAdapter;

    NewsResponse newsResponseList;

    @BindView(R.id.swipeNews)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;  //layout manager for RecyclerView

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        initToolbar();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading data");
        dialog.setCancelable(false);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        newsAdapter = new NewsAdapter(this);
        newsList = new ArrayList<>();

        displayData();

        linearLayoutManager = new LinearLayoutManager(this);
        rv_news_notice.setLayoutManager(linearLayoutManager);
        rv_news_notice.setAdapter(newsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("NEWS AND NOTICE");
    }

    @Override
    public void onRefresh() {

        if (NetworkStatus.isConnected(this)) {
            loadData();
        } else {
            if (mSwipeRefreshLayout.isRefreshing())
                mSwipeRefreshLayout.setRefreshing(false);

            NetworkStatus.showDialog(this);
        }
    }

    private void loadData() {
        dialog.show();
        Call<NewsResponse> callBack = API.getService().getNews();
        callBack.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                if (response.isSuccessful()) {
                    newsResponseList = new NewsResponse();
                    newsResponseList = response.body();
                    AfterGetNews(newsResponseList);

                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });

    }

    @UiThread
    void AfterGetNews(NewsResponse newsList) {

        if (newsList == null) return;

        for (int i = 0; i < newsList.getData().size(); i++) {
            News news = newsList.getData().get(i);
            news.save();

        }
        dialog.dismiss();
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);

        displayData();

    }

    public void displayData() {
        newsList = News.findWithQuery(News.class, "select * from news");

        if (newsList.size() > 0) {

            newsAdapter = new NewsAdapter(this);
            newsAdapter.setData(newsList);
            rv_news_notice.setAdapter(newsAdapter);

        } else {
            if (NetworkStatus.isConnected(this))
                loadData();
            else
                NetworkStatus.showDialog(this);
        }

        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
    }
}
