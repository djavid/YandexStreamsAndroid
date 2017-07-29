package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseFragment;

import butterknife.BindView;


public class DonateFragment extends BaseFragment {

    @BindView(R.id.rl_donate_audio)
    RelativeLayout rl_donate_audio;
    @BindView(R.id.rl_donate_text)
    RelativeLayout rl_donate_text;
    @BindView(R.id.btn_text_donate)
    RadioButton btn_text_donate;
    @BindView(R.id.btn_audio_donate)
    RadioButton btn_audio_donate;

    private static final String ARG_STREAM_ID = "id";
    
    private int mParamId;


    public DonateFragment() {
        // Required empty public constructor
    }


    public static DonateFragment newInstance(int streamId) {
        DonateFragment fragment = new DonateFragment();
        Bundle args = new Bundle();

        args.putLong(ARG_STREAM_ID, streamId);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getInt(ARG_STREAM_ID);
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

        return view;
    }

    @Override
    public void loadData() {

    }
}
