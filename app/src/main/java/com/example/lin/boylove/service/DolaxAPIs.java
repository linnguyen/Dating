package com.example.lin.boylove.service;

import com.example.lin.boylove.entity.Response.ChatRoom;
import com.example.lin.boylove.entity.Response.Debt;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.Income;
import com.example.lin.boylove.entity.Response.ListChatMessage;
import com.example.lin.boylove.entity.Response.ListChatRoom;
import com.example.lin.boylove.entity.Response.Online;
import com.example.lin.boylove.entity.Response.Payment;
import com.example.lin.boylove.entity.Response.User;
import com.example.lin.boylove.utilities.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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
    @POST("signin/")
    Call<User> login(@Body JsonObject jsonObject);

    /*
     *Logout
     */
    @POST("signout/")
    Call<User> logout(@Body JsonObject jsonObject);

    /*
     *Get list payment
     */
    @GET("payments/")
    Call<List<Payment>> getPayments(@Header("Authorization") String authToken);

    /*
     *Add payment
     */
    @POST("users/100/payments/")
    Call<Payment> createPayment(@Header("Authorization") String authToken, @Body JsonObject jsonObject);

    /*
     *Get list debt
     */
    @GET("debts/")
    Call<List<Debt>> getDebts(@Header("Authorization") String authToken);

    /*
     *Get list income
     */
    @GET("incomes/")
    Call<List<Income>> getIncomes(@Header("Authorization") String authToken);

    /*
     * Get online user
     */
    @GET("users/")
    Call<Online> getOnlines(@Header("Authorization") String authToken);

    /*
     * Get list chatrooms
     */
    @GET("rooms/")
    Call<ListChatRoom> getChatRooms(@Header("Authorization") String authToken);

    /*
    * Get messages by room
    */
    @GET("messages/")
    Call<ListChatMessage> getMessagesByRoom(@Header("Authorization") String authToken, @Query("chatroom_id") int chatRoomId);

    /*
   * Get private messages between two users
   */
    @GET("private_messages/")
    Call<ListChatMessage> getPrivateMessages(@Header("Authorization") String authToken, @Query("other_user_id") int otherUserId);

    /*
  * Get private messages between two users
  */
    @GET("private_room/")
    Call<ChatRoom> getPrivateRoom(@Header("Authorization") String authToken, @Query("other_user_id") int otherUserId);

    /*
   * Get user profile
   */
    @GET("profile/")
    Call<User> getUserProfile(@Header("Authorization") String authToken, @Query("id") int userId);

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
                    .baseUrl(Constant.Config.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(DolaxAPIs.class);
        }

        public static Error getError(ResponseBody errorResponse) {
            return new Gson().fromJson(errorResponse.charStream(), Error.class);
        }
    }
}


