package com.kvazars.px500.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kvazars.px500.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leo on 15.03.2016.
 */
public class RecentQueriesAdapter extends RecyclerView.Adapter<RecentQueriesAdapter.QueryViewHolder> {
    //region CONSTANTS -----------------------------------------------------------------------------

    //endregion

    //region CLASS VARIABLES -----------------------------------------------------------------------
    private final List<String> mData;
    private final View.OnClickListener mListener;
    //endregion

    //region CONSTRUCTOR ---------------------------------------------------------------------------
    public RecentQueriesAdapter(List<String> data, View.OnClickListener listener) {
        mData = data;
        mListener = listener;
    }
    //endregion

    //region LOCAL METHODS -------------------------------------------------------------------------
    @Override
    public QueryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_queries_list, parent, false);
        final QueryViewHolder queryViewHolder = new QueryViewHolder(view);
        view.setOnClickListener(mListener);
        return queryViewHolder;
    }

    @Override
    public void onBindViewHolder(QueryViewHolder holder, int position) {
        String query = mData.get(position);
        holder.mQueryText.setText(query);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<String> queries) {
        mData.clear();
        mData.addAll(queries);
        notifyDataSetChanged();
    }
    //endregion

    //region ACCESSORS -----------------------------------------------------------------------------
    public List<String> getData() {
        return mData;
    }
    //endregion

    //region INNER CLASSES -------------------------------------------------------------------------
    protected static class QueryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.query_text)
        public TextView mQueryText;

        public QueryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //endregion
}