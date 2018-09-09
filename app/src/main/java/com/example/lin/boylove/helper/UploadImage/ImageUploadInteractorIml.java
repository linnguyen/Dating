package com.example.lin.boylove.helper.UploadImage;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.Image;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 27/10/2017.
 */

public class ImageUploadInteractorIml implements ImageUploadInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public ImageUploadInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void uploadImage(final String imagePath, final ImageUploadPresenter.OnUploadImageFinishedListener listener) {
        //creating a file
        File file = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Call<ResponseBody> call = dolaxAPIs.uploadImage(SessionManager.getInstance(context).getToken(), body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String path = response.body().string();
                        Image image = new Gson().fromJson(path, Image.class);
                        listener.onSuccess(image.getUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure(context.getString(R.string.toast_login_fail));
            }
        });
    }


//    @Override
//    public void getNewFeeds(final NewfeedPresenter.OnNewfeedFinishedListener listener) {
//        Call<ListNewFeed> call = dolaxAPIs.getNewFeeds(SessionManager.getInstance(context).getToken());
//        call.enqueue(new Callback<ListNewFeed>() {
//            @Override
//            public void onResponse(Call<ListNewFeed> call, Response<ListNewFeed> response) {
//                if (response.isSuccessful()) {
//                    ListNewFeed lstNewFeed = response.body();
//                    if (lstNewFeed != null) {
//                        listener.onSuccess(lstNewFeed);
//                    }
//                } else {
//                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
//                    listener.onFailure(error.getErrors());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListNewFeed> call, Throwable t) {
//                Utils.showToast(context, context.getString(R.string.toast_st_went_wrong));
//            }
//        });
//    }


//    public void uploadEventImage(String path, int eventId) {
//        //creating a file
//        File file = new File(path);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
//        final MultipartBody.Part body = MultipartBody.Part.createFormData("fileUpload", file.getName(), requestFile);
//        String accessToken = SharedPreferenceStorage.getInstance().getAccessToken();
//        final MultipartBody.Part accessTokenPart = MultipartBody.Part.createFormData("accessToken", accessToken);
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        //creating retrofit object
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_HTTP)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        Api api = retrofit.create(Api.class);
//        Call<ResponseBody> call = api.uploadImageToEvent(body, eventId, accessTokenPart);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        String path = response.body().string();
//                        view.onImageUploadSuccess(path);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    String error = "Upload fail";
//                    try {
//                        error = response.errorBody().string();
//                        Log.d("response", response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    view.onImageUploadFail(error);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                view.onImageUploadFail("Upload Fail");
//            }
//        });
//    }
}
