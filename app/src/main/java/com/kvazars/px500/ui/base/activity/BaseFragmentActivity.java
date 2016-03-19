package com.kvazars.px500.ui.base.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.kvazars.px500.R;
import com.kvazars.px500.ui.base.presenter.BasePresenter;
import com.kvazars.px500.ui.base.presenter.PresenterFacade;
import com.kvazars.px500.ui.base.presenter.PresenterFactory;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An {@link AppCompatActivity}, which provides some handy methods for creating and storing
 * the presenter as well as a single child fragment
 */
public abstract class BaseFragmentActivity<T extends BasePresenter<V>, V> extends AppCompatActivity implements PresenterFacade.PresenterCallbacks<T> {
    //region INJECTED VIEWS ------------------------------------------------------------------------
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;
    @Bind(R.id.content_container)
    protected ViewGroup mContent;
    //endregion

    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    protected T mPresenter;
    //endregion

    //region LIFE CYCLE ----------------------------------------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(getBackgroundDrawable());
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (savedInstanceState == null) {
            Fragment contentFragment = getContentFragment();
            if (contentFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.content_container, contentFragment)
                        .commit();
            }
        }

        new PresenterFacade<>(this).create(this, getSupportLoaderManager(), getPresenterFactory());
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
    protected void onStart() {
        if (mPresenter != null) {
            mPresenter.onViewAttached(getPresenterView());
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.onViewDetached();
        }
        super.onStop();
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------

    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    @Nullable
    protected abstract Fragment getContentFragment();

    @Nullable
    public Drawable getBackgroundDrawable() {
        return null;
    }

    protected abstract PresenterFactory<T> getPresenterFactory();

    protected abstract V getPresenterView();
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}