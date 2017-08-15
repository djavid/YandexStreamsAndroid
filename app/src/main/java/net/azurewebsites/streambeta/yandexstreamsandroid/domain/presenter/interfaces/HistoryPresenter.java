package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.HistoryView;


public interface HistoryPresenter extends Presenter<HistoryView, MainRouter, Object> {
    void loadDonationsHistory(String id);
}
