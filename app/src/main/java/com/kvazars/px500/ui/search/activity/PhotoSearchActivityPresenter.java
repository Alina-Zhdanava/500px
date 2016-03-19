package com.kvazars.px500.ui.search.activity;

import com.kvazars.px500.ui.base.presenter.BasePresenter;

/**
 * Created by Leo on 15.03.2016.
 */
public class PhotoSearchActivityPresenter implements BasePresenter<PhotoSearchActivityView> {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final String mSearchQuery;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public PhotoSearchActivityPresenter(String searchQuery) {
        mSearchQuery = searchQuery;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public void onViewAttached(PhotoSearchActivityView view) {
        view.setTitle(mSearchQuery);
    }

    @Override
    public void onViewDetached() {

    }

    @Override
    public void onDestroyed() {

    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}