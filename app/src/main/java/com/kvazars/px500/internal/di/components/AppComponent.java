package com.kvazars.px500.internal.di.components;

import com.kvazars.px500.internal.di.modules.AppModule;
import com.kvazars.px500.internal.di.modules.NetModule;
import com.kvazars.px500.internal.di.modules.RepositoryModule;
import com.kvazars.px500.ui.main.activity.MainActivityPresenter;
import com.kvazars.px500.ui.main.fragment.MainFragmentPresenter;
import com.kvazars.px500.ui.search.fragment.PhotoSearchFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Leo on 15.03.2016.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivityPresenter mainActivityPresenter);
    void inject(MainFragmentPresenter mainFragmentPresenter);

    void inject(PhotoSearchFragmentPresenter photoSearchFragmentPresenter);
}
