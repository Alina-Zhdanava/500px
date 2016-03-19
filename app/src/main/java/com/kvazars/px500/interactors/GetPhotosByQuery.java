package com.kvazars.px500.interactors;

import com.kvazars.px500.data.entities.PhotoEntity;
import com.kvazars.px500.data.net.ApiService;
import com.kvazars.px500.data.net.models.PhotoListModel;
import com.kvazars.px500.data.storage.DataStorage;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Performs an http request to 500px webservice and grabs a list of photos
 * according to specified search term
 * @see ApiService
 */
public class GetPhotosByQuery {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final ApiService mService;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    @Inject
    public GetPhotosByQuery(ApiService service) {
        mService = service;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    public Observable<PhotoEntity> search(String term) {
        return mService.search(term)
                .subscribeOn(Schedulers.io())
                .flatMapIterable(PhotoListModel::getPhotos)
                .map(photoModel -> new PhotoEntity(photoModel.getImageUrl()))
                .observeOn(AndroidSchedulers.mainThread());
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}