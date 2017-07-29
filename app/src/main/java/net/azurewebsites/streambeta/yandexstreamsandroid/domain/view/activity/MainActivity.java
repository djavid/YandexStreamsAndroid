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

import com.yandex.money.api.methods.payment.params.P2pTransferParams;
import com.yandex.money.api.methods.payment.params.PaymentParams;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.Config;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.interactor.mapped.StreamFeedItemModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.MainPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.DonateFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.GamecodeFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.ProfileFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.StreamFeedFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.MainView;

import java.math.BigDecimal;

import ru.yandex.money.android.PaymentActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;


public class MainActivity extends AppCompatActivity implements MainView, MainRouter,
        GamecodeFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener,
        StreamFeedFragment.OnListFragmentInteractionListener {


    private final String TAG = getClass().getSimpleName();
    private final String TAG_SEARCH = "TAG_SEARCH";
    private final String TAG_DONATE = "TAG_DONATE";
    private final String TAG_GAMECODE = "TAG_GAMECODE";
    private final String TAG_PROFILE = "TAG_PROFILE";

    private FragmentManager fragmentManager;
    Fragment searchFragment, gamecodeFragment, profileFragment;
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

        //goToScreen(ScreenTag.AUTH_TWITCH);
    }


    private void changeFragment(Fragment fragment, String tag, boolean addBackStack) {

        //KeyboardUtils.hideKeyboard(this); TODO

        Fragment existFragment = fragmentManager.findFragmentByTag(tag);

        if (existFragment == null) {
            // fragment not in back stack, create it.
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content, fragment, tag);
            if (addBackStack) ft.addToBackStack(tag);
            ft.commit();
            Log.w(TAG, tag + " added to the backstack");
        } else {
            // fragment in back stack, call it back.
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content, existFragment, tag);
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

            String tag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2).getName();

            switch (tag) {
                case TAG_SEARCH:
                case TAG_DONATE:
                    navigation.setSelectedItemId(R.id.navigation_search);
                    break;
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
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public ScreenTag getCurrentScreen() {
        return null;
    }

    @Override
    public void goToStreamPage(int streamId) {
        Fragment donateFragment = DonateFragment.newInstance(streamId);
        changeFragment(donateFragment, TAG_DONATE, true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(StreamFeedItemModel item) {
//        startPaymentActivityForResult(
//                new P2pTransferParams.Builder(Long.toString(item.getId()))
//                        .setAmount(new BigDecimal(1))
//                        .create()
//        );

        goToStreamPage(item.getId());
    }

    private static final int REQUEST_CODE = 101;

//    private void startPaymentActivityForResult(PaymentParams paymentParams) {
//
//        goToScreen(ScreenTag.AUTH_YANDEX_MONEY);
//
//        Intent intent = PaymentActivity.getBuilder(this)
//                .setPaymentParams(paymentParams)
//                .setClientId(Config.YANDEX_MONEY_CLIENT_ID)
//                .build();
//        startActivityForResult(intent, REQUEST_CODE);
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
