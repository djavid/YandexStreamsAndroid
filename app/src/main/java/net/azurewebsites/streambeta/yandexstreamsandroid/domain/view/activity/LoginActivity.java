package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.wv_auth_page)
    WebView webView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);


    }
    @Override
    public void onStart(){
        super.onStart();
        webView.loadUrl("https://api.twitch.tv/kraken/oauth2/authorize?client_id=kfst7tyjg0jf3qx8hvc1y29ccf89kp&redirect_uri=https://bkjjipopfjknbeabnlelfhplklgjfcaf.chromiumapp.org&response_type=code&scope=user_read");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }
    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}
