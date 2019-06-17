package com.heinkhantzaw.tn.movie_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.heinkhantzaw.tn.movie_application.adapter.DiscreteAdapterMovie;
import com.heinkhantzaw.tn.movie_application.adapter.RecyclerAdapter;
import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.ResultsItem;
import com.heinkhantzaw.tn.movie_application.rest.API_Client;
import com.heinkhantzaw.tn.movie_application.rest.Rest;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class Search extends AppCompatActivity {

LottieAnimationView searchLoading;
SearchView searchView;
RecyclerView resultView;
RecyclerAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_layout);

        resultView=findViewById(R.id.results);
        searchView=findViewById(R.id.searchView);
        searchLoading=findViewById(R.id.searchLoading);
        resultView.setLayoutManager(new LinearLayoutManager(Search.this,RecyclerView.VERTICAL,false));
        hideLoadingAnimation();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchingProcess();
                    resultAdapter.setListener(new RecyclerAdapter.RecOnClickListener() {
                        @Override
                        public void onItemClick(ResultsItem item) {
                            Intent intent=new Intent(Search.this,MovieDetail.class);
                            intent.putExtra("Data",item);
                            startActivity(intent);
                        }
                    });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
    private void showLoadingAnimation()
    {
        searchLoading.setVisibility(View.VISIBLE);
    }
    private void hideLoadingAnimation()
    {
        searchLoading.setVisibility(GONE);
    }
    private void  searchingProcess()
    {
        showLoadingAnimation();
        resultAdapter=new RecyclerAdapter(new ArrayList<ResultsItem>());
        resultView.setAdapter(resultAdapter);
        Rest.getRetrofit()
                .create(API_Client.class)
                .getSearchResults("search/movie?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US&query="+searchView.getQuery().toString())
                .enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                resultAdapter.setData((ArrayList<ResultsItem>)response.body().getResults());
                hideLoadingAnimation();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(Search.this, "No Match found!",Toast.LENGTH_LONG).show();
            hideLoadingAnimation();
            }
        });

    }
}
