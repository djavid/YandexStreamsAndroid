package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.HistoryFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MyHistoryRecyclerViewAdapter extends RecyclerView.Adapter<MyHistoryRecyclerViewAdapter.ViewHolder> {

    private final List<DonationModel> mValues;
    private final OnListFragmentInteractionListener mListener;


    public MyHistoryRecyclerViewAdapter(List<DonationModel> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mDonation = mValues.get(position);
        //holder.mNameView.setText(mValues.get(position).); TODO
        holder.mAmountView.setText(mValues.get(position).getAmount());
        holder.mDateView.setText(mValues.get(position).getDatetime().toString());


        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mDonation);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public DonationModel mDonation;
        public final TextView mNameView;
        public final TextView mAmountView;
        public final TextView mDateView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.history_stream_name);
            mAmountView = (TextView) view.findViewById(R.id.history_stream_amount);
            mDateView = (TextView) view.findViewById(R.id.history_stream_date);

        }

    }
}
