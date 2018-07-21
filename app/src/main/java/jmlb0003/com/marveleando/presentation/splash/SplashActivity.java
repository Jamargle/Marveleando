package jmlb0003.com.marveleando.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import jmlb0003.com.marveleando.presentation.list.CharacterListActivity;

public final class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, CharacterListActivity.class));

        finish();
    }

}
