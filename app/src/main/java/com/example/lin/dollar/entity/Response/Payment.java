package com.example.lin.dollar.entity.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by lin on 20/08/2017.
 */

public class Payment {
    private String name;
    private double fee;
    @SerializedName("created_at")
    @Expose
    private Date date;
    private int user_id;

    public Payment(String name, double fee, Date date) {
        this.name = name;
        this.fee = fee;
        this.date = date;
    }

    public Payment(String name, double fee) {
        this.name = name;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
