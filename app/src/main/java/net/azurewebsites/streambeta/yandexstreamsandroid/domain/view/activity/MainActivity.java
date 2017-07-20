package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.model.StreamModel;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.MainPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.GamecodeFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.ProfileFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.SearchFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.MainView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;


public class MainActivity extends AppCompatActivity implements MainView, MainRouter,
        SearchFragment.OnListFragmentInteractionListener, GamecodeFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener{

    final FragmentManager fragmentManager = getFragmentManager();
    Fragment searchFragment, gamecodeFragment, profileFragment;

    private MainPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            final FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_search:

                    transaction.replace(R.id.content, searchFragment);
                    transaction.addToBackStack("searchFragment");
                    transaction.commit();

                    return true;
                case R.id.navigation_gamecode:

                    transaction.replace(R.id.content, gamecodeFragment);
                    transaction.addToBackStack("gamecodeFragment");
                    transaction.commit();

                    return true;
                case R.id.navigation_profile:

                    transaction.replace(R.id.content, profileFragment);
                    transaction.addToBackStack("profileFragment");
                    transaction.commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = SearchFragment.newInstance(1);
        gamecodeFragment = GamecodeFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        fragmentManager.popBackStack();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void goBack() {

    }

    @Override
    public void goToScreen(ScreenTag screenTag) {

    }

    @Override
    public ScreenTag getCurrentScreen() {
        return null;
    }

    @Override
    public void onListFragmentInteraction(StreamModel item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
