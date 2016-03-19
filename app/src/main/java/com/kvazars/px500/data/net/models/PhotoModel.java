package com.kvazars.px500.data.net.models;

import com.google.gson.annotations.SerializedName;

/**
 * An internal 500px service json model, which represents a photo
 */
public class PhotoModel {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    @SerializedName("image_url")
    private String mImageUrl;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------

    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------

    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    public String getImageUrl() {
        return mImageUrl;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}