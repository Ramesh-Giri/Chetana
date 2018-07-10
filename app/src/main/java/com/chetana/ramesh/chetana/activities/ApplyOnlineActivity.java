package com.chetana.ramesh.chetana.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chetana.ramesh.chetana.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyOnlineActivity extends AppCompatActivity {

    @BindView(R.id.wv_onlineForm)
    WebView wv_onlineForm;


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_online);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        initToolbar();

        wv_onlineForm.loadUrl("https://online.nepalpassport.gov.np/PreEnrollment/home.html");
        wv_onlineForm.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }
        });
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
        getSupportActionBar().setTitle("APPLY ONLINE");
    }
}

