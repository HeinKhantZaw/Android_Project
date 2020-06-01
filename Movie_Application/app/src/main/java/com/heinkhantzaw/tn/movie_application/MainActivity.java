package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bosphere.fadingedgelayout.FadingEdgeLayout;
import com.heinkhantzaw.tn.movie_application.adapter.DiscreteAdapter;
import com.heinkhantzaw.tn.movie_application.adapter.RecTV_Adapter;
import com.heinkhantzaw.tn.movie_application.adapter.RecyclerAdapter;
import com.heinkhantzaw.tn.movie_application.adapter.upcomingMovieAdapter;
import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;
import com.heinkhantzaw.tn.movie_application.model.tv_model.Result_TrendingTV;
import com.heinkhantzaw.tn.movie_application.model.tv_model.TrendingTV;
import com.heinkhantzaw.tn.movie_application.model.tv_model.model;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainInterface {
    public LottieAnimationView loading;
    public DiscreteScrollView dis, rec, recTrending, upcoming;
    public DiscreteAdapter discreteAdapter;
    public RecyclerAdapter adapter;
    public upcomingMovieAdapter upcomingMovieAdapter;
    public RecTV_Adapter recTV_adapter;
    public TextView txtPlaying, txtPopular, txtTV, txtRating, title1, title2, txtupcoming;
    public SwipeRefreshLayout refreshLayout;
    public ImageView btnSearch, logo;
    FadingEdgeLayout fadingEdgeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showIntroOnce();

        loading = findViewById(R.id.spin_kit);
        txtPlaying = findViewById(R.id.nowPlaying);
        txtPopular = findViewById(R.id.Popular);
        refreshLayout = findViewById(R.id.pullToRefreshLayout);
        recTrending = findViewById(R.id.Trending);
        txtTV = findViewById(R.id.txtTVShow);
        rec = findViewById(R.id.PopRecView);
        dis = findViewById(R.id.similarDiscrete);
        txtRating = findViewById(R.id.ratingMovie);
        btnSearch = findViewById(R.id.search);
        logo = findViewById(R.id.logo);
        title1 = findViewById(R.id.textView4);
        title2 = findViewById(R.id.textView5);
        upcoming = findViewById(R.id.upcomingView);
        txtupcoming = findViewById(R.id.Upcoming_Movies);
        fadingEdgeLayout = findViewById(R.id.fade);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, about.class);
                startActivity(intent);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });


        //search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });

        //Adapter initialization
        adapter = new RecyclerAdapter(new ArrayList<ResultsItem>());
        discreteAdapter = new DiscreteAdapter(new ArrayList<ResultsItem>());
        recTV_adapter = new RecTV_Adapter(new ArrayList<Result_TrendingTV>());
        upcomingMovieAdapter = new upcomingMovieAdapter(new ArrayList<ResultsItem>());


        //setting Adapter
        rec.setAdapter(adapter);
        recTrending.setAdapter(recTV_adapter);
        dis.setAdapter(discreteAdapter);
        upcoming.setAdapter(upcomingMovieAdapter);

        //scroll view animations
        dis.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());
        recTrending.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.9f).build());
        upcoming.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.9f).build());

        recTV_adapter.setListener(new RecTV_Adapter.RecOnClickListener() {
            @Override
            public void onItemClick(Result_TrendingTV item) {
                Intent intent = new Intent(MainActivity.this, TV_details.class);
                intent.putExtra("TV_Data", item);
                startActivity(intent);
            }
        });
        adapter.setListener(new RecyclerAdapter.RecOnClickListener() {
            @Override
            public void onItemClick(ResultsItem item) {
                Intent intent = new Intent(MainActivity.this, MovieDetail.class);
                intent.putExtra("Data", item);
                startActivity(intent);
            }
        });
        discreteAdapter.setListener(new DiscreteAdapter.DiscreteOnClickListener() {
            @Override
            public void onItemClick(ResultsItem item) {
                Intent intent = new Intent(MainActivity.this, MovieDetail.class);
                intent.putExtra("Data", item);
                startActivity(intent);
            }
        });

        upcomingMovieAdapter.setListener(new upcomingMovieAdapter.RecOnClickListener() {
            @Override
            public void onItemClick(ResultsItem item) {
                Intent intent = new Intent(MainActivity.this, MovieDetail.class);
                intent.putExtra("Data", item);
                startActivity(intent);

            }
        });


        dis.setOrientation(DSVOrientation.HORIZONTAL);
        dis.setOffscreenItems(2);
        dis.setOverScrollEnabled(true);
        dis.setSlideOnFling(true);
        dis.scrollToPosition(1);

        rec.setOffscreenItems(3);
        rec.setSlideOnFling(true);
        rec.setOverScrollEnabled(true);
        rec.setSlideOnFlingThreshold(4000);

        recTrending.setOrientation(DSVOrientation.HORIZONTAL);
        recTrending.scrollToPosition(1);
        recTrending.setOffscreenItems(3);
        recTrending.setOverScrollEnabled(true);
        recTrending.setSlideOnFling(true);

        upcoming.setOrientation(DSVOrientation.HORIZONTAL);
        upcoming.setOffscreenItems(3);
        upcoming.setOverScrollEnabled(true);
        upcoming.setSlideOnFling(true);
        upcoming.scrollToPosition(1);

        showLoadingView();
        contentPopularLoading();
        contentNowPlaying();
        contentTrending();
        contentUpcoming();
    }

    public void showLoadingView() {
        loading.setVisibility(View.VISIBLE);

        rec.setVisibility(View.GONE);
        btnSearch.setVisibility(View.GONE);
        dis.setVisibility(View.GONE);
        recTrending.setVisibility(View.GONE);
        txtPlaying.setVisibility(View.GONE);
        txtPopular.setVisibility(View.GONE);
        txtTV.setVisibility(View.GONE);
        logo.setVisibility(View.GONE);
        title2.setVisibility(View.GONE);
        title1.setVisibility(View.GONE);
        txtupcoming.setVisibility(View.GONE);

    }

    public void showNormalView() {
        loading.setVisibility(View.GONE);

        dis.setVisibility(View.VISIBLE);
        rec.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.VISIBLE);
        recTrending.setVisibility(View.VISIBLE);
        txtPlaying.setVisibility(View.VISIBLE);
        txtPopular.setVisibility(View.VISIBLE);
        txtTV.setVisibility(View.VISIBLE);
        logo.setVisibility(View.VISIBLE);
        title2.setVisibility(View.VISIBLE);
        title1.setVisibility(View.VISIBLE);
        txtupcoming.setVisibility(View.VISIBLE);
    }

    public void showIntroOnce() {
        SharedPreferences sp = getSharedPreferences("Main", Context.MODE_PRIVATE);
        if (!sp.getBoolean("first", false)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("first", true);
            editor.apply();
            Intent intent = new Intent(this, IntroActivity.class); // Call the AppIntro java class
            startActivity(intent);
        }
    }


    // RETROFIT

    public void contentPopularLoading() {
        Rest.getRetrofit().create(API_Client.class).getPopularMovie().enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                adapter.setData((ArrayList<ResultsItem>) response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void contentNowPlaying() {
        Rest.getRetrofit().create(API_Client.class).getNowPlayingMovie().enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                discreteAdapter.setData((ArrayList<ResultsItem>) response.body().getResults());

            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void contentTrending() {
        Rest.getRetrofit().create(API_Client.class).getTrendingTV().enqueue(new Callback<TrendingTV>() {
            @Override
            public void onResponse(Call<TrendingTV> call, Response<TrendingTV> response) {
                recTV_adapter.setData(response.body().getResults());

            }

            @Override
            public void onFailure(Call<TrendingTV> call, Throwable t) {

            }
        });
    }

    public void contentUpcoming() {
        Rest.getRetrofit().create(API_Client.class).getUpcomingMovie().enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                upcomingMovieAdapter.setData(response.body().getResults());
                showNormalView();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
    }

    private void refreshItems() {
        contentNowPlaying();
        contentPopularLoading();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onItemsLoadComplete();
            }
        }, 3000);
    }

    private void onItemsLoadComplete() {
        refreshLayout.setRefreshing(false);
    }

}
