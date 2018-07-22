package jmlb0003.com.marveleando.app.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

public final class FirebaseAnalyticsHelper {

    private static final String LOG_TAG = "FirebaseAnalytics";
    private static final String CURRENT_SCREEN_SPLASH = "current_screen_splash";
    private static final String CURRENT_SCREEN_LIST = "current_screen_list";
    private static final String CURRENT_SCREEN_DETAILS = "current_screen_details";

    private static FirebaseAnalytics firebaseAnalytics;

    @Inject
    public FirebaseAnalyticsHelper(final Context applicationContext) {
        if (firebaseAnalytics == null) {
            firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext);
        }
    }

    // region logging screens
    public void setCurrentScreenSplash(final Activity splashActivity) {
        setCurrentScreen(splashActivity, CURRENT_SCREEN_SPLASH);
    }

    public void setCurrentScreenList(final Activity listActivity) {
        setCurrentScreen(listActivity, CURRENT_SCREEN_LIST);
    }

    public void setCurrentScreenDetails(final Activity detailsActivity) {
        setCurrentScreen(detailsActivity, CURRENT_SCREEN_DETAILS);
    }
    // endregion

    private void setCurrentScreen(
            @NonNull final Activity activity,
            @NonNull final String screenName) {

        firebaseAnalytics.setCurrentScreen(activity, screenName, null);
        logCurrentScreen(screenName);
    }

    private void logEvent(
            final String eventKey,
            final Bundle params) {

        firebaseAnalytics.logEvent(eventKey, params);
        Log.d(LOG_TAG, "Track event-> " + eventKey + " Params-> " + params);
    }

    private void logCurrentScreen(final String activityName) {
        Log.d(LOG_TAG, "Current screen-> " + activityName);
    }

}
