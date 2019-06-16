package com.heinkhantzaw.tn.movie_application.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest
{
    private static Retrofit retrofit=null;
    public static Retrofit getRetrofit()
    {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }
}
