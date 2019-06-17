package com.heinkhantzaw.tn.movie_application.model.tv_model;

import com.google.gson.annotations.*;

import java.io.Serializable;

public class Last_episode_to_air implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("production_code")
    private String production_code;

    @SerializedName("air_date")
    private String air_date;

    @SerializedName("overview")
    private String overview;

    @SerializedName("episode_number")
    private String episode_number;

    @SerializedName("show_id")
    private String show_id;

    @SerializedName("vote_average")
    private String vote_average;

    @SerializedName("name")
    private String name;

    @SerializedName("season_number")
    private String season_number;

    @SerializedName("id")
    private String id;

    @SerializedName("still_path")
    private String still_path;

    @SerializedName("vote_count")
    private String vote_count;

    public String getProduction_code ()
    {
        return production_code;
    }

    public void setProduction_code (String production_code)
    {
        this.production_code = production_code;
    }

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

    public String getEpisode_number ()
    {
        return episode_number;
    }

    public void setEpisode_number (String episode_number)
    {
        this.episode_number = episode_number;
    }

    public String getShow_id ()
    {
        return show_id;
    }

    public void setShow_id (String show_id)
    {
        this.show_id = show_id;
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

    public String getStill_path ()
    {
        return still_path;
    }

    public void setStill_path (String still_path)
    {
        this.still_path = still_path;
    }

    public String getVote_count ()
    {
        return vote_count;
    }

    public void setVote_count (String vote_count)
    {
        this.vote_count = vote_count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [production_code = "+production_code+", air_date = "+air_date+", overview = "+overview+", episode_number = "+episode_number+", show_id = "+show_id+", vote_average = "+vote_average+", name = "+name+", season_number = "+season_number+", id = "+id+", still_path = "+still_path+", vote_count = "+vote_count+"]";
    }
}

