package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.heinkhantzaw.tn.movie_application.model.VideoModel;
import com.heinkhantzaw.tn.movie_application.model.tv_model.Result_TrendingTV;
import com.heinkhantzaw.tn.movie_application.model.tv_model.model;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TV_details extends AppCompatActivity {
    TextView tvTitle,tvRating,tvOverview,tvFirstDate,tvWebsite;
    ImageView backdropImg,posterImg;
    LottieAnimationView playButton,playLoading;
    YouTubePlayerView playerView;
    Result_TrendingTV modelItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_details);

        tvTitle=findViewById(R.id.TV_detail_title);
        tvRating=findViewById(R.id.TV_rating_num);
        tvOverview=findViewById(R.id.TV_detail_overView);
        tvFirstDate=findViewById(R.id.TV_detail_firstAir);
        backdropImg=findViewById(R.id.TV_detail_backdrop);
        posterImg=findViewById(R.id.TV_detail_posterView);
        playButton=findViewById(R.id.TV_detail_playButton);
        playLoading=findViewById(R.id.TV_detail_playLoading);
        tvWebsite=findViewById(R.id.url);

        if(getIntent().hasExtra("TV_Data"))
        {
            loadDetails();
            playLoading.setVisibility(View.GONE);
            playButton.setVisibility(View.VISIBLE);
            loadVideo();
        }
    }

    private void loadDetails()
    {
        modelItem= (Result_TrendingTV) getIntent().getSerializableExtra("TV_Data");
        tvTitle.setText(modelItem.getOriginalName());
        tvRating.setText(String.valueOf(modelItem.getVoteAverage()));
        tvOverview.setText(modelItem.getOverview());
        tvFirstDate.setText(modelItem.getFirstAirDate());
        Glide.with(getBaseContext())
                .load("https://image.tmdb.org/t/p/w500/"+modelItem.getBackdropPath())
                .into(backdropImg);
        Glide.with(getBaseContext())
                .load("https://image.tmdb.org/t/p/w500/"+modelItem.getPosterPath())
                .into(posterImg);
        TV_Detail_loading();
    }

    private void loadVideo()
    {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButton.setVisibility(View.GONE);
                playLoading.setVisibility(View.VISIBLE);
                Rest.getRetrofit().create(API_Client.class).getVideo("tv/"+modelItem.getId()+"/videos?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US").enqueue(new Callback<VideoModel>() {
                    @Override
                    public void onResponse(Call<VideoModel> call, final Response<VideoModel> response) {

                        final AlertDialog dialog=new AlertDialog.Builder(TV_details.this).setTitle("Trailers")
                                .setView(R.layout.video_dialog).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        playButton.setVisibility(View.VISIBLE);
                                        playLoading.setVisibility(View.GONE);
                                    }
                                }).create();
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                playButton.setVisibility(View.VISIBLE);
                                playLoading.setVisibility(View.GONE);
                                if (playerView != null) {
                                    playerView.release();
                                }
                            }
                        });
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
    private void TV_Detail_loading()
    {
        Rest.getRetrofit().create(API_Client.class).getTV_details("tv/"+modelItem.getId()+"?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US")
                .enqueue(new Callback<model>() {
                    @Override
                    public void onResponse(Call<model> call, final Response<model> response) {
                        tvWebsite.setText(response.body().getHomepage());
                        tvWebsite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(response.body().getHomepage()));
                                startActivity(viewIntent);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<model> call, Throwable t) {

                    }
                });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (playerView != null) {
            playerView.release();
        }
    }

}
