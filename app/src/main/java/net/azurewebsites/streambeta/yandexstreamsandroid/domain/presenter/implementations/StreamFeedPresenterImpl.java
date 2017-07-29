package net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.BasePresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.StreamFeedInteractor;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.StreamFeedUseCase;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.StreamFeedPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.StreamFeedPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.StreamFeedView;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.ThrowableToStringIdConverter;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Tetawex on 29.07.2017.
 */

public class StreamFeedPresenterImpl
        extends BasePresenter<StreamFeedView, MainRouter, StreamFeedPresenterInstanceState>
        implements StreamFeedPresenter {

    private Disposable disposable = Disposables.empty();

    private StreamFeedInteractor streamFeedInteractor;
    private String query = "";
    private int offset = 0;

    public StreamFeedPresenterImpl() {
        streamFeedInteractor = new StreamFeedUseCase();
    }

    @Override
    public String getId() {
        return "stream_feed";
    }

    @Override
    public void onStart() {
        if (getInstanceState() != null) {
            getView().appendFeed(getInstanceState().getData());
            getView().setQueryText(getInstanceState().getQuery());
            getView().scrollToPosition(getInstanceState().getScrollPosition());
        }
    }

    @Override
    public void onStop() {
    }

    @Override
    public void saveInstanceState(StreamFeedPresenterInstanceState instanceState) {
        setInstanceState(instanceState);
    }

    @Override
    public void onQueryStringModified(String query) {
        if (getView() != null) {
            this.query = query;
            getView().showProgressbar();
            getView().resetFeed();
            if (!disposable.isDisposed())
                disposable.dispose();

            if (query == null || query.isEmpty()) {
                loadGenericFeed();
            } else
                loadFeedWithQuery(query);
        }
    }

    private void loadGenericFeed() {
        loadFeedWithQuery("");//temporary
    }

    private void loadFeedWithQuery(String query) {
        disposable = streamFeedInteractor.getStreamFeedForQuery(query, offset)
                .compose(RxUtils.applySingleSchedulers())
                .retry(2L)
                .subscribe((list) -> {
                            if (getView() != null) {
                                getView().appendFeed(list);
                                getView().hideProgressbar();
                            } else {
                                getInstanceState().getData().addAll(list);
                            }
                        },
                        (throwable) -> {
                            getView().showError(ThrowableToStringIdConverter.convert(throwable));
                        });
    }

    @Override
    public void onQrButtonPressed() {
        getRouter().goToScreen(ScreenTag.QR_SCANNER);
    }
}
