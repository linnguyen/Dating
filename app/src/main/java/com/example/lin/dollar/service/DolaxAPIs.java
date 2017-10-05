package com.example.lin.dollar.service;

import com.example.lin.dollar.entity.Response.Payment;
import com.example.lin.dollar.entity.Response.User;
import com.example.lin.dollar.utilities.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ryne on 20/09/2017.
 */

public interface DolaxAPIs {
    /*
     * Get user sample
     */
    @GET("users/")
    Call<List<User>> getUsers();

    /*
    * Login
     */
    @POST("sessions/")
    Call<User> login(@Body JsonObject jsonObject);

    /*
     Logout
     */
    @POST("sessions/")
    Call<User> logout(@Query("email") String email, @Query("password") String password);

    /*
     Get list payment
     */
    @GET("payments/")
    Call<List<Payment>> getPayments(@Header("Authorization") String authToken);

    /*
     Add payment
     */
    @POST("users/100/payments/")
    Call<Payment> createPayment(@Header("Authorization") String token, @Body JsonObject jsonObject);

    class Factory {
        public static DolaxAPIs create() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(DolaxAPIs.class);
        }
    }
}
