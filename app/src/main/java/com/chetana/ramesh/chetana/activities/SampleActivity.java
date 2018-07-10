package com.chetana.ramesh.chetana.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.chetana.ramesh.chetana.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SampleActivity extends AppCompatActivity {

    @BindView(R.id.myZoomageView)
    ZoomageView myZoomageView;


    String imageTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);

        imageTitle = getIntent().getStringExtra("imgTitle");

        if(imageTitle.equalsIgnoreCase("info")){

            myZoomageView.setImageDrawable(getResources().getDrawable(R.drawable.img_info_sample));
        }else {
            myZoomageView.setImageDrawable(getResources().getDrawable(R.drawable.img_passport_sample));
        }

    }
}
