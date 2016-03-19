package com.kvazars.px500.ui.main.fragment;

import java.util.List;

/**
 * Created by Leo on 15.03.2016.
 */
interface MainFragmentView {
    void clearSearchQuery();
    void showRecentSearchQueries(List<String> queries);

    MainFragmentEventListener getEventListener();
}
