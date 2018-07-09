package jmlb0003.com.marveleando.presentation.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import jmlb0003.com.marveleando.R;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.presentation.BaseActivity;

public final class CharacterDetailActivity
        extends BaseActivity<CharacterDetailActivityPresenter> implements
        CharacterDetailActivityPresenter.CharacterDetailActivityView,
        CharacterDetailFragment.Callback {

    private static final String CHARACTER_TO_SHOW = "Key:CharacterDetailActivity_Character";

    @Inject CharacterDetailActivityPresenter presenter;

    public static Bundle newBundle(final Character character) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(CHARACTER_TO_SHOW, character);
        return bundle;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @NonNull
    @Override
    protected CharacterDetailActivityPresenter getPresenter() {
        return presenter;
    }

}

