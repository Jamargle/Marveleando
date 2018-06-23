package jmlb0003.com.marveleando.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import jmlb0003.com.marveleando.R;

public final class SharedPreferencesHandler {

    private final Context context;
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesHandler(final Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public long getLastCharactersRefresh() {
        final String key = context.getString(R.string.prefs_key_last_refresh);
        return getLong(key, 0);
    }

    public void setLastCharactersRefresh(final long lastRefresh) {
        final String key = context.getString(R.string.prefs_key_last_refresh);
        setLong(key, lastRefresh);

    }

    private long getLong(
            final String key,
            final long defaultValue) {

        return sharedPreferences.getLong(key, defaultValue);
    }

    private void setLong(
            final String key,
            final long value) {

        sharedPreferences.edit().putLong(key, value).apply();
    }

}
