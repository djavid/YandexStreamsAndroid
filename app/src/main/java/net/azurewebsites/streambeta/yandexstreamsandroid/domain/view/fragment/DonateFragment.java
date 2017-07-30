package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.DonateFragmentView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.DonateFragmentPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.DonateFragmentPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.GlideUtils;

import butterknife.BindView;


public class DonateFragment extends BaseFragment implements DonateFragmentView {

    @BindView(R.id.rl_donate_audio)
    RelativeLayout rl_donate_audio;
    @BindView(R.id.rl_donate_text)
    RelativeLayout rl_donate_text;
    @BindView(R.id.btn_text_donate)
    RadioButton btn_text_donate;
    @BindView(R.id.btn_audio_donate)
    RadioButton btn_audio_donate;

    @BindView(R.id.fl_toolbar_donate)
    FrameLayout fl_toolbar_donate;
    @BindView(R.id.rl_form_donate)
    RelativeLayout rl_toolbar_donate;
    @BindView(R.id.btn_submit_donate)
    Button btn_submit_donate;

    @BindView(R.id.tv_donate_toolbar)
    TextView tv_donate_toolbar;
    @BindView(R.id.iv_avatar_donate)
    ImageView iv_avatar_donate;

    @BindView(R.id.et_donate_text)
    EditText et_donate_text;
    @BindView(R.id.tv_donate_hint_text)
    TextView tv_donate_hint_text;

    @BindView(R.id.et_donate_value)
    EditText et_donate_value;
    @BindView(R.id.tv_donate_hint_value)
    TextView tv_donate_hint_value;

    @BindView(R.id.et_donate_nick)
    EditText et_donate_nick;
    @BindView(R.id.tv_donate_hint_nick)
    TextView tv_donate_hint_nick;


    private static final String ARG_STREAM_ID = "id";
    private static final String ARG_STREAM_DESC = "name";
    private int mParamId;
    private String mParamDesc;

    DonateFragmentPresenter presenter;


    @Override
    public void onStart() {
        presenter = getPresenter(DonateFragmentPresenter.class);
        presenter.setView(this);
        presenter.setRouter((MainRouter) getActivity());
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.saveInstanceState(
                new DonateFragmentPresenterInstanceState(et_donate_nick.getText().toString(),
                        et_donate_value.getText().toString(), et_donate_text.getText().toString()));
        presenter.setView(null);
    }

    public DonateFragment() {
        // Required empty public constructor
    }


    public static DonateFragment newInstance(int streamId, String streamName) {
        DonateFragment fragment = new DonateFragment();
        Bundle args = new Bundle();

        args.putLong(ARG_STREAM_ID, streamId);
        args.putString(ARG_STREAM_DESC, streamName);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getInt(ARG_STREAM_ID);
            mParamDesc = getArguments().getString(ARG_STREAM_DESC);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_donate;
    }

    @Override
    public String getPresenterId() {
        return "donate_fragment";
    }

    @Override
    public View setupView(View view) {

        rl_donate_audio.setVisibility(View.GONE);
        rl_donate_text.setVisibility(View.VISIBLE);

        btn_text_donate.setChecked(true);

        btn_text_donate.setOnClickListener(v -> {
            rl_donate_audio.setVisibility(View.GONE);
            rl_donate_text.setVisibility(View.VISIBLE);
        });

        btn_audio_donate.setOnClickListener(v -> {
            rl_donate_text.setVisibility(View.GONE);
            rl_donate_audio.setVisibility(View.VISIBLE);
        });

        et_donate_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    tv_donate_hint_text.setVisibility(View.VISIBLE);
                } else {
                    tv_donate_hint_text.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_donate_nick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    tv_donate_hint_nick.setVisibility(View.VISIBLE);
                } else {
                    tv_donate_hint_nick.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_donate_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    tv_donate_hint_value.setVisibility(View.VISIBLE);
                } else {
                    tv_donate_hint_value.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_submit_donate.setOnClickListener(v -> {
            presenter.getRouter().goToScreen(ScreenTag.AUTH_YANDEX_MONEY);
        });

        return view;
    }

    @Override
    public void loadData() {
        presenter.loadStreamSettings(mParamId);
    }

    @Override
    public void showProgressbar() {
        super.showProgressbar();

        fl_toolbar_donate.setVisibility(View.GONE);
        rl_toolbar_donate.setVisibility(View.GONE);
        btn_submit_donate.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressbar() {
        super.hideProgressbar();

        fl_toolbar_donate.setVisibility(View.VISIBLE);
        rl_toolbar_donate.setVisibility(View.VISIBLE);
        btn_submit_donate.setVisibility(View.VISIBLE);
    }

    @Override
    public void setStreamSettings(StreamSettingsDto streamSettings) {
        tv_donate_toolbar.setText(mParamDesc);
        GlideUtils.loadImageIntoView(getContext(), iv_avatar_donate, streamSettings.getLogo(),
                R.color.colorToolbarBackground);

        if (streamSettings.getVoiceL() == 0) {
            btn_text_donate.setChecked(true);
            btn_audio_donate.setClickable(false);
        }

        if (streamSettings.getTextL() == 0) {
            btn_audio_donate.setChecked(true);
            btn_text_donate.setClickable(false);
        } else {
            et_donate_text.setFilters(new InputFilter[]{
                    new InputFilter.LengthFilter(streamSettings.getTextL())});
        }
    }

    @Override
    public void setNicknameText(String nicknameText) {
        et_donate_nick.setText(nicknameText);
    }

    @Override
    public void setDonateText(String donateText) {
        et_donate_text.setText(donateText);
    }

    @Override
    public void setSumText(String donateSumText) {
        et_donate_value.setText(donateSumText);
    }
}
