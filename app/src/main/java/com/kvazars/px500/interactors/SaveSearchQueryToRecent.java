package com.kvazars.px500.interactors;

import com.kvazars.px500.data.storage.DataStorage;

import javax.inject.Inject;

/**
 * Stores specified search query in the persistent storage
 * @see DataStorage
 */
public class SaveSearchQueryToRecent {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final DataStorage mDataStorage;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    @Inject
    public SaveSearchQueryToRecent(DataStorage dataStorage) {
        mDataStorage = dataStorage;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    public void execute(String query) {
        mDataStorage.saveSearchQueryToRecent(query);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------

    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}