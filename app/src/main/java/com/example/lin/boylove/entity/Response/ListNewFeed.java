package com.example.lin.boylove.entity.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lin on 13/05/2018.
 */

public class ListNewFeed {
    @SerializedName("newfeeds")
    @Expose
    private List<NewFeed> lstNewFeed;

    @SerializedName("meta")
    @Expose
    private Pagination pagination;

    public List<NewFeed> getLstNewFeed() {
        return lstNewFeed;
    }

    public void setLstNewFeed(List<NewFeed> lstNewFeed) {
        this.lstNewFeed = lstNewFeed;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
