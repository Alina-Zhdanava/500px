package com.kvazars.px500.ui.search.fragment;

import java.util.List;

/**
 * Created by Leo on 16.03.2016.
 */
interface PhotoSearchFragmentView {
    void loadImages(List<String> urls);
    void hideProgressBar();
    void showErrorMessage(CharSequence message);
}
