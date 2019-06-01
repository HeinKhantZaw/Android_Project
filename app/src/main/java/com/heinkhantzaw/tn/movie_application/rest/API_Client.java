package com.heinkhantzaw.tn.movie_application.rest;


import com.heinkhantzaw.tn.movie_application.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Client
{
    @GET("popular?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US&page=1")
    Call<MovieList> getPopularMovie();

    @GET("upcoming?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US&page=1")
    Call <MovieList> getUpcomingMovie();

    @GET("atest?api_key=b64b30ff8a183dbfd580ecfb0021d7cd&language=en-US")
    Call <MovieList> getLatestMovie();
}
