package com.example.lin.boylove.utilities;

/**
 * Created by ryne on 20/09/2017.
 */

public class Constant {
    public static final String BASE_URL = "https://dolax-clone.herokuapp.com/api/";

    public static TestBuilder builder() {
        return new TestBuilder();
    }

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

        public static final String BASE_URL = "http://206.189.71.173/api/";
        public static final String SOCKET_URL = "ws://206.189.71.173/cable";
        public static final String URL_IMAGE = "http://206.189.71.173";

//        public static final String BASE_URL = "http://192.168.1.13:3000/api/";
//        public static final String SOCKET_URL = "ws://192.168.1.13:3000/cable";
//        public static final String URL_IMAGE = "http://192.168.1.13:3000";
    }

    // Tab layout name
    public static final String TAB_PAYMENT = "Payment";
    public static final String TAB_INCOME = "Income";
    public static final String TAB_DEBT = "Debt";

    public static final String EMPTY = "";


    public static class TestBuilder {
        public TestBuilder() {

        }

        public TestBuilder hello() {
            return this;
        }

        public TestBuilder hi() {
            return this;
        }

        public TestBuilder goodMorning() {
            return this;
        }
    }


}
