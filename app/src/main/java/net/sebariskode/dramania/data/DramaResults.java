package net.sebariskode.dramania.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bagus on 22/10/16.
 */

public class DramaResults {
    @SerializedName("results")
    private List<Drama> results;
    @SerializedName("page")
    private String page;
    @SerializedName("total_pages")
    private String total_pages;
    @SerializedName("total_results")
    private String total_results;

    public List<Drama> getDramas() {
        return results;
    }

    public void setDramas(List<Drama> results) {
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }
}
