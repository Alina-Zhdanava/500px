package com.kvazars.px500.ui.main.activity;

import com.kvazars.px500.ui.base.presenter.BasePresenter;

/**
 * Created by Leo on 15.03.2016.
 */
public class MainActivityPresenter implements BasePresenter<MainActivityView> {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private MainActivityView mView;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public void onViewAttached(MainActivityView view) {
        mView = view;
        mView.setTitle("Main activity");
    }

    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onDestroyed() {

    }

    public void doSearch(String searchQuery) {
        mView.navigateToSearchScreen(searchQuery);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}