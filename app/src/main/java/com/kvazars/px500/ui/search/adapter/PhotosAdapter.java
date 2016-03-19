package com.kvazars.px500.ui.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kvazars.px500.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leo on 15.03.2016.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoItemViewHolder> {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final List<String> mData;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public PhotosAdapter(List<String> data) {
        mData = data;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public PhotoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_list, parent, false);
        return new PhotoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoItemViewHolder holder, int position) {
        String imageUrl = mData.get(position);
        ImageView imageView = holder.mPhoto;
        Picasso
                .with(imageView.getContext())
                .load(imageUrl)
                .fit()
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<String> urls) {
        mData.clear();
        mData.addAll(urls);
        notifyDataSetChanged();
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    public List<String> getData() {
        return mData;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    protected static class PhotoItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.photo)
        public ImageView mPhoto;

        public PhotoItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //endregion
}