package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.GamecodeFragmentInteractor;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.GamecodeFragmentUseCase;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.GamecodeFragmentPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.GamecodeFragmentView;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.ThrowableToStringIdConverter;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by djavid on 03.08.17.
 */


public class GamecodeFragmentPresenterImpl
        extends BasePresenter<GamecodeFragmentView, MainRouter, Object>
        implements GamecodeFragmentPresenter {

    private Disposable disposable = Disposables.empty();
    private GamecodeFragmentInteractor gamecodeFragmentInteractor;


    public GamecodeFragmentPresenterImpl() {
        gamecodeFragmentInteractor = new GamecodeFragmentUseCase();
    }

    @Override
    public String getId() {
        return "gamecode_fragment";
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void saveInstanceState(Object instanceState) {

    }

    @Override
    public void loadStreamByUrl(String url) {
        if (getView() != null) {
            getView().showProgressbar();
        }

        if (!disposable.isDisposed())
            disposable.dispose();

        disposable = gamecodeFragmentInteractor.getStreamByUrl(url)
                .compose(RxUtils.applySingleSchedulers())
                .retry(2l)
                .subscribe(model -> {
                            if (getView() != null) {
                                getView().hideProgressbar();
                                getView().openDonateFragment(model.getStreamId(),
                                        model.getName(), model.getStreamerId());
                            }
                        }, error -> {
                            if (getView() != null) {
                                getView().showError(ThrowableToStringIdConverter.convert(error));
                                getView().hideProgressbar();
                            }
                        }
                );
    }
}
