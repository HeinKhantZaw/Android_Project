package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.heinkhantzaw.tn.movie_application.adapter.DiscreteAdapterMovie;
import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;
import com.heinkhantzaw.tn.movie_application.model.VideoModel;
import com.heinkhantzaw.tn.movie_application.model.VideoResults;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity {
    TextView tvMovieTitle,tvRating,tvOverview,tvRelease;
    ImageView backdropImg,posterImg;
    ResultsItem resultsItem;
    DiscreteScrollView scrollView;
    DiscreteAdapterMovie adapterMovie;
    TextView txtSimilarMovies;
    LottieAnimationView playButton,playLoading;
    YouTubePlayerView playerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvMovieTitle=findViewById(R.id.MovieTitle);
        tvRating=findViewById(R.id.RatingNum);
        tvOverview=findViewById(R.id.overView);
        tvRelease=findViewById(R.id.Release);
        backdropImg=findViewById(R.id.backdrop);
        posterImg=findViewById(R.id.posterView);
        scrollView=findViewById(R.id.similar);
        txtSimilarMovies=findViewById(R.id.txtSimilar);
        playButton=findViewById(R.id.PlayButton);
        playLoading=findViewById(R.id.PlayLoading);
        playLoading.setVisibility(View.GONE);

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
            loadVideo();
        }


        if(getIntent().hasExtra("MovieData"))
        {
            resultsItem=(ResultsItem)getIntent().getSerializableExtra("MovieData");
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
            txtSimilarMovies.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        playerView.release();
    }
    public void loadSimilarMovies()
    {
        scrollView.setAdapter(adapterMovie);
        scrollView.setOrientation(DSVOrientation.HORIZONTAL);
        scrollView.setOffscreenItems(3);
        scrollView.setOverScrollEnabled(true);
        Rest.getRetrofit().create(API_Client.class).getSimilarMovie("movie/"+resultsItem.getId()+"/similar?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US&page=1").enqueue(new Callback<MovieList>() {
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
    public void loadVideo()
    {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButton.setVisibility(View.GONE);
                playLoading.setVisibility(View.VISIBLE);
                Rest.getRetrofit().create(API_Client.class).getVideo("movie/"+resultsItem.getId()+"/videos?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US").enqueue(new Callback<VideoModel>() {
                    @Override
                    public void onResponse(Call<VideoModel> call, final Response<VideoModel> response) {


                        final AlertDialog dialog=new AlertDialog.Builder(MovieDetail.this).setTitle("Trailers")
                                .setView(R.layout.video_dialog).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        playButton.setVisibility(View.VISIBLE);
                                    }
                                }).create();
                       dialog.show();
                        playerView=dialog.findViewById(R.id.youtube_player_view);
                        playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError error) {
                                super.onError(youTubePlayer, error);
                            }

                            @Override
                            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                                super.onReady(youTubePlayer);
                                playLoading.setVisibility(View.GONE);
                                youTubePlayer.loadVideo(response.body().getResults().get(0).getKey(),0);
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<VideoModel> call, Throwable t) {

                    }
                });
            }
        });

    }
}
