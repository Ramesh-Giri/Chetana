package com.chetana.ramesh.chetana.activities;

import android.app.ProgressDialog;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chetana.ramesh.chetana.R;
import com.chetana.ramesh.chetana.Utils.NetworkStatus;
import com.chetana.ramesh.chetana.adapters.FaqAdapter;
import com.chetana.ramesh.chetana.adapters.NewsAdapter;
import com.chetana.ramesh.chetana.api.API;
import com.chetana.ramesh.chetana.model.Faqs;
import com.chetana.ramesh.chetana.model.FaqsResponse;
import com.chetana.ramesh.chetana.model.News;
import com.chetana.ramesh.chetana.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_faqs)
    RecyclerView rv_faqs;

    ProgressDialog dialog;
    List<Faqs> faqList;

    FaqAdapter faqAdapter;

    FaqsResponse faqResponseList;

    @BindView(R.id.swipeFaqs)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LinearLayoutManager linearLayoutManager;  //layout manager for RecyclerView

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        initToolbar();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading data");
        dialog.setCancelable(false);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        faqAdapter = new FaqAdapter(this);
        faqList = new ArrayList<>();

        displayData();

        linearLayoutManager = new LinearLayoutManager(this);
        rv_faqs.setLayoutManager(linearLayoutManager);
        rv_faqs.setAdapter(faqAdapter);
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
        getSupportActionBar().setTitle("FAQ's");
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
        Call<FaqsResponse> callBack = API.getService().getFaqs();
        callBack.enqueue(new Callback<FaqsResponse>() {
            @Override
            public void onResponse(Call<FaqsResponse> call, Response<FaqsResponse> response) {

                if (response.isSuccessful()) {
                    faqResponseList = new FaqsResponse();
                    faqResponseList = response.body();
                    AfterGetNews(faqResponseList);

                }
            }

            @Override
            public void onFailure(Call<FaqsResponse> call, Throwable t) {

            }
        });

    }

    @UiThread
    void AfterGetNews(FaqsResponse newsList) {

        if (newsList == null) return;

        for (int i = 0; i < newsList.getData().size(); i++) {
            Faqs faqs = newsList.getData().get(i);
            faqs.save();

        }
        dialog.dismiss();
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);

        displayData();

    }

    public void displayData() {
        faqList = Faqs.findWithQuery(Faqs.class, "select * from faqs");

        if (faqList.size() > 0) {

            faqAdapter = new FaqAdapter(this);
            faqAdapter.setData(faqList);
            rv_faqs.setAdapter(faqAdapter);

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


