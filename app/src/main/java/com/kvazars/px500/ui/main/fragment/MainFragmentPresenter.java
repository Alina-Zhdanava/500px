package com.kvazars.px500.ui.main.fragment;

import android.text.TextUtils;

import com.kvazars.px500.interactors.ClearRecentSearchQueries;
import com.kvazars.px500.interactors.GetRecentSearchQueries;
import com.kvazars.px500.interactors.SaveSearchQueryToRecent;
import com.kvazars.px500.ui.base.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Leo on 15.03.2016.
 */
public class MainFragmentPresenter implements BasePresenter<MainFragmentView> {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private MainFragmentView mView;

    @Inject
    protected GetRecentSearchQueries mGetRecentSearchQueries;
    @Inject
    protected SaveSearchQueryToRecent mSaveSearchQueryToRecent;
    @Inject
    protected ClearRecentSearchQueries mClearRecentSearchQueries;

    private List<String> mRecentSearchQueries;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public void onViewAttached(MainFragmentView view) {
        mView = view;

        showRecentSearchQueries(false);
    }

    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onDestroyed() {

    }

    private void showRecentSearchQueries(boolean forceUpdate) {
        if (mRecentSearchQueries == null || forceUpdate) {
            mRecentSearchQueries = mGetRecentSearchQueries.execute();
        }

        mView.showRecentSearchQueries(mRecentSearchQueries);
    }

    public void doSearch(String searchQuery) {
        if (TextUtils.isEmpty(searchQuery)) return;

        mSaveSearchQueryToRecent.execute(searchQuery);
        showRecentSearchQueries(true);

        mView.clearSearchQuery();

        if (mView.getEventListener() != null) {
            mView.getEventListener().onDoSearchEvent(searchQuery);
        }
    }

    public void onRecentSearchQueryClick(String searchQuery) {
        if (mView.getEventListener() != null) {
            mView.getEventListener().onDoSearchEvent(searchQuery);
        }
    }

    public void clearRecent() {
        mClearRecentSearchQueries.execute();
        showRecentSearchQueries(true);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}