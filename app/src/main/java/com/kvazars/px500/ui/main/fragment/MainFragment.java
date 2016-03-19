package com.kvazars.px500.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kvazars.px500.PhotosApplication;
import com.kvazars.px500.R;
import com.kvazars.px500.ui.base.fragment.BaseFragment;
import com.kvazars.px500.ui.base.presenter.PresenterFactory;
import com.kvazars.px500.ui.main.adapter.RecentQueriesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leo on 14.03.2016.
 */
public class MainFragment extends BaseFragment<MainFragmentPresenter, MainFragmentView> implements MainFragmentView {
    //region INJECTED VIEWS ------------------------------------------------------------------------
    @Bind(R.id.recent_queries_list)
    protected RecyclerView mRecentQueriesRecyclerView;
    @Bind(R.id.search_query_text)
    protected TextView mSearchQueryText;
    //endregion

    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private RecentQueriesAdapter mRecentQueriesAdapter;
    private MainFragmentEventListener mEventListener;
    //endregion

    //region LIFE CYCLE ----------------------------------------------------------------------------
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        initQueriesRecyclerView();

        initQueryEditText();

        return view;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    private void initQueriesRecyclerView() {
        mRecentQueriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecentQueriesAdapter = new RecentQueriesAdapter(
                new ArrayList<>(),
                v -> {
                    int position = mRecentQueriesRecyclerView.getChildAdapterPosition(v);
                    mPresenter.onRecentSearchQueryClick(mRecentQueriesAdapter.getData().get(position));
                });
        mRecentQueriesRecyclerView.setAdapter(mRecentQueriesAdapter);
    }

    private void initQueryEditText() {
        mSearchQueryText.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        mSearchQueryText.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;

            if (actionId == KeyEvent.KEYCODE_CALL) {
                handled = true;
                mPresenter.doSearch(v.getText().toString());
            }
            return handled;
        });
    }

    @Override
    public void showRecentSearchQueries(List<String> queries) {
        mRecentQueriesAdapter.setData(queries);
    }

    public void setEventListener(MainFragmentEventListener eventListener) {
        mEventListener = eventListener;
    }

    @Override
    public MainFragmentEventListener getEventListener() {
        return mEventListener;
    }

    @OnClick(R.id.button_do_search)
    public void onDoSearchClick() {
        mPresenter.doSearch(mSearchQueryText.getText().toString());
    }

    @OnClick(R.id.button_clear_recent)
    public void onClearRecentClick() {
        mPresenter.clearRecent();
    }

    @Override
    public void clearSearchQuery() {
        mSearchQueryText.setText("");
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    @Override
    protected PresenterFactory<MainFragmentPresenter> getPresenterFactory() {
        return applicationContext -> {
            PhotosApplication application = (PhotosApplication) applicationContext;
            MainFragmentPresenter mainFragmentPresenter = new MainFragmentPresenter();
            application.getAppComponent().inject(mainFragmentPresenter);
            return mainFragmentPresenter;
        };
    }

    @Override
    protected MainFragmentView getPresenterView() {
        return this;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------

    //endregion
}