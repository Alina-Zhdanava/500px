package com.kvazars.px500.ui.base.presenter;

/**
 * A base implementation of a presenter
 */
public interface BasePresenter<V> {
    void onViewAttached(V view);
    void onViewDetached();
    void onDestroyed();
}
