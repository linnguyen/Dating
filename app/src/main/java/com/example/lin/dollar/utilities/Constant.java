package com.example.lin.dollar.utilities;

/**
 * Created by ryne on 20/09/2017.
 */

public class Constant {
    public static final String BASE_URL = "https://dolax-clone.herokuapp.com/api/";

    // Firebase configuration
    public class Config {

        // global topic to receive app wide push notifications
        public static final String TOPIC_GLOBAL = "global";

        // broadcast receiver intent filters
        public static final String REGISTRATION_COMPLETE = "registrationComplete";
        public static final String PUSH_NOTIFICATION = "pushNotification";

        // id to handle the notification in the notification tray
        public static final int NOTIFICATION_ID = 100;
        public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

        public static final String SHARED_PREF = "ah_firebase";
    }
}
