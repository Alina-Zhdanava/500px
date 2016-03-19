package com.kvazars.px500.ui.search.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kvazars.px500.PhotosApplication;
import com.kvazars.px500.R;
import com.kvazars.px500.ui.base.fragment.BaseFragment;
import com.kvazars.px500.ui.base.presenter.PresenterFactory;
import com.kvazars.px500.ui.search.adapter.PhotosAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leo on 16.03.2016.
 */
public class PhotoPhotoSearchFragment extends BaseFragment<PhotoSearchFragmentPresenter, PhotoSearchFragmentView> implements PhotoSearchFragmentView {
    //region INJECTED VIEWS ------------------------------------------------------------------------
    @Bind(R.id.progress_bar)
    protected View mProgressBar;
    @Bind(R.id.error_text)
    protected TextView mErrorText;
    @Bind(R.id.photos_list)
    protected RecyclerView mPhotosRecyclerView;
    //endregion

    //region CONSTANTS -----------------------------------------------------------------------------
    private static final String ARG_SEARCH_QUERY = "searchQuery";
    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private PhotosAdapter mPhotosAdapter;
    //endregion

    //region LIFE CYCLE ----------------------------------------------------------------------------
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        initPhotosRecyclerView();

        return view;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    public static PhotoPhotoSearchFragment getInstance(String searchQuery) {
        PhotoPhotoSearchFragment photoSearchFragment = new PhotoPhotoSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SEARCH_QUERY, searchQuery);
        photoSearchFragment.setArguments(bundle);
        return photoSearchFragment;
    }

    private void initPhotosRecyclerView() {
        mPhotosRecyclerView.setLayoutManager(new PreCachingGridLayoutManager(getActivity()));
        mPhotosAdapter = new PhotosAdapter(new ArrayList<>());
        mPhotosRecyclerView.setAdapter(mPhotosAdapter);
    }

    @Override
    public void loadImages(List<String> urls) {
        mPhotosAdapter.setData(urls);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(CharSequence message) {
        mErrorText.setText(message);
        mErrorText.setVisibility(View.VISIBLE);
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    @Override
    protected PresenterFactory<PhotoSearchFragmentPresenter> getPresenterFactory() {
        return applicationContext -> {
            Bundle arguments = getArguments();
            if (arguments != null && arguments.containsKey(ARG_SEARCH_QUERY)) {
                PhotosApplication application = (PhotosApplication) applicationContext;
                PhotoSearchFragmentPresenter presenter = new PhotoSearchFragmentPresenter(arguments.getString(ARG_SEARCH_QUERY));
                application.getAppComponent().inject(presenter);

                return presenter;
            }
            throw new IllegalStateException("No search query specified");
        };
    }

    @Override
    protected PhotoSearchFragmentView getPresenterView() {
        return this;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    private static class PreCachingGridLayoutManager extends GridLayoutManager {
        public PreCachingGridLayoutManager(Context context) {
            super(context, 2);
        }

        @Override
        protected int getExtraLayoutSpace(RecyclerView.State state) {
            return getHeight();
        }
    }
    //endregion
}