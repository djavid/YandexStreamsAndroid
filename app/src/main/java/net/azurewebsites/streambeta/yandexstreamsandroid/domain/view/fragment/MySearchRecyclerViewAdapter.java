package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.SearchFragment.OnListFragmentInteractionListener;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.dummy.DummyContent.DummyItem;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySearchRecyclerViewAdapter extends RecyclerView.Adapter<MySearchRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MySearchRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_search, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mName.setText(mValues.get(position).id);
        //holder.mDescription.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mAvatar;
        public final TextView mName;
        public final TextView mDescription;
        public final TextView mViews;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAvatar = (ImageView) view.findViewById(R.id.avatar);
            mName = (TextView) view.findViewById(R.id.name);
            mDescription = (TextView) view.findViewById(R.id.description);
            mViews = (TextView) view.findViewById(R.id.views);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescription.getText() + "'";
        }
    }
}
