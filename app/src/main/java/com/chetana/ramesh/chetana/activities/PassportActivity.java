package com.chetana.ramesh.chetana.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chetana.ramesh.chetana.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PassportActivity extends AppCompatActivity {


    @BindView(R.id.news_notice)
    ImageView news_notice;

    @BindView(R.id.iv_faq)
    ImageView iv_faq;

    @BindView(R.id.iv_general_info)
    ImageView iv_general_info;

    @BindView(R.id.iv_forms)
    ImageView iv_forms;

    @BindView(R.id.iv_online_form)
    ImageView iv_online_form;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        initToolbar();

        news_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassportActivity.this, NewsActivity.class));

            }
        });

        iv_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassportActivity.this, FaqActivity.class));

            }
        });


        iv_general_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassportActivity.this, GeneralInfoActivity.class));

            }
        });

        iv_forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassportActivity.this, FormActivity.class));

            }
        });

        iv_online_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassportActivity.this, ApplyOnlineActivity.class));

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
        getSupportActionBar().setTitle("PASSPORT");
    }


}


