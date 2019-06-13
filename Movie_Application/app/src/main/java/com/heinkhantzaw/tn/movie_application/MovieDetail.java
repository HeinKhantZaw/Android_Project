package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;

public class MovieDetail extends AppCompatActivity {
    TextView tvMovieTitle,tvRating,tvRunTime,tvOverview,tvRelease;
    ImageView backdropImg,posterImg;
    ResultsItem resultsItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        tvMovieTitle=findViewById(R.id.MovieTitle);
        tvRating=findViewById(R.id.RatingNum);
        tvRunTime=findViewById(R.id.runTime);
        tvOverview=findViewById(R.id.overView);
        tvRelease=findViewById(R.id.Release);
        backdropImg=findViewById(R.id.backdrop);
        posterImg=findViewById(R.id.posterView);

        if(getIntent().hasExtra("Data"))
        {
            resultsItem=(ResultsItem) getIntent().getSerializableExtra("Data");
            tvMovieTitle.setText(resultsItem.getOriginalTitle());
            tvRating.setText(String.valueOf(resultsItem.getVoteAverage()));
            tvOverview.setText(resultsItem.getOverview());
            tvRelease.setText(resultsItem.getReleaseDate());
            Glide.with(getBaseContext())
                    .load("https://image.tmdb.org/t/p/w500/"+resultsItem.getBackdropPath())
                    .into(backdropImg);
            Glide.with(getBaseContext())
                    .load("https://image.tmdb.org/t/p/w500/"+resultsItem.getPosterPath())
                    .into(posterImg);
        }
    }
}
