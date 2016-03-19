package com.kvazars.px500.ui.base.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

/**
 * A helper class, which deals with a {@link BasePresenter} creation using {@link Loader} and {@link LoaderManager}
 */
public class PresenterFacade<T extends BasePresenter> {
    //region CONSTANTS -----------------------------------------------------------------------------
    private static final int LOADER_ID = 404;
    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final PresenterCallbacks<T> mPresenterCallbacks;

    private PresenterFactory<T> mPresenterFactory;
    private Context mContext;

    private boolean mDelivered = false;

    private LoaderManager.LoaderCallbacks mLoaderCallbacks = new LoaderManager.LoaderCallbacks<T>() {
        @Override
        public Loader<T> onCreateLoader(int id, Bundle args) {
            return new PresenterLoader<>(mContext, mPresenterFactory);
        }

        @Override
        public void onLoadFinished(Loader<T> loader, T data) {
            if (!mDelivered) {
                mDelivered = true;
                if (mPresenterCallbacks != null) {
                    mPresenterCallbacks.onPresenterCreated(data);
                }
            }
        }

        @Override
        public void onLoaderReset(Loader<T> loader) {
            if (mPresenterCallbacks != null) {
                mPresenterCallbacks.onPresenterDestroyed();
            }
        }
    };
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public PresenterFacade(PresenterCallbacks<T> callbacks) {
        mPresenterCallbacks = callbacks;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    public void create(Context context, LoaderManager loaderManager, PresenterFactory<T> presenterFactory) {
        mContext = context;
        mPresenterFactory = presenterFactory;
        loaderManager.initLoader(LOADER_ID, null, mLoaderCallbacks);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    public interface PresenterCallbacks<T extends BasePresenter> {
        void onPresenterCreated(T presenter);
        void onPresenterDestroyed();
    }
    //endregion
}