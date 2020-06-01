package com.heinkhantzaw.tn.movie_application.rest;

import com.heinkhantzaw.tn.movie_application.model.MovieList;
import com.heinkhantzaw.tn.movie_application.model.VideoModel;
import com.heinkhantzaw.tn.movie_application.model.tv_model.TrendingTV;
import com.heinkhantzaw.tn.movie_application.model.tv_model.model;

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

    @GET
    Call<MovieList> getSimilarMovie(@Url String url);

    @GET("trending/tv/week?api_key="+apiKey)
    Call<TrendingTV> getTrendingTV();

    @GET
    Call<MovieList> getSearchResults(@Url String query);

    @GET
    Call<model> getTV_details(@Url String url);

    @GET
    Call<VideoModel> getVideo(@Url String url);
}
