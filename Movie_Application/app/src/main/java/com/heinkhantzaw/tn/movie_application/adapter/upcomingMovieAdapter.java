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
import java.util.List;

public class upcomingMovieAdapter extends RecyclerView.Adapter<upcomingMovieAdapter.UpcomingHolder>
{
    ArrayList<ResultsItem> list;
    RecOnClickListener listener;

    public void setListener(RecOnClickListener listener) {
        this.listener = listener;
    }

    public upcomingMovieAdapter(ArrayList<ResultsItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UpcomingHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_movie_layout,parent,false);
        return new UpcomingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingHolder holder, final int position) {
            holder.txtTitle.setText(list.get(position).getOriginalTitle());
            holder.tvRating.setText(list.get(position).getReleaseDate());
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + list.get(position).getPosterPath()).into(holder.imgPoster);
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

    public class UpcomingHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,tvRating;
        ImageView imgPoster;
        public UpcomingHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.textView6);
            tvRating=itemView.findViewById(R.id.textView7);
            imgPoster=itemView.findViewById(R.id.ImgUpcoming);
        }
    }
    public void setData(List<ResultsItem> data)
    {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    public  interface RecOnClickListener
    {
        void onItemClick(ResultsItem item);
    }
}
