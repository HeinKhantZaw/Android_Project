package com.heinkhantzaw.tn.movie_application.rest;


import com.heinkhantzaw.tn.movie_application.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Client
{
    String apiKey="/v4yVTbbl8dE1UP2dWu5CLyaXOku.jpg";
    @GET("popular?api_key="+apiKey+"&language=en-US&page=1")
    Call<MovieList> getPopularMovie();

    @GET("upcoming?api_key="+apiKey+"&language=en-US&page=1")
    Call <MovieList> getUpcomingMovie();

    @GET("atest?api_key="+apiKey+"&language=en-US")
    Call <MovieList> getLatestMovie();

}
