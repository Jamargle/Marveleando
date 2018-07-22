package jmlb0003.com.marveleando.app.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.model.MarvelUrl;

public final class FirebaseAnalyticsHelper {

    private static final String LOG_TAG = "FirebaseAnalytics";
    private static final String CURRENT_SCREEN_SPLASH = "current_screen_splash";
    private static final String CURRENT_SCREEN_LIST = "current_screen_list";
    private static final String CURRENT_SCREEN_DETAILS = "current_screen_details";
    private static final String EVENT_KEY_SHOW_FAVORITES = "show_favorites";
    private static final String EVENT_KEY_SHOW_WITH_NO_FILTERS = "show_without_filters";
    private static final String EVENT_KEY_CHARACTER_CLICKED = "character_clicked";
    private static final String EVENT_KEY_SEARCH = "search";
    private static final String EVENT_KEY_ON_SCROLL = "scroll_character_list";
    private static final String PARAM_KEY_CURRENT_PAGE = "Key:CurrentPage";
    private static final String EVENT_KEY_FAVORITE_CLICK = "character_is_favorite";
    private static final String PARAM_KEY_IS_FAVORITE = "Key:is_favorite";
    private static final String EVENT_KEY_CHARACTER_LINK_CLICKED = "character_link_clicked";
    private static final String PARAM_KEY_LINK_TYPE = "Key:character_link_type";

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

    // region logging events
    public void logShowFavorites() {
        logEvent(EVENT_KEY_SHOW_FAVORITES, new Bundle());
    }

    public void logShowWithoutFilters() {
        logEvent(EVENT_KEY_SHOW_WITH_NO_FILTERS, new Bundle());
    }

    public void logCharacterClicked(final Character character) {
        final Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(character.getId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.getName());
        logEvent(EVENT_KEY_CHARACTER_CLICKED, bundle);
    }

    public void logSearch(final String query) {
        final Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, query);
        logEvent(EVENT_KEY_SEARCH, bundle);
    }

    public void logScroll(final int currentPage, final String query) {
        final Bundle bundle = new Bundle();
        bundle.putString(PARAM_KEY_CURRENT_PAGE, String.valueOf(currentPage));
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, query);
        logEvent(EVENT_KEY_ON_SCROLL, bundle);
    }

    public void logMarkedAsFavorite(final Character character) {
        final Bundle bundle = new Bundle();
        bundle.putString(PARAM_KEY_IS_FAVORITE, String.valueOf(character.isFavorite()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(character.getId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.getName());
        logEvent(EVENT_KEY_FAVORITE_CLICK, bundle);
    }

    public void logCharacterLinkCLicked(final Character character, final MarvelUrl url) {
        final Bundle bundle = new Bundle();
        bundle.putString(PARAM_KEY_LINK_TYPE, url.getType());
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(character.getId()));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.getName());
        logEvent(EVENT_KEY_CHARACTER_LINK_CLICKED, bundle);
    }
    // endregion

    private void setCurrentScreen(
            @NonNull final Activity activity,
            @NonNull final String screenName) {

        firebaseAnalytics.setCurrentScreen(activity, screenName, null);
        logCurrentScreen(screenName);
    }

    private void logEvent(
            @NonNull final String eventKey,
            @NonNull final Bundle params) {

        firebaseAnalytics.logEvent(eventKey, params);
        Log.d(LOG_TAG, "Track event-> " + eventKey + " Params-> " + params);
    }

    private void logCurrentScreen(final String activityName) {
        Log.d(LOG_TAG, "Current screen-> " + activityName);
    }

}
