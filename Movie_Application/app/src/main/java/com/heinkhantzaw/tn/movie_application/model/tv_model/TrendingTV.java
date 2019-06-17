
package com.heinkhantzaw.tn.movie_application.model.tv_model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TrendingTV implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<Result_TrendingTV> mResults;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<Result_TrendingTV> getResults() {
        return mResults;
    }

    public void setResults(List<Result_TrendingTV> results) {
        mResults = results;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
