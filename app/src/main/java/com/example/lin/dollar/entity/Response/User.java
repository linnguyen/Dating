package com.example.lin.dollar.entity.Response;

/**
 * Created by ryne on 20/09/2017.
 */

public class User {
    private int id;
    private String email;
    private String auth_token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
}
