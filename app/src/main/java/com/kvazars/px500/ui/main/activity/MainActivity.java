package com.kvazars.px500.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.kvazars.px500.PhotosApplication;
import com.kvazars.px500.ui.base.activity.BaseFragmentActivity;
import com.kvazars.px500.ui.base.presenter.PresenterFactory;
import com.kvazars.px500.ui.main.fragment.MainFragment;
import com.kvazars.px500.ui.main.fragment.MainFragmentEventListener;
import com.kvazars.px500.ui.search.activity.PhotoSearchActivity;

/**
 * Created by Leo on 14.03.2016.
 */
public class MainActivity extends BaseFragmentActivity<MainActivityPresenter, MainActivityView> implements MainActivityView, MainFragmentEventListener {
    //region INJECTED VIEWS ------------------------------------------------------------------------

    //endregion

    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------

    //endregion

    //region LIFE CYCLE ----------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        MainFragment mainFragment = (MainFragment) fragment;
        mainFragment.setEventListener(this);
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void navigateToSearchScreen(String searchQuery) {
        startActivity(PhotoSearchActivity.getLaunchIntent(this, searchQuery));
    }

    @Override
    public void onDoSearchEvent(String searchQuery) {
        mPresenter.doSearch(searchQuery);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    @Nullable
    @Override
    protected Fragment getContentFragment() {
        return new MainFragment();
    }

    @Override
    protected PresenterFactory<MainActivityPresenter> getPresenterFactory() {
        return applicationContext -> {
            PhotosApplication application = (PhotosApplication) applicationContext;
            MainActivityPresenter mainActivityPresenter = new MainActivityPresenter();
            application.getAppComponent().inject(mainActivityPresenter);
            return mainActivityPresenter;
        };
    }

    @Override
    protected MainActivityView getPresenterView() {
        return this;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}