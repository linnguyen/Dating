package com.example.lin.boylove.localstorage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ryne on 20/09/2017.
 */

public class SessionManager {
    /**
     * Token
     */
    private static final String TOKEN = "token";
    /**
     * Shared preferences file name
     */
    private static final String PREF_NAME = "Dolax_sharedpreferences";
    /**
     * /*
     * SessionManager
     */
    private static SessionManager instance;
    /*
     * Shared Preferences
     */
    private SharedPreferences preferences;
    /*
     * Editor for shared preferences
     */
    private SharedPreferences.Editor editor;

    private int PRIVATE_MODE = 0;

    /**
     * Constructor
     *
     * @param context the context
     */
    protected SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public String getToken() {
        return preferences.getString(TOKEN, "");
    }

    public void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }


}
