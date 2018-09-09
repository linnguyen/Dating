package com.example.lin.boylove.entity.Response;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.lin.boylove.custom.commons.models.IUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ryne on 20/09/2017.
 */

public class User implements Parcelable, IUser {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("auth_token")
    @Expose
    private String authToken;

    @SerializedName("image")
    @Expose
    private Image image;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("online")
    @Expose
    private boolean online;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    protected User(Parcel in) {
        id = in.readInt();
        email = in.readString();
        authToken = in.readString();
        userName = in.readString();
        online = in.readByte() != 0;
        created_at = in.readString();
        updated_at = in.readString();
        image = in.readParcelable(Image.class.getClassLoader());
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
        return userName;
    }

    @Override
    public String getAvatar() {
        return image.getUrl();
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(email);
        parcel.writeString(authToken);
        parcel.writeString(userName);
        parcel.writeByte((byte) (online ? 1 : 0));
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeParcelable(this.image, i);
    }
}
