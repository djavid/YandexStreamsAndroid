package net.azurewebsites.streambeta.yandexstreamsandroid.core;

/**
 * Created by tetawex on 11.07.17.
 */

public abstract class BasePresenter<V extends View, R extends Router, I> implements Presenter<V, R, I>{
    private V view;
    private R router;
    private I instanceState;
    private String id;

    public BasePresenter(V view, R router) {
        this.view = view;
        this.router = router;
    }

    public BasePresenter() {
    }

    public abstract void onStart();

    public abstract void onStop();

    public abstract void saveInstanceState(I instanceState);

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public R getRouter() {
        return router;
    }

    public void setRouter(R router) {
        this.router = router;
    }

    protected I getInstanceState(){
        return instanceState;
    }
    protected void setInstanceState(I instanceState){
        this.instanceState=instanceState;
    }
}
