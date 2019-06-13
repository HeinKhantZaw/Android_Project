package com.heinkhantzaw.tn.movie_application.rest;

import com.heinkhantzaw.tn.movie_application.MainActivity;
import com.heinkhantzaw.tn.movie_application.model.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface API_Client
{

    String apiKey="b64b30ff8a183dbfd580ecfb0021d7cd";
    @GET("popular?api_key="+apiKey+"&language=en-US&page=1")
    Call<MovieList> getPopularMovie();

    @GET("upcoming?api_key="+apiKey+"&language=en-US&page=1")
    Call <MovieList> getUpcomingMovie();

    @GET("now_playing?api_key="+apiKey+"&language=en-US")
    Call <MovieList> getNowPlayingMovie();

    @GET("search/movie?"+apiKey+"&language=en-US&query=")
    Call <MovieList> searchMovie();

    @GET
    Call<MovieList> getSimilarMovie(@Url String url);
}
