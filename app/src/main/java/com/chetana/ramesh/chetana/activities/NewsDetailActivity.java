package com.chetana.ramesh.chetana.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetana.ramesh.chetana.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {
    String title;
    String content;
    String image;

    @BindView(R.id.imgNews)
    ImageView imgNews;

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtDesc)
    TextView txtDesc;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        initToolbar();

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        image= getIntent().getStringExtra("image");

        txtDesc.setText(content);
        txtTitle.setText(title);

        if ( image!= null) {
            Picasso.with(this)
                    .load(image)
                    .into(imgNews);
        }


    }

    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("NEWS AND NOTICE");
    }
}
