package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.heinkhantzaw.tn.movie_application.adapter.DiscreteAdapter;
import com.heinkhantzaw.tn.movie_application.adapter.RecyclerAdapter;
import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;
import com.heinkhantzaw.tn.movie_application.model.movie_detail.MovieDetailsModel;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;
import com.hlab.animatedPullToRefresh.AnimatedPullToRefreshLayout;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  MainInterface,AnimatedPullToRefreshLayout.OnRefreshListener {
public LottieAnimationView loading;
public DiscreteScrollView dis;
public DiscreteAdapter discreteAdapter;
public RecyclerAdapter adapter;
public RecyclerView rec;
public TextView txtPlaying,txtPopular;
public AnimatedPullToRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showIntroOnce();
        loading=findViewById(R.id.spin_kit);
        txtPlaying=findViewById(R.id.nowPlaying);
        txtPopular=findViewById(R.id.Popular);
        refreshLayout=findViewById(R.id.pullToRefreshLayout);
        refreshLayout.setColorAnimationArray(new int[]{Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA,Color.CYAN,Color.BLUE,Color.BLACK});
        refreshLayout.setOnRefreshListener(this);
        adapter=new RecyclerAdapter(new ArrayList<ResultsItem>());
        rec=findViewById(R.id.PopRecView);
        rec.setAdapter(adapter);
        rec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter.setListener(new RecyclerAdapter.RecOnClickListener() {
            @Override
            public void onItemClick(ResultsItem item) {
                Intent intent=new Intent(MainActivity.this,MovieDetail.class);
                intent.putExtra("Data",item);
                startActivity(intent);
            }
        });
        showLoadingView();
        contentPopularLoading();
        discreteAdapter=new DiscreteAdapter(new ArrayList<ResultsItem>());
        dis=findViewById(R.id.picker);
        dis.setAdapter(discreteAdapter);
        dis.setOrientation(DSVOrientation.HORIZONTAL);
        dis.setOffscreenItems(3);
        dis.setOverScrollEnabled(true);
        dis.setSlideOnFling(true);
        showLoadingView();
        discreteAdapter.setListener(new DiscreteAdapter.DiscreteOnClickListener() {
            @Override
            public void onItemClick(ResultsItem item) {
                Intent intent=new Intent(MainActivity.this,MovieDetail.class);
                intent.putExtra("Data",item);
                startActivity(intent);
            }
        } );
        contentNowPlaying();
    }
    public void showLoadingView()
    {
        loading.setVisibility(View.VISIBLE);
        rec.setVisibility(View.GONE);
        txtPlaying.setVisibility(View.GONE);
        txtPopular.setVisibility(View.GONE);
    }
     public void showNormalView()
    {
        loading.setVisibility(View.GONE);
        rec.setVisibility(View.VISIBLE);
        txtPlaying.setVisibility(View.VISIBLE) ;
        txtPopular.setVisibility(View.VISIBLE);
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

    public void contentPopularLoading()
    {
        Rest.getRetrofit().create(API_Client.class).getPopularMovie().enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                adapter.setData((ArrayList<ResultsItem>)response.body().getResults());
                showNormalView();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                call.cancel();
            }
        });
    }
    public void contentNowPlaying()
    {
        Rest.getRetrofit().create(API_Client.class).getNowPlayingMovie().enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                discreteAdapter.setData((ArrayList<ResultsItem>)response.body().getResults());
                showNormalView();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onRefresh() {
        contentNowPlaying();
        contentPopularLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.refreshComplete();
            }
        }, 6000);
    }
}
