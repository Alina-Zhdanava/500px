package com.kvazars.px500.interactors;

import com.kvazars.px500.data.storage.DataStorage;

import javax.inject.Inject;

/**
 * Removes all saved search queries from the persistent storage
 * @see DataStorage
 */
public class ClearRecentSearchQueries {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final DataStorage mDataStorage;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    @Inject
    public ClearRecentSearchQueries(DataStorage dataStorage) {
        mDataStorage = dataStorage;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    public void execute() {
        mDataStorage.clearRecentSearchQueries();
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}