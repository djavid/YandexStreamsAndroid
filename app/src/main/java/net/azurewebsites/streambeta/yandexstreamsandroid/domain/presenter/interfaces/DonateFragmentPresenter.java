package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import com.yandex.money.api.methods.payment.RequestPayment;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.view.DonateFragmentView;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.StreamSettingsDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.DonateFragmentPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;

/**
 * Created by djavid on 30.07.17.
 */


public interface DonateFragmentPresenter
        extends Presenter<DonateFragmentView, MainRouter, DonateFragmentPresenterInstanceState> {

    void loadStreamSettings(int stream_id);
    void requestPayment();
    void processPayment(RequestPayment requestPayment);
    void sendDonationWithId(DonationDto donationDto);
    boolean isAuthorised();
    boolean checkForm();
    StreamSettingsDto getStreamSettings();
}
