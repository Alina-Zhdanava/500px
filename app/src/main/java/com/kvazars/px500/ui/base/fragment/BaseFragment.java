package com.kvazars.px500.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.kvazars.px500.ui.base.presenter.BasePresenter;
import com.kvazars.px500.ui.base.presenter.PresenterFacade;
import com.kvazars.px500.ui.base.presenter.PresenterFactory;

/**
 * A {@link Fragment}, which provides some handy methods for creating and storing the presenter
 */
public abstract class BaseFragment<T extends BasePresenter<V>, V> extends Fragment implements PresenterFacade.PresenterCallbacks<T> {
    //region INJECTED VIEWS ------------------------------------------------------------------------

    //endregion

    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    protected T mPresenter;
    //endregion

    //region LIFE CYCLE ----------------------------------------------------------------------------
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new PresenterFacade<>(this).create(getContext(), getLoaderManager(), getPresenterFactory());
    }

    @Override
    public void onPresenterCreated(T presenter) {
        mPresenter = presenter;

        mPresenter.onViewAttached(getPresenterView());
    }

    @Override
    public void onPresenterDestroyed() {
        mPresenter = null;
    }

    @Override
    public void onStart() {
        if (mPresenter != null) {
            mPresenter.onViewAttached(getPresenterView());
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        if (mPresenter != null) {
            mPresenter.onViewDetached();
        }
        super.onStop();
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------

    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    protected abstract PresenterFactory<T> getPresenterFactory();

    protected abstract V getPresenterView();
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}