package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;


public class DonateFragment extends Fragment {

    private static final String ARG_ID = "id";
    private static final String ARG_NAME = "name";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_IMAGE_URL = "image_url";

    private long mParamId;
    private String mParamName;
    private String mParamDescription;
    private String mParamImageUrl;

    private OnFragmentInteractionListener mListener;

    public DonateFragment() {
        // Required empty public constructor
    }


    public static DonateFragment newInstance(int streamId) {
        DonateFragment fragment = new DonateFragment();
        Bundle args = new Bundle();

            args.putLong(ARG_ID, 1);
            args.putString(ARG_NAME, "oh");
            args.putString(ARG_DESCRIPTION, "i'll fix it in a matter of a day");
            args.putString(ARG_IMAGE_URL, "no image today, sry");

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getLong(ARG_ID);
            mParamName = getArguments().getString(ARG_NAME);
            mParamDescription = getArguments().getString(ARG_DESCRIPTION);
            mParamImageUrl = getArguments().getString(ARG_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_donate, container, false);

        view.findViewById(R.id.rl_donate_audio).setVisibility(View.GONE);
        view.findViewById(R.id.rl_donate_text).setVisibility(View.VISIBLE);

        final RadioButton textButton = (RadioButton) view.findViewById(R.id.btn_text_donate);
        final RadioButton audioButton = (RadioButton) view.findViewById(R.id.btn_audio_donate);

        textButton.setChecked(true);

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.rl_donate_audio).setVisibility(View.GONE);
                view.findViewById(R.id.rl_donate_text).setVisibility(View.VISIBLE);
            }
        });

        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.rl_donate_text).setVisibility(View.GONE);
                view.findViewById(R.id.rl_donate_audio).setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
}
