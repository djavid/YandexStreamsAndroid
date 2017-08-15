package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationHistoryDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.HistoryPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.adapter.HistoryRecyclerAdapter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.HistoryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class HistoryFragment extends BaseFragment implements HistoryView {

    @BindView(R.id.rv_history_list)
    RecyclerView rv_history_list;

    HistoryPresenter presenter;
    private HistoryRecyclerAdapter recyclerAdapter;
    private OnListFragmentInteractionListener mListener;


    public HistoryFragment() { }

    @Override
    public View setupView(View view) {
        setupRecyclerView();

        return view;
    }

    @Override
    public void onStart() {
        presenter = getPresenter(HistoryPresenter.class);
        presenter.setView(this);
        presenter.setRouter((MainRouter) getActivity());
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.setView(null);
    }

    @Override
    public void loadData() {
        presenter.loadDonationsHistory(
                App.getAppInstance().getPreferencesWrapper().getDeviceId("device_id"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_history_list;
    }

    @Override
    public String getPresenterId() {
        return "history";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DonationHistoryDto item);
    }

    @Override
    public void showProgressbar() {
        super.showProgressbar();
        rv_history_list.setVisibility(GONE);
    }

    @Override
    public void hideProgressbar() {
        super.hideProgressbar();
        rv_history_list.setVisibility(VISIBLE);
    }

    private void setupRecyclerView() {
        recyclerAdapter = new HistoryRecyclerAdapter(getContext(), mListener);
        rv_history_list.setAdapter(recyclerAdapter);
        rv_history_list.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void scrollToPosition(int position) {
        rv_history_list.scrollToPosition(position);
    }

    @Override
    public void appendFeed(List<DonationHistoryDto> feed) {
        recyclerAdapter.appendDataWithNotify(feed);
    }

    @Override
    public void resetFeed() {
        recyclerAdapter.clear();
    }
}
