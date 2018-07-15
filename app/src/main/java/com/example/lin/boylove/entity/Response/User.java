package com.example.lin.boylove.entity.Response;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.lin.boylove.custom.commons.models.IUser;
import com.example.lin.boylove.utilities.Constant;

/**
 * Created by ryne on 20/09/2017.
 */

public class User implements Parcelable, IUser {
    private int id;
    private String email;
    private String auth_token;
    private Image image;
    private String created_at;
    private String updated_at;

    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        auth_token = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public String getAvatar() {
        return Constant.Config.URL_IMAGE + image.getUrl();
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(auth_token);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
    }
}
