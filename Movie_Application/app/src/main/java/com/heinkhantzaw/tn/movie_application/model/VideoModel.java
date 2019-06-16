
package com.heinkhantzaw.tn.movie_application.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class VideoModel implements Serializable {

    @SerializedName("id")
    private Long mId;
    @SerializedName("results")
    private List<VideoResults> mResults;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<VideoResults> getResults() {
        return mResults;
    }

    public void setResults(List<VideoResults> results) {
        mResults = results;
    }

}
