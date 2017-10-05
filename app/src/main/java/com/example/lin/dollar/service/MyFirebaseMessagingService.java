package com.example.lin.dollar.service;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.lin.dollar.utilities.Constant;
import com.example.lin.dollar.utilities.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ryne on 05/10/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage == null)
            return;

        if (remoteMessage.getNotification() != null) {

        }
    }

    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            Intent pushNotification = new Intent(Constant.Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
        }
    }
}
