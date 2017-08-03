package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.BaseFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.GamecodeFragmentPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.activity.MainActivity;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.GamecodeFragmentView;

import butterknife.BindView;


public class GamecodeFragment extends BaseFragment
        implements QRCodeReaderView.OnQRCodeReadListener, GamecodeFragmentView {


    QRCodeReaderView qrCodeReaderView;
    GamecodeFragmentPresenter presenter;
    private ViewGroup mainLayout;


    public GamecodeFragment() { }

    public static GamecodeFragment newInstance() {
        GamecodeFragment fragment = new GamecodeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gamecode;
    }

    @Override
    public String getPresenterId() {
        return "gamecode_fragment";
    }

    @Override
    public void onStart() {
        presenter = getPresenter(GamecodeFragmentPresenter.class);
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
    public View setupView(View view) {
        mainLayout = (ViewGroup) view.findViewById(R.id.gamecode_main_layout);

        view.findViewById(R.id.btn_allow_permission).setOnClickListener(v -> {
            requestPermissions();
        });

        if (!isCameraPermissionGranted()) {
            requestPermissions();
        } else {
            initQrCodeReaderView();
        }

        return view;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        initQrCodeReaderView();
                    }
                });
    }

    private void initQrCodeReaderView() {

        mainLayout.findViewById(R.id.gamecode_permission).setVisibility(View.GONE);

        qrCodeReaderView = new QRCodeReaderView(getContext());
        qrCodeReaderView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        qrCodeReaderView.setId(R.id.qrdecoderview);
        mainLayout.addView(qrCodeReaderView);


        qrCodeReaderView = (QRCodeReaderView) mainLayout.findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

        qrCodeReaderView.startCamera();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isCameraPermissionGranted()) {
            qrCodeReaderView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (isCameraPermissionGranted()) {
            qrCodeReaderView.stopCamera();
        }
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        if (URLUtil.isValidUrl(text) && text.contains("twitch")) {
            System.out.println(text);

            presenter.loadStreamByUrl(text);
        }
    }

    @Override
    public void openDonateFragment(int streamId, String streamName, String streamer_id) {
        Fragment donateFragment = DonateFragment.newInstance(streamId, streamName, streamer_id);
        ((MainActivity) getActivity()).changeFragment(donateFragment, "TAG_DONATE", true);
    }

    @Override
    public void showProgressbar() {
        super.showProgressbar();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgressbar() {
        super.hideProgressbar();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }
}
