package com.example.lin.boylove.fragment.Chat;

import android.content.Context;

import com.example.lin.boylove.R;
import com.example.lin.boylove.entity.Response.Error;
import com.example.lin.boylove.entity.Response.ListChatRoom;
import com.example.lin.boylove.localstorage.SessionManager;
import com.example.lin.boylove.service.DolaxAPIs;
import com.example.lin.boylove.utilities.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ryne on 27/10/2017.
 */

public class ChatRoomInteractorIml implements ChatRoomInteractor {
    private Context context;
    private DolaxAPIs dolaxAPIs;

    public ChatRoomInteractorIml(Context context) {
        this.context = context;
        dolaxAPIs = DolaxAPIs.Factory.create();
    }

    @Override
    public void getLstChatRoom(final ChatRoomPresenter.OnChatFinishedListener listener) {
        Call<ListChatRoom> call = dolaxAPIs.getChatRooms(SessionManager.getInstance(context).getToken());
        call.enqueue(new Callback<ListChatRoom>() {
            @Override
            public void onResponse(Call<ListChatRoom> call, Response<ListChatRoom> response) {
                if (response.isSuccessful()) {
                    ListChatRoom listChatRoom = response.body();
                    if (listChatRoom != null) {
                        listener.onSuccess(listChatRoom);
                    }
                } else {
                    Error error = DolaxAPIs.Factory.getError(response.errorBody());
                    listener.onFailure(error.getErrors());
                }
            }

            @Override
            public void onFailure(Call<ListChatRoom> call, Throwable t) {
                Utils.showToast(context, context.getString(R.string.toast_st_went_wrong));
            }
        });
    }
}
