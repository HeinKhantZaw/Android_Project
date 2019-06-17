package com.heinkhantzaw.tn.movie_application.model.tv_model;

import com.google.gson.annotations.*;

import java.io.Serializable;

public class Genres implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

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

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", id = "+id+"]";
    }
}

