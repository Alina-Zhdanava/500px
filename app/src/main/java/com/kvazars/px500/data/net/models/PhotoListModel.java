package com.kvazars.px500.data.net.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * An internal 500px service json model, which represents a list of photos
 */
public class PhotoListModel {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    @SerializedName("photos")
    private List<PhotoModel> mPhotos;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------

    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    public List<PhotoModel> getPhotos() {
        return mPhotos;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}