package jmlb0003.com.marveleando.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity implements
        BasePresenter.BaseView,
        HasFragmentInjector {

    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;

    @NonNull protected abstract P getPresenter();

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        getPresenter().attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().detachView();
    }

}
