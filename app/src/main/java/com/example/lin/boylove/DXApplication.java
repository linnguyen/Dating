package com.example.lin.boylove;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;

import com.enclaveit.ezfaxing.services.EzFaxingAPIs;
import com.enclaveit.ezfaxing.services.EzFaxingWebAPIs;

import java.lang.reflect.Field;

/**
 * Created by thanh.nguyen on 7/5/17.
 */
public class EFApplication extends Application {
    /**
     * EzFaxingAPIs
     */
    public static Typeface efFontStyle;
    /**
     * Application context
     */
    private Context context;
    /**
     * EzFaxingAPIs
     */
    private EzFaxingAPIs ezFaxingAPIs;
    /**
     * EzFaxingWebAPIs
     */
    private EzFaxingWebAPIs ezFaxingWebAPIs;

    /**
     * Get EFApplication instance
     *
     * @param context the context
     * @return EFApplication
     */
    public static EFApplication get(Context context) {
        return (EFApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // register to be informed of activities starting up
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // new activity created; force its orientation to portrait
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/ProximaNova-Regular.ttf");
    }

    /**
     * Get the context of application
     *
     * @return context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Get the EzFaxingAPIs
     *
     * @return EzFaxingAPIs
     */
    public EzFaxingAPIs getEzFaxingAPIs() {
        if (ezFaxingAPIs == null) {
            ezFaxingAPIs = EzFaxingAPIs.Factory.create();
        }
        return ezFaxingAPIs;
    }

    /**
     * Get the EzFaxingWebAPIs
     *
     * @return EzFaxingWebAPIs
     */
    public EzFaxingWebAPIs getEzFaxingWebAPIs() {
        if (ezFaxingWebAPIs == null) {
            ezFaxingWebAPIs = EzFaxingWebAPIs.Factory.create();
        }
        return ezFaxingWebAPIs;
    }

    static class FontsOverride {
        static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
            efFontStyle = Typeface.createFromAsset(context.getAssets(),
                    fontAssetName);
            replaceFont(staticTypefaceFieldName, efFontStyle);
        }

        static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField(staticTypefaceFieldName);
                staticField.setAccessible(true);
                staticField.set(null, newTypeface);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
