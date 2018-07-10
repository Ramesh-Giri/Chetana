package com.chetana.ramesh.chetana.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chetana.ramesh.chetana.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormActivity extends AppCompatActivity {

    @BindView(R.id.info_sample)
    CardView info_sample;

    @BindView(R.id.passport_form)
    CardView passport_form;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);

        info_sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormActivity.this, SampleActivity.class);
                intent.putExtra("imgTitle", "info");
                startActivity(intent);
            }
        });

        passport_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormActivity.this, SampleActivity.class);
                intent.putExtra("imgTitle", "passport");
                startActivity(intent);
            }
        });
        toolbar = findViewById(R.id.toolbar);
        initToolbar();

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
        getSupportActionBar().setTitle("FORM SAMPLES");
    }


}
