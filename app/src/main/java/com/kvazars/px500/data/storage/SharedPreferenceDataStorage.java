package com.kvazars.px500.data.storage;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An implementation of {@link DataStorage}, which stores data in the shared preferences
 */
public class SharedPreferenceDataStorage implements DataStorage {
    //region CONSTANTS -----------------------------------------------------------------------------
    private static final String KEY_RECENT_SEARCH_QUERIES = "recentSearchQueries";
    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final Gson mGson;
    private final SharedPreferences mSharedPreferences;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public SharedPreferenceDataStorage(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;

        mGson = new Gson();
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public void saveSearchQueryToRecent(String query) {
        List<String> recentSearchQueries = new ArrayList<>(getRecentSearchQueries());
        recentSearchQueries.add(0, query);

        String json = mGson.toJson(recentSearchQueries);
        mSharedPreferences.edit().putString(KEY_RECENT_SEARCH_QUERIES, json).apply();
    }

    @Override
    public void clearRecentSearchQueries() {
        mSharedPreferences.edit().remove(KEY_RECENT_SEARCH_QUERIES).apply();
    }

    @Override
    public List<String> getRecentSearchQueries() {
        String json = mSharedPreferences.getString(KEY_RECENT_SEARCH_QUERIES, "[]");
        String[] result = mGson.fromJson(json, String[].class);
        return Arrays.asList(result);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}