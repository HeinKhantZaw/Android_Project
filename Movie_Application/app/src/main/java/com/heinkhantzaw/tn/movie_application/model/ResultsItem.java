
package com.heinkhantzaw.tn.movie_application.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ResultsItem implements Serializable {

    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genre_ids")
    private List<Long> genreIds;
    @Expose
    private Long id;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @Expose
    private String overview;
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @Expose
    private String title;
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Long voteCount;

    public Boolean getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getVideo() {
        return video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public static class Builder {

        private Boolean adult;
        private String backdropPath;
        private List<Long> genreIds;
        private Long id;
        private String originalLanguage;
        private String originalTitle;
        private String overview;
        private Double popularity;
        private String posterPath;
        private String releaseDate;
        private String title;
        private Boolean video;
        private Double voteAverage;
        private Long voteCount;

        public ResultsItem.Builder withAdult(Boolean adult) {
            this.adult = adult;
            return this;
        }

        public ResultsItem.Builder withBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
            return this;
        }

        public ResultsItem.Builder withGenreIds(List<Long> genreIds) {
            this.genreIds = genreIds;
            return this;
        }

        public ResultsItem.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public ResultsItem.Builder withOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
            return this;
        }

        public ResultsItem.Builder withOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public ResultsItem.Builder withOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public ResultsItem.Builder withPopularity(Double popularity) {
            this.popularity = popularity;
            return this;
        }

        public ResultsItem.Builder withPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public ResultsItem.Builder withReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public ResultsItem.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ResultsItem.Builder withVideo(Boolean video) {
            this.video = video;
            return this;
        }

        public ResultsItem.Builder withVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
            return this;
        }

        public ResultsItem.Builder withVoteCount(Long voteCount) {
            this.voteCount = voteCount;
            return this;
        }

        public ResultsItem build() {
            ResultsItem resultsItem = new ResultsItem();
            resultsItem.adult = adult;
            resultsItem.backdropPath = backdropPath;
            resultsItem.genreIds = genreIds;
            resultsItem.id = id;
            resultsItem.originalLanguage = originalLanguage;
            resultsItem.originalTitle = originalTitle;
            resultsItem.overview = overview;
            resultsItem.popularity = popularity;
            resultsItem.posterPath = posterPath;
            resultsItem.releaseDate = releaseDate;
            resultsItem.title = title;
            resultsItem.video = video;
            resultsItem.voteAverage = voteAverage;
            resultsItem.voteCount = voteCount;
            return resultsItem;
        }

    }

}
