package com.kvazars.px500.data.storage;

import java.util.List;

/**
 * An abstraction, which provides methods to store data
 * in some kind of persistent storage like database or shared preferences
 */
public interface DataStorage {
    List<String> getRecentSearchQueries();
    void saveSearchQueryToRecent(String query);
    void clearRecentSearchQueries();
}
