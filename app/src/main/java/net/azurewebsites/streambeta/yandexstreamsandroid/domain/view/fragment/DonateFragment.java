package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.google.zxing.common.StringUtils;
import com.yandex.money.api.authorization.AuthorizationParameters;
import com.yandex.money.api.methods.InstanceId;
import com.yandex.money.api.methods.payment.RequestPayment;
import com.yandex.money.api.methods.payment.params.P2pTransferParams;
import com.yandex.money.api.methods.payment.params.PaymentParams;
import com.yandex.money.api.net.ApiRequest;
import com.yandex.money.api.net.clients.ApiClient;
import com.yandex.money.api.net.clients.DefaultApiClient;
import com.yandex.money.api.util.Responses;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.DonateFragmentView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.Config;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.DonateFragmentPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.DonateFragmentPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.GlideUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;

import java.math.BigDecimal;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.Single;


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

    @BindView(R.id.back_button_donate)
    ImageView back_button_donate;


    private static final String ARG_STREAM_ID = "id";
    private static final String ARG_STREAM_DESC = "name";
    private static final String ARG_STREAMER_ID = "streamer";
    private int mParamId;
    private String mParamDesc;
    private String mParamStreamer;

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


    public static DonateFragment newInstance(int streamId, String streamName, String streamer_id) {
        DonateFragment fragment = new DonateFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_STREAM_ID, streamId);
        args.putString(ARG_STREAM_DESC, streamName);
        args.putString(ARG_STREAMER_ID, streamer_id);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getInt(ARG_STREAM_ID);
            mParamDesc = getArguments().getString(ARG_STREAM_DESC);
            mParamStreamer = getArguments().getString(ARG_STREAMER_ID);
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


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Выберите способ оплаты:");
        alertDialog.setCancelable(false);


        final String[] items = {"Бансковская карта", "Счет в Я.Деньги"};
        alertDialog.setItems(items, (dialog, which) -> {
        //TODO
        });

        btn_submit_donate.setOnClickListener(v -> {

            if (presenter.isAuthorised()) {
                if (presenter.checkForm()) {
                    presenter.requestPayment();
                } else {
                    if ((getDonateText().isEmpty() || !getDonateText().isEmpty())
                            && getSumText() < presenter.getStreamSettings().getMinSum()) {

                        AlertDialog.Builder alert = createDonateAlertDialog();
                        alert.setTitle("Неправильные данные");
                        alert.setMessage("Минимальная сумма доната - " +
                                presenter.getStreamSettings().getMinSum() + "!");
                        alert.setPositiveButton("Закрыть", (dialog, which) -> dialog.cancel());
                        alert.show();
                    } else {
                        AlertDialog.Builder alert = createDonateAlertDialog();
                        alert.setTitle("Неправильные данные");
                        alert.setMessage("Заполните все поля!");
                        alert.setPositiveButton("Закрыть", (dialog, which) -> dialog.cancel());
                        alert.show();
                    }
                }
            } else {
                presenter.getRouter().goToScreen(ScreenTag.AUTH_YANDEX_MONEY);
            }

        });

        back_button_donate.setOnClickListener(v -> {
            getActivity().onBackPressed();
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

    @Override
    public String getNicknameText() {
        return et_donate_nick.getText().toString();
    }

    @Override
    public String getDonateText() {
        return et_donate_text.getText().toString();
    }

    @Override
    public int getSumText() {
        if (et_donate_value.getText().toString().isEmpty()) return -1;
        else return Integer.parseInt(et_donate_value.getText().toString()) * 100;
    }

    public DonateFragmentPresenter getPresenter() {
        return presenter;
    }

    @Override
    public String getStreamerId() {
        return mParamStreamer;
    }

    @Override
    public AlertDialog.Builder createDonateAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Отправка доната");
        alert.setMessage("Отправить " + (float)(getSumText() / 100) + "руб. на стрим '" + mParamDesc + "'?");

        return alert;
    }
}
