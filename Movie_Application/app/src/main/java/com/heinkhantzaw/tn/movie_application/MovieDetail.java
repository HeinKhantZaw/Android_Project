package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heinkhantzaw.tn.movie_application.adapter.DiscreteAdapter;
import com.heinkhantzaw.tn.movie_application.adapter.DiscreteAdapterMovie;
import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MovieDetail extends AppCompatActivity {
    TextView tvMovieTitle,tvRating,tvRunTime,tvOverview,tvRelease;
    ImageView backdropImg,posterImg;
    ResultsItem resultsItem;
    DiscreteScrollView scrollView;
    DiscreteAdapterMovie adapterMovie;

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
        scrollView=findViewById(R.id.similar);
        adapterMovie=new DiscreteAdapterMovie(new ArrayList<ResultsItem>());


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
            loadSimilarMovies();
        }
    }
    public void loadSimilarMovies()
    {
        scrollView.setAdapter(adapterMovie);
        scrollView.setOrientation(DSVOrientation.HORIZONTAL);
        scrollView.setOffscreenItems(3);
        scrollView.setOverScrollEnabled(true);
        Rest.getRetrofit().create(API_Client.class).getSimilarMovie(resultsItem.getId()+"/similar?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US&page=1").enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                adapterMovie.setData((ArrayList<ResultsItem>)response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
        adapterMovie.setListener(new DiscreteAdapterMovie.movieOnClickListener() {
            @Override
            public void onItemClick(ResultsItem item) {
                Intent intent=new Intent(MovieDetail.this,MovieDetail.class);
                intent.putExtra("Data",item);
                startActivity(intent);
            }
        });
    }
}
