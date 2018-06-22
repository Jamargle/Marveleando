package jmlb0003.com.marveleando.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public abstract class BaseFragment<C, P extends BasePresenter>
        extends Fragment implements
        BasePresenter.BaseView {

    protected C callback;

    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    public void onAttach(final Activity activity) {
        if (((BaseActivity) activity).fragmentInjector() != null) {
            AndroidInjection.inject(this);
        }
        super.onAttach(activity);
        try {
            callback = (C) activity;
        } catch (final ClassCastException exception) {
            throw new RuntimeException(activity.toString() + " must implement " +
                    callback.getClass().getSimpleName());
        }
    }

    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {

        final View rootView = inflater.inflate(getLayoutResourceId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @LayoutRes protected abstract int getLayoutResourceId();

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        getPresenter().attachView(this);
    }

    @NonNull protected abstract P getPresenter();

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(isToBeRetained());
    }

    /**
     * Control whether a fragment instance is retained across Activity re-creation (such as from
     * a configuration change)
     */
    protected boolean isToBeRetained() {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getPresenter().getView() == null) {
            getPresenter().attachView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().detachView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

}
