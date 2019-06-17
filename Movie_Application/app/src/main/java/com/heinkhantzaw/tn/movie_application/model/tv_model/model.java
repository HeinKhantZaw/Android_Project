package com.heinkhantzaw.tn.movie_application.model.tv_model;

import com.google.gson.annotations.*;

import java.io.Serializable;

public class model implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("original_language")
    private String original_language;

    @SerializedName("number_of_episodes")
    private String number_of_episodes;

    @SerializedName("networks")
    private Networks[] networks;

    @SerializedName("type")
    private String type;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("genres")
    private Genres[] genres;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("id")
    private String id;

    @SerializedName("number_of_seasons")
    private String number_of_seasons;

    @SerializedName("vote_count")
    private String vote_count;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("overview")
    private String overview;

    @SerializedName("seasons")
    private Seasons[] seasons;

    @SerializedName("languages")
    private String[] languages;

    @SerializedName("last_episode_to_air")
    private Last_episode_to_air last_episode_to_air;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("origin_country")
    private String[] origin_country;

    @SerializedName("original_name")
    private String original_name;

    @SerializedName("vote_average")
    private String vote_average;

    @SerializedName("name")
    private String name;

    @SerializedName("episode_run_time")
    private String[] episode_run_time;

    @SerializedName("in_production")
    private String in_production;

    @SerializedName("last_air_date")
    private String last_air_date;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("status")
    private String status;

    public String getOriginal_language ()
    {
        return original_language;
    }

    public void setOriginal_language (String original_language)
    {
        this.original_language = original_language;
    }

    public String getNumber_of_episodes ()
    {
        return number_of_episodes;
    }

    public void setNumber_of_episodes (String number_of_episodes)
    {
        this.number_of_episodes = number_of_episodes;
    }

    public Networks[] getNetworks ()
    {
        return networks;
    }

    public void setNetworks (Networks[] networks)
    {
        this.networks = networks;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getBackdrop_path ()
    {
        return backdrop_path;
    }

    public void setBackdrop_path (String backdrop_path)
    {
        this.backdrop_path = backdrop_path;
    }

    public Genres[] getGenres ()
    {
        return genres;
    }

    public void setGenres (Genres[] genres)
    {
        this.genres = genres;
    }

    public String getPopularity ()
    {
        return popularity;
    }

    public void setPopularity (String popularity)
    {
        this.popularity = popularity;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getNumber_of_seasons ()
    {
        return number_of_seasons;
    }

    public void setNumber_of_seasons (String number_of_seasons)
    {
        this.number_of_seasons = number_of_seasons;
    }

    public String getVote_count ()
    {
        return vote_count;
    }

    public void setVote_count (String vote_count)
    {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date ()
    {
        return first_air_date;
    }

    public void setFirst_air_date (String first_air_date)
    {
        this.first_air_date = first_air_date;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public Seasons[] getSeasons ()
    {
        return seasons;
    }

    public void setSeasons (Seasons[] seasons)
    {
        this.seasons = seasons;
    }

    public String[] getLanguages ()
    {
        return languages;
    }

    public void setLanguages (String[] languages)
    {
        this.languages = languages;
    }


    public Last_episode_to_air getLast_episode_to_air ()
    {
        return last_episode_to_air;
    }

    public void setLast_episode_to_air (Last_episode_to_air last_episode_to_air)
    {
        this.last_episode_to_air = last_episode_to_air;
    }

    public String getPoster_path ()
    {
        return poster_path;
    }

    public void setPoster_path (String poster_path)
    {
        this.poster_path = poster_path;
    }

    public String[] getOrigin_country ()
    {
        return origin_country;
    }

    public void setOrigin_country (String[] origin_country)
    {
        this.origin_country = origin_country;
    }

    public String getOriginal_name ()
    {
        return original_name;
    }

    public void setOriginal_name (String original_name)
    {
        this.original_name = original_name;
    }

    public String getVote_average ()
    {
        return vote_average;
    }

    public void setVote_average (String vote_average)
    {
        this.vote_average = vote_average;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String[] getEpisode_run_time ()
    {
        return episode_run_time;
    }

    public void setEpisode_run_time (String[] episode_run_time)
    {
        this.episode_run_time = episode_run_time;
    }

    public String getIn_production ()
    {
        return in_production;
    }

    public void setIn_production (String in_production)
    {
        this.in_production = in_production;
    }

    public String getLast_air_date ()
    {
        return last_air_date;
    }

    public void setLast_air_date (String last_air_date)
    {
        this.last_air_date = last_air_date;
    }

    public String getHomepage ()
    {
        return homepage;
    }

    public void setHomepage (String homepage)
    {
        this.homepage = homepage;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
			
			