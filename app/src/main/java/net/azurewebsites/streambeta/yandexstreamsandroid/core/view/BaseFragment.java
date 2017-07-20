package net.azurewebsites.streambeta.yandexstreamsandroid.core.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.azurewebsites.streambeta.yandexstreamsandroid.R;
import net.azurewebsites.streambeta.yandexstreamsandroid.core.Presenter;
import net.azurewebsites.streambeta.yandexstreamsandroid.domain.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO add description
 */

public abstract class BaseFragment extends Fragment
        implements net.azurewebsites.streambeta.yandexstreamsandroid.core.View {
	@BindView(R.id.progressbar)
	View progressbar;

	private Unbinder unbinder;

	public abstract int getLayoutId();

	public abstract String getPresenterId();

	public abstract View setupView(View view);

	public abstract void loadData();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
		unbinder = ButterKnife.bind(this, view);
		return setupView(view);
	}

	@Override
	public void onStart() {
		super.onStart();
		loadData();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	public <CustomPresenter> CustomPresenter getPresenter(Class<CustomPresenter> c) {
		return c.cast(App.getAppInstance().getPresenterProvider().getPresenter(getPresenterId(),
				Presenter.class));
	}

	@Override
	public void showProgressbar() {
		progressbar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgressbar() {
		progressbar.setVisibility(View.GONE);
	}

	@Override
	public void dispose() {
		App.getAppInstance().getPresenterProvider().removePresenter(getPresenterId());
	}

}
