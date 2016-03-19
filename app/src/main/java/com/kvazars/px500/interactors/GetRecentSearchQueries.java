package com.kvazars.px500.interactors;

import com.kvazars.px500.data.storage.DataStorage;

import java.util.List;

import javax.inject.Inject;

/**
 * Returns a list of search queries that were stored in the persistent storage
 * @see DataStorage
 */
public class GetRecentSearchQueries {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final DataStorage mDataStorage;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    @Inject
    public GetRecentSearchQueries(DataStorage dataStorage) {
        mDataStorage = dataStorage;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    public List<String> execute() {
        return mDataStorage.getRecentSearchQueries();
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}