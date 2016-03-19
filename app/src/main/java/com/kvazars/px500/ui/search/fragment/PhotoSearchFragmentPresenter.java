package com.kvazars.px500.ui.search.fragment;

import com.kvazars.px500.data.entities.PhotoEntity;
import com.kvazars.px500.interactors.GetPhotosByQuery;
import com.kvazars.px500.ui.base.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Leo on 16.03.2016.
 */
public class PhotoSearchFragmentPresenter implements BasePresenter<PhotoSearchFragmentView> {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final String mSearchQuery;
    private List<String> mPhotoUrls;
    private PhotoSearchFragmentView mView;

    @Inject
    protected GetPhotosByQuery mGetPhotosByQuery;
    private Subscription mSubscription;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public PhotoSearchFragmentPresenter(String searchQuery) {
        mSearchQuery = searchQuery;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public void onViewAttached(PhotoSearchFragmentView view) {
        mView = view;
        if (mPhotoUrls == null) {
            mSubscription = mGetPhotosByQuery.search(mSearchQuery)
                    .map(PhotoEntity::getImageUrl)
                    .toList()
                    .onErrorReturn(e -> new ArrayList<>())
                    .subscribe(strings -> {
                        mPhotoUrls = strings;
                        showImages();
                    });
        } else {
            showImages();
        }
    }

    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onDestroyed() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void showImages() {
        if (mView != null) {
            if (mPhotoUrls.isEmpty()) {
                mView.showErrorMessage("No images");
            } else {
                mView.loadImages(mPhotoUrls);
            }
            mView.hideProgressBar();
        }
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}