package com.example.lin.boylove.entity.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ryne on 20/09/2017.
 */

public class Pagination {
    @SerializedName("per_page")
    @Expose
    private int perPage;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    @SerializedName("total_objects")
    @Expose
    private int totalObjects;

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }
}
