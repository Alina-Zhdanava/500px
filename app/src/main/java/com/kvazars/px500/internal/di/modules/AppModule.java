package com.kvazars.px500.internal.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leo on 13.03.2016.
 */
@Module
public class AppModule {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final Application mApplication;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public AppModule(Application application) {
        mApplication = application;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Provides
    @Singleton
    Context providesApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}