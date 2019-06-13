package com.heinkhantzaw.tn.movie_application.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.heinkhantzaw.tn.movie_application.R;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;

import java.util.ArrayList;

public class DiscreteAdapterMovie extends RecyclerView.Adapter<DiscreteAdapterMovie.dataHolder>
 {
     movieOnClickListener listener;

     public void setListener(movieOnClickListener listener) {
         this.listener = listener;
     }

     ArrayList<ResultsItem> list;

     public DiscreteAdapterMovie(ArrayList<ResultsItem>list) {
         this.list = list;
     }

     @NonNull
     @Override
     public dataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similar_movies,parent,false);
         return new dataHolder(view);
     }

     @Override
     public void onBindViewHolder(@NonNull dataHolder holder, final int position) {
        holder.Titletxt.setText(list.get(position).getTitle());
        holder.yearTxt.setText(list.get(position).getReleaseDate().subSequence(0,4));
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+list.get(position).getPosterPath()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(list.get(position));
            }
        });
     }

     @Override
     public int getItemCount() {
         return list.size();
     }

     public class dataHolder extends RecyclerView.ViewHolder {
     ImageView imageView;
     TextView Titletxt,yearTxt;

     public dataHolder(@NonNull View itemView) {
         super(itemView);
        imageView=itemView.findViewById(R.id.similarMovieImage);
        Titletxt=itemView.findViewById(R.id.similarMovieTitle);
        yearTxt=itemView.findViewById(R.id.year);
     }
 }
     public void setData(ArrayList<ResultsItem> data)
     {
         list.clear();
         list.addAll(data);
         notifyDataSetChanged();
     }
     public interface movieOnClickListener
     {
         void onItemClick(ResultsItem item);
     }
 }