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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetana.ramesh.chetana.R;
import com.chetana.ramesh.chetana.model.Faqs;
import com.chetana.ramesh.chetana.model.Info;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoAdapter extends RecyclerSwipeAdapter<InfoAdapter.SimpleStringRecyclerViewAdapter> {

    private Context c;
    private List<Info> infoData;

    private static LayoutInflater inflater;

    public InfoAdapter(Context context) {
        inflater = inflater.from(context);
        this.c = context;


    }

    public void setData(List<Info> data) {
        this.infoData = data;

    }

    @Override
    public InfoAdapter.SimpleStringRecyclerViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        InfoAdapter.SimpleStringRecyclerViewAdapter viewHolder;
        View view = inflater.inflate(R.layout.row_list_info, parent, false);
        viewHolder = new InfoAdapter.SimpleStringRecyclerViewAdapter(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final InfoAdapter.SimpleStringRecyclerViewAdapter vh, int position) {
        final Info data = infoData.get(position);


        vh.txt_question.setText(data.getTitle());
        vh.wv_description.loadData( data.getDescription(), "text/html; charset=UTF-8", null);


        if (data.getImage() != null) {
            Picasso.with(c)
                    .load(data.getImage())
                    .into(vh.post_img);
            vh.post_img.setVisibility(View.VISIBLE);
        }

        vh.tvDate.setText(data.getCreated_at()+"-"+data.getLocation());

        vh.cv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.ll_details.getVisibility() == View.GONE) {
                    vh.imgLike.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_arrow_drop_up));
                    vh.ll_details.setVisibility(View.VISIBLE);
                } else {
                    vh.imgLike.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_arrow_drop_down));
                    vh.ll_details.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (infoData == null) {
            return 0;
        }
        return infoData.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return position;
    }

    public class SimpleStringRecyclerViewAdapter extends RecyclerView.ViewHolder {
        public final View mView;

        @BindView(R.id.cv_info)
        CardView cv_info;

        @BindView(R.id.ll_details)
        LinearLayout ll_details;


        @BindView(R.id.txt_question)
        TextView txt_question;

        @BindView(R.id.tvDate)
        TextView tvDate;


        @BindView(R.id.imgLike)
        ImageView imgLike;

        @BindView(R.id.post_img)
        ImageView post_img;

        @BindView(R.id.wv_description)
        WebView wv_description;


        public SimpleStringRecyclerViewAdapter(View itemView) {
            super(itemView);
            mView = itemView;

            ButterKnife.bind(this, mView);
        }
    }
}
