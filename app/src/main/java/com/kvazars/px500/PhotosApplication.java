package com.kvazars.px500;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.kvazars.px500.internal.di.components.AppComponent;
import com.kvazars.px500.internal.di.components.DaggerAppComponent;
import com.kvazars.px500.internal.di.modules.AppModule;
import com.kvazars.px500.internal.di.modules.NetModule;
import com.squareup.picasso.Picasso;

/**
 * Created by Leo on 14.03.2016.
 */
public class PhotosApplication extends Application {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private AppComponent mAppComponent;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.500px.com/v1/", "7obxDs9u9NTkjPTCnlVspXyXKCnPT6Pddk4a2jNU"))
                .build();

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        Picasso picasso = builder.build();
        Picasso.setSingletonInstance(picasso);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}