package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.StreamFeedPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.StreamFeedPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.adapter.StreamFeedRecyclerAdapter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.StreamFeedView;

import java.util.List;

import butterknife.BindView;


public class StreamFeedFragment extends BaseFragment implements StreamFeedView {

    @BindView(R.id.et_toolbar)
    EditText etToolbar;
    @BindView(R.id.iv_qr_button)
    ImageView ivQrButton;
    @BindView(R.id.iv_search_icon)
    ImageView ivSearchIcon;
    @BindView(R.id.tv_search_hint)
    TextView tvSearchHint;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.rv_stream_list)
    RecyclerView rvStreamList;
    @BindView(R.id.search_warning)
    LinearLayout search_warning;
    @BindView(R.id.btn_search_login)
    Button btn_search_login;

    StreamFeedPresenter presenter;

    private StreamFeedRecyclerAdapter recyclerAdapter;
    private OnListFragmentInteractionListener mListener;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_search_list;
    }

    @Override
    public String getPresenterId() {
        return "stream_feed";
    }

    @Override
    public void onStart() {
        presenter = getPresenter(StreamFeedPresenter.class);
        presenter.setView(this);
        presenter.setRouter((MainRouter) getActivity());
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.saveInstanceState(
                new StreamFeedPresenterInstanceState(
                        etToolbar.getText().toString(),
                        rvStreamList.getVerticalScrollbarPosition(),
                        recyclerAdapter.getData()
                ));
        presenter.setView(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        fixSearchBar();
    }

    @Override
    public View setupView(View view) {
        setupRecyclerView();
        ivQrButton.setOnClickListener(v -> presenter.onQrButtonPressed());
        setupSearchBar();

        btn_search_login.setOnClickListener(v -> {
            presenter.getRouter().goToScreen(ScreenTag.AUTH_TWITCH);
        });

        return view;
    }

    @Override
    public void loadData() {
        presenter.onQueryStringModified("");
        //TODO load twitch follow
    }

    private void setupRecyclerView() {
        recyclerAdapter = new StreamFeedRecyclerAdapter(getContext(), mListener);
        rvStreamList.setAdapter(recyclerAdapter);
        rvStreamList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupSearchBar() {

        etToolbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    ivSearchIcon.setVisibility(View.VISIBLE);
                    tvSearchHint.setVisibility(View.VISIBLE);
                    tvToolbar.setText(R.string.title_search_toolbar);
                } else {
                    ivSearchIcon.setVisibility(View.INVISIBLE);
                    tvSearchHint.setVisibility(View.INVISIBLE);
                    tvToolbar.setText(R.string.title_search_toolbar_results);
                }
                if (presenter != null)
                    presenter.onQueryStringModified(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void fixSearchBar() {
        if (etToolbar.getText().toString().isEmpty()) {
            ivSearchIcon.setVisibility(View.VISIBLE);
            tvSearchHint.setVisibility(View.VISIBLE);
            tvToolbar.setText(R.string.title_search_toolbar);
            if (!presenter.isAuthorised()) showLoginWarning();
            else hideLoginWarning();
        } else {
            ivSearchIcon.setVisibility(View.INVISIBLE);
            tvSearchHint.setVisibility(View.INVISIBLE);
            tvToolbar.setText(R.string.title_search_toolbar_results);
            hideLoginWarning();

            if (presenter != null)
                presenter.onQueryStringModified(etToolbar.getText().toString());
        }
    }

    @Override
    public void scrollToPosition(int position) {
        rvStreamList.scrollToPosition(position);
    }

    @Override
    public void appendFeed(List<StreamFeedItemModel> feed) {
        recyclerAdapter.appendDataWithNotify(feed);
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
    public void resetFeed() {
        recyclerAdapter.clear();
    }

    public static StreamFeedFragment newInstance() {
        return new StreamFeedFragment();
    }

    @Override
    public void setQueryText(String query) {
        etToolbar.setText(query);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(StreamFeedItemModel item);
    }

    public void showLoginWarning() {
        search_warning.setVisibility(View.VISIBLE);
        rvStreamList.setVisibility(View.GONE);
    }

    public void hideLoginWarning() {
        search_warning.setVisibility(View.GONE);
        rvStreamList.setVisibility(View.VISIBLE);
    }

}
