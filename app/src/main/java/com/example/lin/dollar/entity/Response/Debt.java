package com.example.lin.dollar.entity.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ryne on 13/10/2017.
 */

public class Debt {
    // still consider for image here
    private String content;
    private double fee;
    @SerializedName("created_at")
    @Expose
    private Date date;
    @SerializedName("finance_type")
    @Expose
    private int financeType;
    private int user_id;

    public Debt(String content, double fee, Date date, int financeType, int user_id) {
        this.content = content;
        this.fee = fee;
        this.date = date;
        this.financeType = financeType;
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFinanceType() {
        return financeType;
    }

    public void setFinanceType(int financeType) {
        this.financeType = financeType;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
