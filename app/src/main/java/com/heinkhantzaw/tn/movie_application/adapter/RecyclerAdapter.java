package com.heinkhantzaw.tn.movie_application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heinkhantzaw.tn.movie_application.R;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder>
{
    ArrayList<ResultsItem> list;
    RecOnClickListener listener;

    public void setListener(RecOnClickListener listener) {
        this.listener = listener;
    }

    public RecyclerAdapter(ArrayList<ResultsItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_data_popular,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
    holder.tvTitle.setText(list.get(position).getOriginalTitle());
    holder.tvRating.setText(String.valueOf(list.get(position).getVoteAverage()));
    holder.tvRelease.setText(list.get(position).getReleaseDate());
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

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvRating,tvRelease;
        ImageView imgPoster;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.titleText);
            tvRating=itemView.findViewById(R.id.rating);
            tvRelease=itemView.findViewById(R.id.date);
            imgPoster=itemView.findViewById(R.id.Popularposter );
        }
    }
    public void setData(ArrayList<ResultsItem> data)
    {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    public  interface RecOnClickListener
    {
        public void onItemClick(ResultsItem item);
    }
}
