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
import android.os.Bundle;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.heinkhantzaw.tn.movie_application.adapter.DiscreteAdapter;
import com.heinkhantzaw.tn.movie_application.adapter.RecyclerAdapter;
import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
SpinKitView loading;
DiscreteScrollView dis;
DiscreteAdapter discreteAdapter;
RecyclerAdapter adapter;
RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showIntroOnce();
        loading=findViewById(R.id.spin_kit);
        adapter=new RecyclerAdapter(new ArrayList<ResultsItem>());
        rec=findViewById(R.id.PopRecView);
        rec.setAdapter(adapter);
        rec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        showLoadingView();
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
        discreteAdapter=new DiscreteAdapter(new ArrayList<ResultsItem>());
        dis=findViewById(R.id.picker);
        dis.setAdapter(adapter);
        dis.setOrientation(DSVOrientation.HORIZONTAL);
        dis.setOffscreenItems(3);
        dis.setSlideOnFling(true);
        dis.setOverScrollEnabled(true);
        showLoadingView();
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


    private void showLoadingView()
    {
        loading.setVisibility(View.VISIBLE);
        rec.setVisibility(View.GONE);
    }
    private void showNormalView()
    {
        loading.setVisibility(View.GONE);
        rec.setVisibility(View.VISIBLE);
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
}
