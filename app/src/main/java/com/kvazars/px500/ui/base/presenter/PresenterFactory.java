package com.kvazars.px500.ui.base.presenter;

import android.content.Context;

/**
 * A simple factory used to bring some flexibility into the process of presenter creation
 */
public interface PresenterFactory<T> {
    T create(Context applicationContext);
}
