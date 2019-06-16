package com.heinkhantzaw.tn.movie_application.rest;

import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.VideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface API_Client
{

    String apiKey="b64b30ff8a183dbfd580ecfb0021d7cd";
    @GET("movie/popular?api_key="+apiKey+"&language=en-US&page=1")
    Call<MovieList> getPopularMovie();

    @GET("movie/upcoming?api_key="+apiKey+"&language=en-US&page=1")
    Call <MovieList> getUpcomingMovie();

    @GET("movie/now_playing?api_key="+apiKey+"&language=en-US")
    Call <MovieList> getNowPlayingMovie();

    @GET("movie/search/movie?"+apiKey+"&language=en-US&query=")
    Call <MovieList> searchMovie();

    @GET
    Call<MovieList> getSimilarMovie(@Url String url);

    @GET("trending/tv/week?api_key="+apiKey)
    Call<MovieList> getTrendingTV();

    @GET
    Call<VideoModel> getVideo(@Url String str);
}
