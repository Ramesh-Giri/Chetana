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
import com.chetana.ramesh.chetana.adapters.InfoAdapter;
import com.chetana.ramesh.chetana.api.API;
import com.chetana.ramesh.chetana.model.Faqs;
import com.chetana.ramesh.chetana.model.FaqsResponse;
import com.chetana.ramesh.chetana.model.Info;
import com.chetana.ramesh.chetana.model.InfoResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralInfoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.rv_general_info)
    RecyclerView rv_general_info;

    ProgressDialog dialog;
    List<Info> infoList;

    InfoAdapter infoAdapter;

    InfoResponse infoResponseList;

    @BindView(R.id.swipeGeneralInfo)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LinearLayoutManager linearLayoutManager;  //layout manager for RecyclerView

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        initToolbar();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading data");
        dialog.setCancelable(false);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        infoAdapter = new InfoAdapter(this);
        infoList = new ArrayList<>();

        displayData();

        linearLayoutManager = new LinearLayoutManager(this);
        rv_general_info.setLayoutManager(linearLayoutManager);
        rv_general_info.setAdapter(infoAdapter);

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
        getSupportActionBar().setTitle("GENERAL INFO");
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
        Call<InfoResponse> callBack = API.getService().getInfos();
        callBack.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {

                if (response.isSuccessful()) {
                    infoResponseList = new InfoResponse();
                    infoResponseList = response.body();
                    AfterGetNews(infoResponseList);

                }
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {

            }
        });

    }

    @UiThread
    void AfterGetNews(InfoResponse infoList) {

        if (infoList == null) return;

        for (int i = 0; i < infoList.getData().size(); i++) {
            Info info = infoList.getData().get(i);
            info.save();

        }
        dialog.dismiss();
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);

        displayData();

    }

    public void displayData() {
        infoList = Info.findWithQuery(Info.class, "select * from info");

        if (infoList.size() > 0) {

            infoAdapter = new InfoAdapter(this);
            infoAdapter.setData(infoList);
            rv_general_info.setAdapter(infoAdapter);

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



