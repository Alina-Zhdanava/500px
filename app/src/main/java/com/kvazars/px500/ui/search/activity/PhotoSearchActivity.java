package com.kvazars.px500.ui.search.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.kvazars.px500.ui.base.activity.BaseFragmentActivity;
import com.kvazars.px500.ui.base.presenter.PresenterFactory;
import com.kvazars.px500.ui.search.fragment.PhotoPhotoSearchFragment;

/**
 * Created by Leo on 15.03.2016.
 */
public class PhotoSearchActivity extends BaseFragmentActivity<PhotoSearchActivityPresenter, PhotoSearchActivityView> implements PhotoSearchActivityView {
    //region INJECTED VIEWS ------------------------------------------------------------------------

    //endregion

    //region CONSTANTS -----------------------------------------------------------------------------
    private static final String EXTRA_SEARCH_QUERY = "searchQuery";
    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private String mSearchQuery;
    //endregion

    //region LIFE CYCLE ----------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(EXTRA_SEARCH_QUERY)) {
            throw new IllegalStateException("No search query specified");
        }
        mSearchQuery = intent.getStringExtra(EXTRA_SEARCH_QUERY);

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    protected Fragment getContentFragment() {
        return PhotoPhotoSearchFragment.getInstance(mSearchQuery);
    }

    @Override
    protected PresenterFactory<PhotoSearchActivityPresenter> getPresenterFactory() {
        return applicationContext -> new PhotoSearchActivityPresenter(mSearchQuery);
    }

    @Override
    protected PhotoSearchActivityView getPresenterView() {
        return this;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    public static Intent getLaunchIntent(Context context, String searchQuery) {
        Intent intent = new Intent(context, PhotoSearchActivity.class);
        intent.putExtra(EXTRA_SEARCH_QUERY, searchQuery);
        return intent;
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}