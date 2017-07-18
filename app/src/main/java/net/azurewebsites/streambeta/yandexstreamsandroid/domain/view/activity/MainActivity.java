package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.presenter.interfaces.MainPresenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.MainRouter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.router.ScreenTag;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.GamecodeFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.ProfileFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.SearchFragment;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.fragment.dummy.DummyContent;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces.MainView;


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
                    transaction.commit();

                    return true;
                case R.id.navigation_gamecode:

                    transaction.replace(R.id.content, gamecodeFragment);
                    transaction.commit();

                    return true;
                case R.id.navigation_profile:

                    transaction.replace(R.id.content, profileFragment);
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
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
