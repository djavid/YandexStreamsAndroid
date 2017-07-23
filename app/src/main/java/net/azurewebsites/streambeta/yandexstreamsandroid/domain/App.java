package net.azurewebsites.streambeta.yandexstreamsandroid.domain;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.ApiInterface;
import net.danlew.android.joda.JodaTimeAndroid;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by tetawex on 12.07.17.
 */

public class App extends Application {
    private static App appInstance;

    private ApiInterface apiInterface;
    private PresenterProvider presenterProvider;
    private SharedPreferences sharedPreferences;

    public static String SHARED_PREFERENCES_CODE="yandexstreamsandroid";
    public static String CLIENT_ID;


    @Override
    public void onCreate() {
        getPresenterProvider();
        getPreferencesWrapper();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        JodaTimeAndroid.init(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("minecraft.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());super.onCreate();

        appInstance = (App)getApplicationContext();
    }

    public static Context getContext() {
        return appInstance.getApplicationContext();
    }

    public static App getAppInstance() {
        return appInstance;
    }

    public PresenterProvider getPresenterProvider() {
        if (presenterProvider == null)
            presenterProvider = new PresenterProvider();
        return presenterProvider;
    }

    public ApiInterface getApiInterface() {
        if (apiInterface == null)
            apiInterface = buildApiInterface();
        return apiInterface;
    }

    public SharedPreferences getRawPreferences() {
        return getSharedPreferences(SHARED_PREFERENCES_CODE, MODE_PRIVATE);
    }

    public PreferencesWrapper getPreferencesWrapper() {
        return new PreferencesWrapper(getRawPreferences());
    }

    private ApiInterface buildApiInterface() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://streambeta.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
