package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.SearchFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MySearchRecyclerViewAdapter extends RecyclerView.Adapter<MySearchRecyclerViewAdapter.ViewHolder> {

    private final List<StreamModel> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MySearchRecyclerViewAdapter(List<StreamModel> items, OnListFragmentInteractionListener listener) {
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
        StreamModel stream = mValues.get(position);
        holder.mName.setText(stream.getName());
        holder.mDescription.setText(stream.getDescription());
        holder.mViews.setText(stream.getAudience());
        holder.mItem = stream;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
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
        public StreamModel mItem;
        public final ImageView mAvatar;
        public final TextView mName;
        public final TextView mDescription;
        public final TextView mViews;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAvatar = (ImageView) view.findViewById(R.id.avatar);
            mName = (TextView) view.findViewById(R.id.name);
            mDescription = (TextView) view.findViewById(R.id.description);
            mViews = (TextView) view.findViewById(R.id.views);
            mItem = new StreamModel();
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mName=" + mName +
                    ", mDescription=" + mDescription +
                    ", mViews=" + mViews +
                    '}';
        }
    }
}
