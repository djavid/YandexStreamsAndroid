package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.HistoryInteractor;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.HistoryUseCase;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.HistoryPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.HistoryView;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.ThrowableToStringIdConverter;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;


public class HistoryPresenterImpl extends BasePresenter<HistoryView, MainRouter, Object>
        implements HistoryPresenter{

    private Disposable disposable = Disposables.empty();
    private HistoryInteractor historyInteractor;


    public HistoryPresenterImpl() {
        historyInteractor = new HistoryUseCase();
    }

    @Override
    public String getId() {
        return "history";
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (!disposable.isDisposed()) disposable.dispose();
    }

    @Override
    public void saveInstanceState(Object instanceState) {

    }

    @Override
    public void loadDonationsHistory(String id) {
        if (getView() != null) getView().showProgressbar();

        disposable = historyInteractor.getDonationsHistory(id)
                .compose(RxUtils.applySingleSchedulers())
                .retry(2L)
                .subscribe(list -> {
                    if (getView() != null) {
                        getView().appendFeed(list);
                        getView().hideProgressbar();
                    } else {
                        //TODO save instance state
                    }
                }, error -> {
                    if (getView() != null)
                        getView().hideProgressbar();
                        error.printStackTrace();
                        getView().showError(ThrowableToStringIdConverter.convert(error));
                });

    }
}
