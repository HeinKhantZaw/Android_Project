package com.heinkhantzaw.tn.movie_application.model.tv_model;

import com.google.gson.annotations.*;

import java.io.Serializable;

public class Seasons implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("air_date")
    private String air_date;

    @SerializedName("overview")
    private String overview;

    @SerializedName("episode_count")
    private String episode_count;

    @SerializedName("name")
    private String name;

    @SerializedName("season_number")
    private String season_number;

    @SerializedName("id")
    private String id;

    @SerializedName("poster_path")
    private String poster_path;

    public String getAir_date ()
    {
        return air_date;
    }

    public void setAir_date (String air_date)
    {
        this.air_date = air_date;
    }

    public String getOverview ()
    {
        return overview;
    }

    public void setOverview (String overview)
    {
        this.overview = overview;
    }

    public String getEpisode_count ()
    {
        return episode_count;
    }

    public void setEpisode_count (String episode_count)
    {
        this.episode_count = episode_count;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getSeason_number ()
    {
        return season_number;
    }

    public void setSeason_number (String season_number)
    {
        this.season_number = season_number;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPoster_path ()
    {
        return poster_path;
    }

    public void setPoster_path (String poster_path)
    {
        this.poster_path = poster_path;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [air_date = "+air_date+", overview = "+overview+", episode_count = "+episode_count+", name = "+name+", season_number = "+season_number+", id = "+id+", poster_path = "+poster_path+"]";
    }
}

