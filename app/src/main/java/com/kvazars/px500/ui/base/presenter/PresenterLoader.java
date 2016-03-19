package com.kvazars.px500.ui.base.presenter;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * A {@link Loader}, which holds a reference to {@link BasePresenter}
 */
class PresenterLoader<T extends BasePresenter> extends Loader<T> {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private T mPresenter;
    private final PresenterFactory<T> mFactory;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public PresenterLoader(Context context, PresenterFactory<T> factory) {
        super(context);
        mFactory = factory;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    protected void onStartLoading() {
        if (mPresenter != null) {
            deliverResult(mPresenter);
            return;
        }

        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        mPresenter = mFactory.create(getContext());

        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        if (mPresenter != null) {
            mPresenter.onDestroyed();
            mPresenter = null;
        }
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}