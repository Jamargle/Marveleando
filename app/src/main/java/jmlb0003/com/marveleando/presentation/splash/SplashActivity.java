package jmlb0003.com.marveleando.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import jmlb0003.com.marveleando.app.utils.FirebaseAnalyticsHelper;
import jmlb0003.com.marveleando.presentation.list.CharacterListActivity;

public final class SplashActivity extends AppCompatActivity {

    @Inject FirebaseAnalyticsHelper analyticsHelper;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        analyticsHelper.setCurrentScreenSplash(this);
        startActivity(new Intent(this, CharacterListActivity.class));

        finish();
    }

}
