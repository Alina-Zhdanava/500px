package com.kvazars.px500.internal.di.modules;

import android.content.SharedPreferences;

import com.kvazars.px500.data.storage.DataStorage;
import com.kvazars.px500.data.storage.SharedPreferenceDataStorage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leo on 13.03.2016.
 */
@Module
public class RepositoryModule {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------

    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Provides
    DataStorage providesDataStorage(SharedPreferences sharedPreferences) {
        return new SharedPreferenceDataStorage(sharedPreferences);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}