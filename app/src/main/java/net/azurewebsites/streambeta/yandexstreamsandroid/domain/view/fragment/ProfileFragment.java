package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.ProfilePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.ProfileView;

import java.math.BigDecimal;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class ProfileFragment extends BaseFragment implements ProfileView {

    @BindView(R.id.tv_toolbar_login)
    TextView tv_toolbar_login;
    @BindView(R.id.tv_toolbar_balance)
    TextView tv_toolbar_balance;
    @BindView(R.id.rl_exit_button)
    RelativeLayout rl_exit_button;
    @BindView(R.id.rl_toolbar)
    RelativeLayout rl_toolbar;
    @BindView(R.id.ll_profile_buttons)
    LinearLayout ll_profile_buttons;

    ProfilePresenter presenter;
    private OnFragmentInteractionListener mListener;


    public ProfileFragment() { }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public String getPresenterId() {
        return "profile";
    }

    @Override
    public void onStart() {
        presenter = getPresenter(ProfilePresenter.class);
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
    public void onResume() {
        super.onResume();

        if (!presenter.isAuthorised()) {
            setUnauthorisedMode();
        } else {
            setAuthorisedMode();
            presenter.getAccountInfo();
        }
    }

    @Override
    public View setupView(View view) {
        tv_toolbar_login.setOnClickListener(v -> {
            presenter.getRouter().goToScreen(ScreenTag.AUTH_YANDEX_MONEY);
        });

        rl_exit_button.setOnClickListener(v -> {
            presenter.unauthorise();
        });

        return view;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setUnauthorisedMode() {
        rl_exit_button.setVisibility(GONE);
        tv_toolbar_balance.setVisibility(GONE);
        tv_toolbar_login.setVisibility(VISIBLE);
    }

    @Override
    public void setAuthorisedMode() {
        rl_exit_button.setVisibility(VISIBLE);
        tv_toolbar_balance.setVisibility(VISIBLE);
        tv_toolbar_login.setVisibility(GONE);
    }

    @Override
    public void setBalance(BigDecimal amount) {
        tv_toolbar_balance.setText(String.format(getString(R.string.string_profile_balance), amount));
        //TODO преобразования числа
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void showProgressbar() {
        super.showProgressbar();
        rl_toolbar.setVisibility(GONE);
        ll_profile_buttons.setVisibility(GONE);
    }

    @Override
    public void hideProgressbar() {
        super.hideProgressbar();
        rl_toolbar.setVisibility(VISIBLE);
        ll_profile_buttons.setVisibility(VISIBLE);
    }
}
