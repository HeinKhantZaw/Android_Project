package com.heinkhantzaw.tn.movie_application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.heinkhantzaw.tn.movie_application.R;
import com.heinkhantzaw.tn.movie_application.model.tv_model.Result_TrendingTV;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class RecTV_Adapter extends RecyclerView.Adapter<RecTV_Adapter.TV_ViewHolder>
{
    ArrayList<Result_TrendingTV> list;
    RecOnClickListener listener;

    public void setListener(RecOnClickListener listener) {
        this.listener = listener;
    }

    public RecTV_Adapter(ArrayList<Result_TrendingTV> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TV_ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trending_tv,parent,false);
        return new TV_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TV_ViewHolder holder, final int position) {
        holder.txtTitle.setText(list.get(position).getOriginalName());
        holder.tvRating.setText(String.valueOf(list.get(position).getVoteAverage()));
        holder.tvRelease.setText(list.get(position).getFirstAirDate());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+list.get(position).getPosterPath()).into(holder.imgPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TV_ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,tvRating,tvRelease;
        RoundedImageView imgPoster;
        public TV_ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.TV_title);
            tvRating=itemView.findViewById(R.id.TV_rating);
            tvRelease=itemView.findViewById(R.id.TV_startDate);
            imgPoster=itemView.findViewById(R.id.TV_Poster );
        }
    }
    public void setData(List<Result_TrendingTV> data)
    {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    public  interface RecOnClickListener
    {
        void onItemClick(Result_TrendingTV item);
    }
}
