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

public class DiscreteAdapter extends RecyclerView.Adapter<DiscreteAdapter.itemHolder>
{
    DiscreteOnClickListener listener;

    public DiscreteAdapter(ArrayList<ResultsItem> resultsItemArrayList) {
        this.resultsItemArrayList = resultsItemArrayList;
    }
    ArrayList<ResultsItem> resultsItemArrayList;
    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_latest,parent,false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, final int position) {
    holder.tvTitle.setText(resultsItemArrayList.get(position).getTitle());
    Glide.with(holder.itemView.getContext())
            .load("https://image.tmdb.org/t/p/w500/"+resultsItemArrayList.get(position).getPosterPath())
            .into(holder.img);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onItemClick(resultsItemArrayList.get(position));
        }
    });
    }

    @Override
    public int getItemCount() {
        return resultsItemArrayList.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView tvTitle;
        public itemHolder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            tvTitle=itemView.findViewById(R.id.titleView);
        }
    }

    public void setListener(DiscreteOnClickListener listener) {
        this.listener = listener;
    }

    public void setData(ArrayList<ResultsItem> data)
    {
        resultsItemArrayList.clear();
        resultsItemArrayList.addAll(data);
        notifyDataSetChanged();
    }
    public interface DiscreteOnClickListener
    {
        void onItemClick(ResultsItem item);
    }
}
