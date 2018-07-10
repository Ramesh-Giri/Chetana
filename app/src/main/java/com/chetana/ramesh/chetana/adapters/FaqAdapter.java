package com.chetana.ramesh.chetana.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetana.ramesh.chetana.R;
import com.chetana.ramesh.chetana.model.Faqs;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqAdapter extends RecyclerSwipeAdapter<FaqAdapter.SimpleStringRecyclerViewAdapter> {

    private Context c;
    private List<Faqs> faqData;

    private static LayoutInflater inflater;

    public FaqAdapter(Context context) {
        inflater = inflater.from(context);
        this.c = context;


    }

    public void setData(List<Faqs> data) {
        this.faqData = data;

    }


    @Override
    public SimpleStringRecyclerViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        FaqAdapter.SimpleStringRecyclerViewAdapter viewHolder;
        View view = inflater.inflate(R.layout.row_list_faqs, parent, false);
        viewHolder = new SimpleStringRecyclerViewAdapter(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SimpleStringRecyclerViewAdapter vh, int position) {
        final Faqs data = faqData.get(position);


        vh.txt_question.setText(data.getQuestion());
        WebSettings webSettings = vh.postDetail.getSettings();
        vh.postDetail.loadData( data.getAnswer(), "text/html; charset=UTF-8", null);


        vh.cv_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vh.postDetail.getVisibility() == View.GONE) {
                    vh.imgLike.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_arrow_drop_up));
                    vh.postDetail.setVisibility(View.VISIBLE);
                }else {
                    vh.imgLike.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_arrow_drop_down));
                    vh.postDetail.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (faqData == null) {
            return 0;
        }
        return faqData.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return position;
    }

    public class SimpleStringRecyclerViewAdapter extends RecyclerView.ViewHolder {
        public final View mView;

        @BindView(R.id.cv_faq)
        CardView cv_faq;


        @BindView(R.id.txt_question)
        TextView txt_question;


        @BindView(R.id.imgLike)
        ImageView imgLike;

        @BindView(R.id.postDetail)
        WebView postDetail;


        public SimpleStringRecyclerViewAdapter(View itemView) {
            super(itemView);
            mView = itemView;

            ButterKnife.bind(this, mView);
        }
    }
}
