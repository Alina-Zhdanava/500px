package com.kvazars.px500.data.entities;

/**
 * A data layer object, which represents a photo from the 500px webservice
 */
public class PhotoEntity {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private String imageUrl;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public PhotoEntity(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------

    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    public String getImageUrl() {
        return imageUrl;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}