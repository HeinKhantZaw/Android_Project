package com.heinkhantzaw.tn.movie_application.model.tv_model;

import com.google.gson.annotations.*;

import java.io.Serializable;

public class Networks implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("logo_path")
    private String logo_path;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("origin_country")
    private String origin_country;

    public String getLogo_path ()
    {
        return logo_path;
    }

    public void setLogo_path (String logo_path)
    {
        this.logo_path = logo_path;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getOrigin_country ()
    {
        return origin_country;
    }

    public void setOrigin_country (String origin_country)
    {
        this.origin_country = origin_country;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [logo_path = "+logo_path+", name = "+name+", id = "+id+", origin_country = "+origin_country+"]";
    }
}

