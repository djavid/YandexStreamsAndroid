package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.dto.DonationHistoryDto;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.MainPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.DonateFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.GamecodeFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.HistoryFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.ProfileFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.StreamFeedFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.MainView;
import net.azurewebsites.streambeta.yandexstreamsandroid.util.RxUtils;

import java.util.UUID;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;


public class MainActivity extends AppCompatActivity implements MainView, MainRouter,
        ProfileFragment.OnFragmentInteractionListener,
        StreamFeedFragment.OnListFragmentInteractionListener,
        HistoryFragment.OnListFragmentInteractionListener{


    private final String TAG = getClass().getSimpleName();
    private final String TAG_SEARCH = "TAG_SEARCH";
    private final String TAG_DONATE = "TAG_DONATE";
    private final String TAG_GAMECODE = "TAG_GAMECODE";
    private final String TAG_PROFILE = "TAG_PROFILE";
    private final String TAG_HISTORY = "TAG_HISTORY";

    static final private int DONATE_FOR_RESULT = 0;

    private FragmentManager fragmentManager;
    Fragment searchFragment, gamecodeFragment, profileFragment, historyFragment;
    private BottomNavigationView navigation;

    private MainPresenter presenter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_search:

                    changeFragment(searchFragment, TAG_SEARCH, true);

                    return true;
                case R.id.navigation_gamecode:

                    changeFragment(gamecodeFragment, TAG_GAMECODE, true);

                    return true;
                case R.id.navigation_profile:

                    changeFragment(profileFragment, TAG_PROFILE, true);

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = StreamFeedFragment.newInstance();
        gamecodeFragment = GamecodeFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();
        historyFragment = new HistoryFragment();

        fragmentManager = getSupportFragmentManager();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_search);

        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = navigation.getMenu().getItem(i);
            SpannableStringBuilder title = new SpannableStringBuilder(menuItem.getTitle());
            CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(
                    TypefaceUtils.load(getAssets(), "minecraft.ttf"));
            title.setSpan(typefaceSpan, 0, title.length(), 0);
            menuItem.setTitle(title);
        }

        if (!App.getAppInstance().getApiClient().isAuthorized()) {
            if (!App.getAppInstance().getPreferencesWrapper().getAuthToken("yandex_money").isEmpty()) {
                App.getAppInstance().getApiClient().setAccessToken(App.getAppInstance()
                        .getPreferencesWrapper().getAuthToken("yandex_money"));
            }
        }

        if (App.getAppInstance().getPreferencesWrapper().getDeviceId("device_id").isEmpty()) {
            showProgressbar();

            String uniqueID = UUID.randomUUID().toString();
            App.getAppInstance().getApiInterface().createUser(uniqueID)
                    .doOnError(Throwable::printStackTrace)
                    .compose(RxUtils.applyCompletableSchedulers())
                    .subscribe(() -> {
                        hideProgressbar();
                        App.getAppInstance().getPreferencesWrapper().setDeviceId("device_id", uniqueID);
                        System.out.println(App.getAppInstance().getPreferencesWrapper().getDeviceId("device_id"));
                        //TODO show alert about success
                    }, exception -> {
                        hideProgressbar();
                    });

        }
    }


    public void changeFragment(Fragment fragment, String tag, boolean addBackStack) {

        //KeyboardUtils.hideKeyboard(this); TODO hide keyboard

        Fragment existFragment = fragmentManager.findFragmentByTag(tag);

        if (existFragment == null) {
            // fragment not in back stack, create it.
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content, fragment, tag);
            if (addBackStack) ft.addToBackStack(tag);
            ft.commit();
            Log.w(TAG, tag + " added to the backstack");
        } else {
            //if (existFragment.isAdded()) return;
            // fragment in back stack, call it back.
            FragmentTransaction ft = fragmentManager.beginTransaction();

            //fragmentManager.popBackStackImmediate(tag, 0);
            ft.replace(R.id.content, existFragment, tag);
            if (addBackStack) {
                fragmentManager.popBackStack(tag, 0);
                //ft.addToBackStack(tag);
            }
            ft.commit();
            Log.w(TAG, tag + " fragment returned back from backstack");
        }
    }

    @Override
    public void onBackPressed() {

        int entryCount = fragmentManager.getBackStackEntryCount();

        // if first fragment is not on screen, just pop back to the previous fragment.
        if (entryCount > 1) {

            fragmentManager.popBackStack();

            String tag = fragmentManager.getBackStackEntryAt(entryCount - 2).getName();

            switch (tag) {
                case TAG_SEARCH:
                case TAG_DONATE:
                    navigation.setSelectedItemId(R.id.navigation_search);
                    break;
                case TAG_HISTORY:
                case TAG_PROFILE:
                    navigation.setSelectedItemId(R.id.navigation_profile);
                    break;
                case TAG_GAMECODE:
                    navigation.setSelectedItemId(R.id.navigation_gamecode);
                    break;
            }

            return;
        }

        // if first fragment is showing, then finish the activity.
        finish();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void showError(int errorId) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void goBack() {

    }

    @Override
    public void goToScreen(ScreenTag screenTag) {
        switch (screenTag){
            case AUTH_TWITCH: {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("auth_type", "twitch");
                startActivity(intent);
                break;
            }
            case AUTH_YANDEX_MONEY: {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("auth_type", "yandex_money");
                //startActivity(intent);
                startActivityForResult(intent, DONATE_FOR_RESULT);
                break;
            }
            case QR_SCANNER: {
                changeFragment(gamecodeFragment, TAG_GAMECODE, true);
                break;
            }
            case HISTORY_LIST: {
                changeFragment(historyFragment, TAG_HISTORY, true);
                break;
            }
        }
    }

    @Override
    public ScreenTag getCurrentScreen() {
        return null;
    }

    @Override
    public void goToStreamPage(long streamId, String streamDesc, String streamerId) {
        Fragment donateFragment = DonateFragment.newInstance(streamId, streamDesc, streamerId);
        changeFragment(donateFragment, TAG_DONATE, true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(StreamFeedItemModel item) {
        if (!isYandexAuthorised()) {
            goToScreen(ScreenTag.AUTH_YANDEX_MONEY);
        } else {
            goToStreamPage(item.getId(), item.getDescription(), item.getStreamer_id());
        }
    }

    @Override
    public void onListFragmentInteraction(DonationHistoryDto item) {
        goToScreen(ScreenTag.HISTORY_LIST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            App.getAppInstance().getApiClient().setAccessToken(App.getAppInstance()
                    .getPreferencesWrapper().getAuthToken("yandex_money"));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public boolean isYandexAuthorised() {
        return App.getAppInstance().getApiClient().isAuthorized();
    }
}
