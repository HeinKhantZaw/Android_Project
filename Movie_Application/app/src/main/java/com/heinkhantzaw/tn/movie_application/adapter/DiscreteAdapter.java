package com.heinkhantzaw.tn.movie_application.adapter;

import android.content.Context;
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

public class DiscreteAdapter extends RecyclerView.Adapter<DiscreteAdapter.itemHolder>
{
    public DiscreteAdapter(ArrayList<ResultsItem> resultsItemArrayList,Context context) {
        this.resultsItemArrayList = resultsItemArrayList;
        this.context=context;
    }
    Context context;
    ArrayList<ResultsItem> resultsItemArrayList;
    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_latest,parent,false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {
    holder.tvTitle.setText(resultsItemArrayList.get(position).getTitle());
    Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+resultsItemArrayList.get(position).getPosterPath()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return resultsItemArrayList.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tvTitle;
        public itemHolder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            tvTitle=itemView.findViewById(R.id.titleView);
        }
    }
}