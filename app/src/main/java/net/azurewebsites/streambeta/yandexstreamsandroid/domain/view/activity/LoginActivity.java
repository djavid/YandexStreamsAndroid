package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.Router;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.Config;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.PresenterProvider;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.implementations.LoginPresenterImpl;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.instancestate.LoginPresenterInstanceState;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.LoginPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.LoginView;

import java.math.BigDecimal;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.yandex.money.android.PaymentActivity;


public class LoginActivity extends AppCompatActivity implements LoginView, Router {

    @BindView(R.id.wv_auth_page)
    WebView webView;
    @BindView(R.id.progressbar)
    android.view.View progressbar;

    private LoginPresenter presenter;

    private WebViewClient webViewClient;

    private Unbinder unbinder;
    private boolean hasUrlLoaded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);

        showProgressbar();
        setupWebView();
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart LoginActivity");
        presenter = App.getAppInstance().getPresenterProvider().getPresenter("login", LoginPresenter.class);
        presenter.setView(this);
        presenter.setRouter(this);
        presenter.setAuthType(getIntent().getStringExtra("auth_type"));
        presenter.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        System.out.println("onSaveInstanceState - " + webView.getUrl());
        presenter.saveInstanceState(new LoginPresenterInstanceState(webView.getUrl()));
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setupWebView() {
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        WebSettings settings = webView.getSettings();
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webViewClient =  new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("url", url);
                String code = "";
                hasUrlLoaded = true;

                if (url.contains("?code=")) {
                    if (url.contains("&scope")) {
                        code = url.substring(url.indexOf("?code=") + 6, url.indexOf("&"));
                    } else {
                        code = url.substring(url.indexOf("?code=") + 6, url.length());
                    }

                    presenter.onInitialCodeReceived(code);
                    return true;
                }
                return false;
            }
        };

        webView.setWebViewClient(webViewClient);

        hideProgressbar();
    }

    @Override
    public void showProgressbar() {
        progressbar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressbar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(int errorId) {
        Toast.makeText(this, getString(errorId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dispose() {
        finish();
        App.getAppInstance().getPresenterProvider().removePresenter("login");
    }

    @Override
    public void goBack() {

    }

    @Override
    public void setAuthResult(int result) {
        setResult(result);
        //finish()
    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void postUrl(String url, byte[] postData) {
        webView.postUrl(url, postData);
    }

    @Override
    public boolean hasUrlLoaded() {
        return hasUrlLoaded;
    }

}
