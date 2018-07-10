package com.chetana.ramesh.chetana.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetana.ramesh.chetana.activities.NewsDetailActivity;
import com.chetana.ramesh.chetana.R;
import com.chetana.ramesh.chetana.model.News;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerSwipeAdapter<NewsAdapter.SimpleStringRecyclerViewAdapter> {

    private Context c;
    private List<News> newsData;

    private static LayoutInflater inflater;

    public NewsAdapter(Context context) {
        inflater = inflater.from(context);
        this.c = context;


    }

    public void setData(List<News> data) {
        this.newsData = data;

    }


    @Override
    public NewsAdapter.SimpleStringRecyclerViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsAdapter.SimpleStringRecyclerViewAdapter viewHolder;
        View view = inflater.inflate(R.layout.row_list_news, parent, false);
        viewHolder = new NewsAdapter.SimpleStringRecyclerViewAdapter(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.SimpleStringRecyclerViewAdapter vh, int position) {
        final News data = newsData.get(position);


        vh.title.setText(data.getTitle());


            if (data.getImage() != null) {
                Picasso.with(c)
                        .load(data.getImage())
                        .into(vh.news_image);
            }
        vh.card_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent detailIntent = new Intent(c, NewsDetailActivity.class);
                detailIntent.putExtra("title", data.getTitle());
                detailIntent.putExtra("content", data.getDescription());
                detailIntent.putExtra("image", data.getImage());
                c.startActivity(detailIntent);


            }
        });
    }


    @Override
    public int getItemCount() {
        if (newsData == null) {
            return 0;
        }
        return newsData.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return position;
    }

    public class SimpleStringRecyclerViewAdapter extends RecyclerView.ViewHolder {
        public final View mView;

        @BindView(R.id.card_list)
        CardView card_list;

        @BindView(R.id.title)
        TextView title;


        @BindView(R.id.news_image)
        ImageView news_image;


        public SimpleStringRecyclerViewAdapter(View itemView) {
            super(itemView);
            mView = itemView;

            ButterKnife.bind(this, mView);
        }
    }

}
